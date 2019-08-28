项目名称：
诗集分析器
=
前言
==
为什么会写这个项目？，2019/3/7号的晚上，宿舍已经熄灯的。当我准备入睡的时候，宿舍的两位忧国忧民诗人便又开启了诵读，吟诗的环节。奈何自己文学成就并没有他们那么高，并不能参与到人家的讨论之中。这时脑子闪过一个想法，古诗有那么多，谁写的最多呢，古代的流行词汇又是什么样的。基于以上的想法，我就想到能否使用自己掌握的技术来实现这个呢。第二天我便开始了我的项目构思，1.数据源从哪里来2.如何判断这个页面是存放数据的页面3.数据怎么拿下来呢4.拿到的数据怎么存5.如何让从存好的数据拿到自己想要的数据6.数据怎么展示。  通过查阅资料以及询问老师和同学上面的问题也慢慢的都解决了。<br>

构建的流程图：
![诗集分析器图片](https://github.com/xueba609/JAVA.File/blob/master/%E5%9B%BE%E7%89%87/%E8%AF%97%E9%9B%86%E5%88%86%E6%9E%90%E5%99%A8.png)  

##涉及技术
===

JavaSE的基础知识

多线程

生产者和消费者模型

对象工厂模式

MySQL数据库

htmlunit网页解析工具

ansj中文分词工具

##实现功能
===
拿到诗人的姓名和创作数量

拿到诗句的词和词的数量

一.准备
===
1创建数据表：
=====
```java
create table poety_info

(
  title   varchar(64)   primary key ,
  
  dynasty varchar(32)   not null,
  
  author  varchar(12)   not null,
  
  content varchar(1024) not null
  
)engine = InnoDB;
```

2导入相关依赖包
=====

（1）首先导入项目中可能用到的依赖包： 见pom.xml.

（2）相关的配置：网页的地址，数据库连接，用户和密码等：见resources\config.properties

二.crawler包下进行采集，解析，清洗（用到生产者和消费者模型）
===
1.parse包下进行采集和解析（用到htmlunit解析工具）
=====

1.1采集放在Documentparse.java类中，主要判断是标题页还是详情页,是详情页不用处理，不是详情页就要进行跳转拿到详情
=======
```java
public class Documentparse implements Parse {
    @Override
    public void parse(final Page page) {
        //是详情页面就不用处理了
        if (page.isDetail()) {
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();

        System.out.println(htmlPage);
        /*如果每次进入的不是详情页面，则进行操作。
        *通过源代码分析，<div class="typecont">
        *               <a helf="    ">
        返回一个集合，通过freEach循环
        */
        //获取网页的连接
        htmlPage.getBody()
                .getElementsByAttribute("div", "class", "typecont")
                .forEach(div -> {
                            //a标签节点的集合
                            DomNodeList<HtmlElement> aNodeList = div.getElementsByTagName("a");
                            aNodeList.forEach(aNode -> {
                                        //获取得到子页面
                                        String path = aNode.getAttribute("href");//获取a标签下的属性为href的连接

                                        Page subPage = new Page(page.getBase(), path, true);

                                        System.out.println(subPage);
                                       //将取出的超链接放入到子页面对象集合中
                                        page.getSubPage().add(subPage);
                                    }
                            );
                }
        );
    }

    }

```

1.2解析放在DataPageparse.java类中，解析出详情页的数据
=======
```java
public class DataPageparse implements Parse {
    @Override
    public void parse(Page page) {
        //判断是否是详情页面
        if (!page.isDetail()) {
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();
        HtmlElement body=htmlPage.getBody();
        System.out.println(htmlPage);
        //标题
        //h1只有一个，所以取0
        //text()意思是转成字符串
        ///html/body/div[3]/div[1]/div[2]/div[1]/h1(路径方式）
        String titlePath="//div[@class='cont']/h1/text()";
        DomText titleDom = (DomText) body.getByXPath(titlePath).get(0);
        String title=titleDom.asText();
        //朝代
        String dynastyPath="//div[@class='cont']/p/a[1]";
        HtmlAnchor dyanstDom= (HtmlAnchor) body.getByXPath(dynastyPath).get(0);
        String dynasty=dyanstDom.asText();
        //作者
        String authorPath="//div[@class='cont']/p/a[2]";
        HtmlAnchor authorDom=(HtmlAnchor) body.getByXPath(authorPath).get(0);
        String author=authorDom.asText();
        //内容
        String contentPath="//div[@class='cont']/div[@class='contson']";
        HtmlDivision contentDom=(HtmlDivision) body.getByXPath(contentPath).get(0);
        String content=contentDom.asText();

        //方便，在这里只需要把你想要的page数据加进去
        page.getDataSet().putData("title",title);
        page.getDataSet().putData("dynasty",dynasty);
        page.getDataSet().putData("author",author);
        page.getDataSet().putData("content",content);
        page.getDataSet().putData("url",page.geturl());


    }
}
```

2.pipeline包下进行清洗(使用到jdbc)
=====

2.1DataStore.java类中将诗的题目，朝代，作者，内容放在数据库中
========
```java
public class DataStore implements Pipeline {
    private final Logger logger=LoggerFactory.getLogger(DataStore.class);
    private final DataSource dataSource;
    public DataStore(DataSource dataSource){
        this.dataSource=dataSource;
    }
    @Override
    public void pipeline(Page page) {
        String title=(String)page.getDataSet().getData("title");
        String dynasty=(String)page.getDataSet().getData("dynasty");
        String author=(String)page.getDataSet().getData("author");
        String content=(String)page.getDataSet().getData("content");
        String sql="insert into poety_info(title,dynasty,author,content) values(?,?,?,?)" ;
        try(Connection connection=dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)
        ){
            statement.setString(1,title);
            statement.setString(2,dynasty);
            statement.setString(3,author);
            statement.setString(4,content);
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error("Database insert occur exception{}.",e.getMessage());
        }
    }
}
```

3.common包用来描述一些属性
=====

3.1Page.java类中主要页面的属性
========
```java
@Data
public class Page {
    /*
    数据网站的根地址  http://gushiwen.org
     */
    private  final String base;

    /*
    具体网页的路径   /shiwen_45c396367f59.aspx
     */
    private final String path;

    /*
    *网页的DOM对象（文档对象模型）
     */
    private HtmlPage htmlPage;

    /*
    *判断网页是否是详情页
     */
    private final boolean detail;

    /*
    *如果不是详情页，则还有许多子页面
     *子页面对象集合
     */
    private Set<Page> subPage=new HashSet<>();

    /*
     *数据的集合
     */
    private DataSet dataSet=new DataSet();

    /*
    *页面的整个网址
     */
    public String geturl(){
        return this.base+this.path;
    }

    public Page(String base,String path,boolean detail){
        this.base=base;
        this.path=path;
        this.detail=detail;

    }
}
```
4.Crawler.java类主要启动采集，解析，清洗，关闭。（使用线程池，多线程）
===
```java
public class Crawler {
        private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(
            Crawler.class);

    //存放文档页面（超链接）
    //未被采集和解析的页面
   private  Queue<Page> docQueue=new LinkedBlockingQueue<>();
    //放详情页面(数据）
    private final Queue<Page> detailQueue=new LinkedBlockingQueue<>();
    //采集器
    private final WebClient webClient;
    //所有的解析器
    private  List<Parse> parseList=new LinkedList<>();
    //管道清洗器（管道）
    private final List<Pipeline> pipelineList=new LinkedList<>();
    //线程执行器
    private final   ExecutorService executorService;

    public Crawler(){
        this.webClient=new WebClient(BrowserVersion.CHROME);
        this.webClient.getOptions().setJavaScriptEnabled(false);

        this.executorService= Executors.newFixedThreadPool(8, new ThreadFactory() {
            //线程工厂，自己给自己线程命名,从0开始
            private final AtomicInteger id=new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread=new Thread(r);
                thread.setName("Crawler_Thread-"+id.getAndIncrement());
                return thread;
            }
        });
    }

    /*
    *启动（采集，解析，清洗）
     */
    public   void start(){
        //采集解析
        this.executorService.submit(this::parse);
        //清洗
        this.executorService.submit(this::pipeline);
    }
    private void parse(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Parse occur exception {} .",e.getMessage());
                //e.printStackTrace();
            }
            //文档队列        出队列
          final Page page=  this.docQueue.poll();
            if(page==null){
                continue;
            }
            //多线程,单->多，调度器里面一放，匿名内部类，需要类名.this。
            this.executorService.submit(() -> {
                try {
                    //采集页面
                    HtmlPage htmlPage =Crawler.this.webClient.getPage(page.geturl());
                    page.setHtmlPage(htmlPage);
                    for(Parse parse:parseList){
                        parse.parse(page);
                    }
                    //解析
                    if(page.isDetail()){
                        //如果是详情页面
                        Crawler.this.detailQueue.add(page);
                    }else{
                        //文档页面，则继续进入队列，继续进行解析
                        Iterator<Page> iterator=page.getSubPage().iterator();
                        while(iterator.hasNext()){
                            Page subPage=iterator.next();
                            //将页面在加入到文档队列，下一次判断为详情页面，就能加入到详情页面了
                            Crawler.this.docQueue.add(subPage);
                            //拿到页面就移除掉了
                            iterator.remove();
                        }
                    }
                } catch (IOException e) {
                    LOGGER.error("Parse  task occur exception {} .",e.getMessage());
                }
            });
        }
    }
    private void pipeline(){
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Parse occur exception {} .",e.getMessage());
            }
            final Page page = this.detailQueue.poll();
            if (page == null) {
                continue;
            }
            this.executorService.submit(() -> {
                for(Pipeline pipeline:Crawler.this.pipelineList){
                    pipeline.pipeline(page);
                }
            });
        }
    }
    public void addPage(Page page){
        this.docQueue.add(page);
    }
    public void addParse(Parse parse){
        this.parseList.add(parse);
    }
    public void addPipeline(Pipeline pipeline){
        this.pipelineList.add(pipeline);
    }
    /*
    *爬虫停止
     */
    public void stop(){
        if(this.executorService != null && !this.executorService.isShutdown()){
            this.executorService.shutdown();
        }
        LOGGER.info("Crawler stop");
    }

}

```

三.analyze包下进行从数据库拿数据，并对数据进行分词
===

3.1dao包从数据库拿数据
========
```java
public class AnalyzeDaoImpl implements AnalyzeDao {
    private final Logger logger=LoggerFactory.getLogger(AnalyzeDaoImpl.class);
    //数据源
    public DataSource dataSource;
    public AnalyzeDaoImpl(DataSource dataSource){
        this.dataSource=dataSource;
    }
    @Override
    public List<AuthorCount> analyzeProductionAmount() {
        //存放的集合
        List<AuthorCount> data=new ArrayList<>();
        String sql="select count(*) as count,author from poety_info group by author ;";
       // try()自动关闭
        try(Connection connection=dataSource.getConnection();
            //准备命令
            PreparedStatement statement= connection.prepareStatement(sql);
            //执行，返回结果集
            ResultSet rs=statement.executeQuery()
            ) {
            while(rs.next()){
                AuthorCount p=new AuthorCount();
                p.setAuthor(rs.getString("author"));
                p.setCount(rs.getInt("count"));
                data.add(p);
            }
        } catch (SQLException e) {
            logger.error("Database query occur exception {}.",e.getMessage());
        }
        return data;
    }
     //从数据库把所有的用户信息取出来
    @Override
    public List<PoetyInfo> queryAllPoetyInfo() {
        List<PoetyInfo> datas = new ArrayList<>();
        String sql="select title,dynasty,author,content from poety_info;";
        try(Connection connection=dataSource.getConnection();
            PreparedStatement statement= connection.prepareStatement(sql);
            ResultSet rs =statement.executeQuery()
        ) {
            while(rs.next()){
                PoetyInfo poetyInfo=new PoetyInfo();
                poetyInfo.setTitle(rs.getString("title"));
                poetyInfo.setDynasty(rs.getString("dynasty"));
                poetyInfo.setAuthor(rs.getString("author"));
                poetyInfo.setContent(rs.getString("content"));
                datas.add(poetyInfo);
            }
        } catch (SQLException e) {
           logger.error("Database query occur exception{}.",e.getMessage());
        }
        return datas;
    }
}
```

3.1service包对数据进行分词，拿到诗句中词 过滤/v /w /null 
========
```java
public class AnalyzeServiceImpl implements AnalyzeApplication {

    private final AnalyzeDao analyzeDao;

    public AnalyzeServiceImpl(AnalyzeDao analyzeDao){
        this.analyzeDao=analyzeDao;
    }

    @Override
    public List<AuthorCount> analyzeProductionAmount() {
        /*
        *进行排序
        * 1.直接在dao层数据库里面使用order by
        * 2.在service（业务）层使用list
         */
        List<AuthorCount> authorCounts=analyzeDao.analyzeProductionAmount();
        //collections的sort方法
        Collections.sort(authorCounts, new Comparator<AuthorCount>() {
            @Override
            public int compare(AuthorCount o1, AuthorCount o2) {
                //升序
                return o1.getCount().compareTo(o2.getCount());
            }
        });
        return  authorCounts;
    }

    @Override
    public List<WordCount> analyzeWordCloud() {
        /*
        *查询出所有的数据
        * 取出 title content
        * 分词 过滤/v /w /null
        * 统计key-values   k-词   v-词频
         */
        Map<String,Integer> map=new HashMap<>();
        //拿到所有存储的诗集信息
        List<PoetyInfo> poetyInfos=analyzeDao.queryAllPoetyInfo();
        for(PoetyInfo poetyInfo : poetyInfos){
            //储存过滤出的数据
            List<Term> terms=new ArrayList<>();

            String title=poetyInfo.getTitle();
            String content=poetyInfo.getContent();

            terms.addAll(NlpAnalysis.parse(title).getTerms());
            terms.addAll(NlpAnalysis.parse(content).getTerms());
             //过滤
            Iterator<Term> iterator=terms.iterator();
            while(iterator.hasNext()){
                Term term=iterator.next();
                //判断词性
                //词性过滤
                if(term.getNatureStr() == null || term.getNatureStr().equals("w")){
                    iterator.remove();
                    continue;
                }
                //词的长度
                //词的过滤
                if(term.getRealName().length()<2){
                    iterator.remove();
                    continue;
                }
                //统计
                String realName= term.getRealName();
                int count;
                if(map.containsKey(realName)){
                    count=map.get(realName)+1;
                }else{
                    count=1;
                }
                map.put(realName,count);
            }
        }
        //把map转成list
        List<WordCount> wordCounts =new ArrayList<>();

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            WordCount wordCount =new WordCount();
            wordCount.setWord(entry.getKey());
            wordCount.setCount(entry.getValue());
            wordCounts.add(wordCount);
            }

        return wordCounts;

    }
}
```

4.config包主要归档配置相关的类
===
4.1ObjectFactory.java类存放各种对象，避免主程序繁重（如初始化配置对象，数据源对象，爬虫对象，web对象）
==========
```java
public class ObjectFactory {
    //单例，饿汉式
    private static final ObjectFactory instance = new ObjectFactory();
    private final Logger logger=LoggerFactory.getLogger(ObjectFactory.class);
    //存放所有对象
    private  final Map<Class,Object> objectHashMap =new HashMap<>();
    private  ObjectFactory(){
        //1.初始化配置对象
        initConfigMainContent();

        //2.数据源对象
        initDataSource();

        //3.爬虫对象
        initCrawler();

        //4.web对象
        initWebController();

        //5.打印清单
        PrintObjectList();
    }



    private void initWebController() {
        //数据源
        DataSource dataSource = getObject(DataSource.class);
        AnalyzeDao analyzeDao = new AnalyzeDaoImpl(dataSource);
        AnalyzeServiceImpl analyzeApplication = new AnalyzeServiceImpl(analyzeDao);
        WebController webController=new WebController(analyzeApplication);

        objectHashMap .put(WebController.class,webController);
    }

    private void initCrawler() {
        ConfigMainContent configMainContent=getObject(ConfigMainContent.class);
        DataSource dataSource=getObject(DataSource.class);
        final Page page=new Page(
                configMainContent.getCrawlerBase(),
                configMainContent.getCrawlerPath(),
                configMainContent.isCraelerDetail());

        Crawler crawler=new Crawler();

        crawler.addParse(new Documentparse());
        crawler.addParse(new DataPageparse());
        //通过配置的值来判断是否启动，
        if(configMainContent.isStart()) {
            crawler.addPipeline(new CansolePipeline());
        }
        crawler.addPipeline(new DataStore(dataSource));
        crawler.addPage(page);
        objectHashMap .put(Crawler.class,crawler);
    }

    private void initDataSource() {
        ConfigMainContent configMainContent=getObject(ConfigMainContent.class);
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUsername(configMainContent.getDbUsername() );
        dataSource.setPassword(configMainContent.getDbPassword());
        dataSource.setDriverClassName(configMainContent.getDbDriverClass());
        dataSource.setUrl(configMainContent.getDbUrl());
        objectHashMap .put(DataSource.class,dataSource);
    }

    private void initConfigMainContent() {
        ConfigMainContent configMainContent=new ConfigMainContent();

        objectHashMap.put(ConfigMainContent.class,configMainContent);
        //这一步方便我们查找配置信息哪里出错
        logger.info(" ConfigMainContent info:\n{}",configMainContent.toString());
    }

    public  <T> T getObject(Class classz){
        if(!objectHashMap .containsKey(classz)){
            throw new IllegalArgumentException(classz.getName()+"not found");
        }
        return (T) objectHashMap .get(classz);
    }

    public static ObjectFactory getInstance(){
        return instance;
    }
    private void  PrintObjectList(){
        logger.info("------------对象工厂清单----------");
        for(Map.Entry<Class,Object> entry:objectHashMap .entrySet()) {
            logger.info(String.format("\t[%-5s]->[%s]",
                    entry.getKey().getCanonicalName(), entry.getValue().getClass().getCanonicalName()));
        }
        logger.info("----------打印完毕------------");
        }
    }

```
4.web包主要归档web接口相关的类，与前端交互
===
```java
public class WebController {
    private final AnalyzeApplication analyzeApplication;

    public WebController(AnalyzeApplication analyzeApplication) {
        this.analyzeApplication = analyzeApplication;
    }
    //url地址
    //->http://127.0.0.1:4567/
    //->/analyze/AuthorCount
    public List<AuthorCount> analyzeproductionAmoubt(){
        return analyzeApplication.analyzeProductionAmount();
    }
    //url地址
    //->http://127.0.0.1:4567/
    //->/analyze/WordCount
    public List<WordCount> analyzeWordAmount(){
        return analyzeApplication.analyzeWordCloud();
    }
    //运行web
    public void launch(){
        ResponseTransformer transformer=new JSONResponseTransformer();
        //前端静态文件的目录 src/resource
        Spark.staticFileLocation("/static");
        //服务端接口
        Spark.get("/analyze/author_count",((request, response) ->
        analyzeproductionAmoubt()),transformer);
        Spark.get("/analyze/word_cloud",((request, response) ->
        analyzeWordAmount()),transformer);
        Spark.get("/crawler/stop", ((request, response) -> {
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            crawler.stop();
            return "爬虫停止";
        }));
    }
    public  static class JSONResponseTransformer implements ResponseTransformer{
        //Object ->string
        private  Gson gson=new Gson();
        @Override
        public String render(Object model) throws Exception {
            return gson.toJson(model);
        }
    }
}
```
项目效果：

页面展示图：
![诗人和诗的数量](https://github.com/xueba609/JAVA.File/blob/master/%E5%9B%BE%E7%89%87/%E8%AF%97%E4%BA%BA%E5%92%8C%E8%AF%97%E7%9A%84%E6%95%B0%E9%87%8F.png)

页面展示图：
![词和词的数量](https://github.com/xueba609/JAVA.File/blob/master/%E5%9B%BE%E7%89%87/%E8%AF%8D%E5%92%8C%E8%AF%8D%E7%9A%84%E9%A2%91%E7%8E%87.png)

