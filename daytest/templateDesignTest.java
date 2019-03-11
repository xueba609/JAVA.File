package Test1;
/*模板设计模式
 *1.烧水
 * 2.冲泡饮料
 * 3.添加辅料
 * 4.洗杯子
  抽象方法，继承实现不同步骤
 */
abstract class drinks{
    //希望这个方法不被修改
     final void  prepare(){
         boilWater();
         addAccessory();
         brewDrinkes();
         wishCup();
     }
    void boilWater(){
         System.out.println("1.烧水");
    }
    void wishCup(){
         System.out.println("4.洗杯子");
    }
    abstract void addAccessory();abstract void brewDrinkes();
}
class coffer extends drinks{

    @Override
    void addAccessory() {
        System.out.println("2.添加糖和牛奶");
    }

    @Override
    void brewDrinkes() {
        System.out.println("3.将咖啡倒入杯子");
    }
}
class milkTea extends drinks{

    @Override
    void addAccessory() {
        System.out.println("2.添加红豆，椰果");
    }

    @Override
    void brewDrinkes() {
        System.out.println("3.将奶茶倒入杯子");

    }
}

public class templateDesignTest {
    public static void main(String[] args) {
        coffer c=new coffer();
        c.prepare();
        milkTea m=new milkTea();
        m.prepare();
    }
}
