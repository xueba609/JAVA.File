package offerTest.String;
/*题目描述：
*请实现一个函数，将一个字符串中的每个空格替换成“%20”。
* 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */

public class replaceBlank {
    public static void main(String[] args) {
        StringBuffer str=new StringBuffer();
        str.append("We").append(" ").append("Are").append(" ").append("Happy");
        System.out.println(replaceSpace(str));
    }
    private static String replaceSpace(StringBuffer str) {
        /*
        String s=str.toString();
        return s.replaceAll(" ","%20");
        */

        if(str==null){
            return null;
        }
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                str.replace(i,i+1,"%20");
            }
        }
        return str.toString();


    }

}
