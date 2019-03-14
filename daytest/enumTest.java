package Test1;
enum photo{
    oppo,
    vivo,
    huawei
}

public class enumTest {
    public static void main(String[] args) {
        for(photo i:photo.values()){
            System.out.println(i.ordinal()+" :"+i.name());
        }
    }
}
