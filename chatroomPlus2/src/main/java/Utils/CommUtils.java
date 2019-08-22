package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
*封装公共工具方法,如加载配置文件，json序列化
* Ctrl+Shift+t
 */
public class CommUtils {

    private static final Gson GSON = new GsonBuilder().create();

    public static Properties loadProperties(String fileName){
        Properties properties=new Properties();
          InputStream in = CommUtils.class.getClassLoader().
                getResourceAsStream(fileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            return null;

        }
            return properties;
    }

    //将任意对象序列化为json字符串
    public static String object2Json(Object object){

        return GSON.toJson(object);
    }

    //将任意json字符串反序列为对象
    public static Object Json2object(String jsonStr,Class objClass){

        return GSON.fromJson(jsonStr,objClass);
    }
}
