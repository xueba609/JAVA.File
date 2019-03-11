package Test1;

import java.util.Scanner;

/*
简单工厂设计模式

 */
interface  Photo{
    void printPhoto();
}
class oppo implements Photo{

    @Override
    public void printPhoto() {
        System.out.println("oppo手机购买成功");
    }
}
class HuaWei implements Photo{

    @Override
    public void printPhoto() {
        System.out.println("HuaWei手机成功");
    }
}
class photoFactory{
    public static Photo getmessage(String x){
        //判断是需要什么类型手机号
        Photo photo = null;
        if(x.equals("oppo")){
             photo= new oppo();
        }else if(x.equals("HuaWei")){
             photo= new HuaWei();
        }
        return photo;
    }
}

public class simpleFactoryTest {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("请输入你想要买的手机型号");
        String str=s.nextLine();
        Photo photo=photoFactory.getmessage(str) ;
        buyPhoto(photo);

    }
    private static void buyPhoto(Photo photo) {
        photo.printPhoto();
    }
}
