package Domj4;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/**
 * SAX解析:---->针对事件编程
 * 三要素:
 *  1)事件源:xml文件
 *  2)事件监听器:
 *          遇到文档开始
 *                  startDocment(...)
 *          遇到开始标签:
 *                   startElement(...)
 *           遇到文本内容:
 *                   characters(...)
 *           遇到结束标签
 *                 endElement(...)
 *           文档结束
 *                  endDocuement(...)
 *   3)注册事件监听器:基于事件处理程序
 *           DefaultHandler
 *
 *      开发步骤
 *          1)创建SAX解析器对象
 *          2)解析当前xml文件
 *          3)基于事件处理进行判断(开始标签/结束标签/文本内容)
 */
public class SAXDemo1 {
}
