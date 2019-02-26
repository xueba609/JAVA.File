package extthread;

class IntDemo {
    private int num ;
    public IntDemo(int num) {
        this.num = num ;
    }
    public int intValue() {
        return this.num ;
    }
}
public class test {
    public static void main(String[] args) {
// 子类对象向上转型
        Object obj = new IntDemo(55);
        IntDemo temp = (IntDemo) obj; // 向下转型
        System.out.println(temp.intValue()); // 取出里面的基本数据类型操作
    }
}