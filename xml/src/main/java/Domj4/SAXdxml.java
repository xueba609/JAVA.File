package Domj4;
/*
需求:将contact.xml文件原封不动的输出:使用SAX解析
1)	创建解析器
2)	读取contact.xml文件
3)	定义一个基于事件的处理程序(一个类 extends DefaultHandler)
     使用字符串缓存区对象StringBuffer
     定义一个方法---->可以将字符串缓存区的内容--->String类型
     public String getContent(){StringBuffer.toString()}
开始标签startElement(String qName ,Atrributes attributes)
     sb.append(“<”+qName) ;   //<concat id=”001”>
     遍历属性列表
        getValue()/getName()

   sb.append(属性名称=value)

文本内容
    String(ch,start,length) ;

结束标签
   Sb.append(“</”+qName+”>”);

 */

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXdxml {

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        //1.创建SAX解析器
        SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
        MyDefaultHandler1 myDefaultHandler1=new MyDefaultHandler1();
        //2.解析contact1.xml文件
        parser.parse(SAXdxml.class.getClassLoader().getResourceAsStream("contact1.xml"),myDefaultHandler1,);


    }
}
