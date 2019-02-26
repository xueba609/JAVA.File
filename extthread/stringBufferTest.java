package extthread;

public class stringBufferTest {
        public static void main(String[] args) {
            StringBuffer sb = new StringBuffer();
            StringBuilder st = new StringBuilder();
            st.append("Hello").append("World");
            test1(st);
            System.out.println(st);
            sb.append("Hello").append("wang");
            test(sb);
            System.out.println(sb);
        }
        public static void test(StringBuffer temp) {
            temp.append("\n").append("你好 世界");
        }
        public static void test1(StringBuilder temp) {
        temp.append("\n").append("你好 wang");
    }
    }

