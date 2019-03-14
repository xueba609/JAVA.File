package Test1;
/*
 *单例模式-饿汉式
 * 所谓饿汉式就是未在调用它时，已经实例化好了
 * 使用private构造方法，外部不可以实例化，但内部可以实例化
 * 因此在内部实例化，为了优先于主类执行引入static
 * 不希望被修改，使用final
 */
class Singleton{
    private static final Singleton TEMP=new Singleton();
    private Singleton(){
    }
   public  static Singleton getTemp(){
        return TEMP;
    }
    public void print(){
        System.out.println("我是饿汉式");
    }
}
public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton=null;
        singleton=Singleton.getTemp();
        singleton.print();
    }
}
