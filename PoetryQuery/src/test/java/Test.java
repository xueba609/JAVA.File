import com.wang.proetryQuery.crawler.common.Page;
import com.wang.proetryQuery.crawler.parse.Documentparse;
import com.wang.proetryQuery.crawler.parse.Parse;

public class Test {
    public static void main(String[] args) {
        Parse parse=new Documentparse();
        parse.parse(new Page("https://so.gushiwen.org","/gushi/tangshi.aspx",false));
    }
}
