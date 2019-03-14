package Test1;
/*
 *泛型
 */
class myGenericity <T>{
    //泛型类中的普通方法
    public void mythod1(T t){
        System.out.println(t);
    }
    //泛型类中的泛型方法
    public <E>  E mythod2( E e){
        return e;
    }
}
public class genericityTest {
    public static void main(String[] args) {

    myGenericity <String > m=new myGenericity<>();
    m.mythod1("你好，农场");
    Integer m1=m.mythod2(200);
    System.out.println(m1);
    }
}
