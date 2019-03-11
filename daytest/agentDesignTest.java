package Test1;

import java.nio.file.CopyOption;
import java.sql.Connection;
import java.util.concurrent.Callable;

/*
*代理设计模式
* 一个类实现主要业务（买电脑）
* 另一个类实现辅助的业务（生产电脑，售后服务）
 */
interface Computer{
     void buyComputer();
}
//主要业务
class primaryService implements Computer {
    @Override
    public void buyComputer() {
        System.out.println("2.买电脑");
    }
}
//辅助业务
class assistService implements Computer{
    private Computer computer;

    public assistService(Computer computer) {
        this.computer = computer;
    }
    public void fromBuy(){
        System.out.println("1.生产电脑");
    }
    public void afterBuy(){
        System.out.println("3.售后服务");
    }
    @Override
    public void buyComputer() {
        this.fromBuy();
        this.computer.buyComputer();
        this.afterBuy();
    }
    }
class computerFactory {
    public static Computer getmessage() {
        return new assistService(new primaryService());
    }
}
public class agentDesignTest {
    public static void main(String[] args) {
        Computer computer=computerFactory.getmessage();
        computer.buyComputer();

    }
}
