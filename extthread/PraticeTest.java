package extthread;


public class PraticeTest {
    public static void main(String[] args) {
        String str = "abcd22wibjzsj";
        String str1 = "yuisama:27|yui:25" ;
        //将字符串转换为字符数组
        char[] data = str.toCharArray();
        System.out.println(isNumberss(data) ? "有数字" : "无数字");
        if(str.startsWith("a")){
            System.out.println("你有a");
        }
        if(str.contains("abc")){
            System.out.println("你有子串");
        }
        if(str.endsWith("j")){
            System.out.println("你有l");
        }
        String pp=str.replaceAll("a","A");
        System.out.println(pp);
        String cut=str.substring(0,5);
        System.out.println(cut);
        String [] data1=str1.split("\\|");
        String big=str.toUpperCase();
        System.out.println(big);
        for(int i=0;i<data1.length;i++){
            String [] depart=data1[i].split("\\:");
            System.out.println(depart[0]+"年龄为："+depart[1]);
        }
    }
        static boolean isNumberss(char[]data){
            for (int i = 0; i < data.length; i++) {
                if (data[i] > '0' || data[i] < '9') {
                    return true;
                }
            }
            return false;
        }

    }
