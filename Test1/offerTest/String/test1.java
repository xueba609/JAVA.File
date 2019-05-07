package offerTest.String;

public class test1 {
    public static void main(String[] args) {
        String s = "abc";
        char[] c = s.toCharArray();
        StringBuffer sb = new StringBuffer("");
        int len = c.length;
        for (int i = 1; i <= len; i++) {
            CombineRecursiveImpl(c, 0, i, sb);
        }
    }

    private static void CombineRecursiveImpl(char[] c, int begin, int len, StringBuffer sb) {
        if (len == 0) {
            System.out.println(sb + " ");
            return;
        }
        if (begin == c.length) {
            return;
        }
        sb.append(c[begin]);
       CombineRecursiveImpl(c, begin + 1, len - 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(len);
        CombineRecursiveImpl(c, begin + 1, len, sb);
    }
}
