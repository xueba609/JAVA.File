package com.wang.proetryQuery.analyze.service;


import com.wang.proetryQuery.analyze.dao.AnalyzeDao;
import com.wang.proetryQuery.analyze.entity.PoetyInfo;
import com.wang.proetryQuery.analyze.model.AuthorCount;
import com.wang.proetryQuery.analyze.model.WordCount;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;


import java.util.*;

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
        List<AuthorCount> productionAmounts=analyzeDao.analyzeProductionAmount();
        //collections的sort方法
        Collections.sort(productionAmounts, new Comparator<AuthorCount>() {
            @Override
            public int compare(AuthorCount o1, AuthorCount o2) {
                //升序
                return o1.getCount().compareTo(o2.getCount());
            }
        });
        return  productionAmounts;
    }

    @Override
    public List<WordCount> analyzeWordCloud() {
        /*
        *查询出所有的数据
        * 取出 title content
        * 分词
        * 统计key-values
         */
        Map<String,Integer> map=new HashMap<>();
        List<PoetyInfo> poetyInfos=analyzeDao.queryAllPoetyInfo();
        for(PoetyInfo poetyInfo : poetyInfos){
            //储存过滤出的数据
            List<Term> terms=new ArrayList<>();
            String title=poetyInfo.getTitle();
            String content=poetyInfo.getContent();
            terms.addAll(NlpAnalysis.parse(title).getTerms());
            terms.addAll(NlpAnalysis.parse(content).getTerms());

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
