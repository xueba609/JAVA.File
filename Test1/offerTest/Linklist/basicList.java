package offerTest.Linklist;
/*
*链表的普通方法
 */

import java.util.Scanner;

class Node{
    Node next=null;
    char data;
    public Node(char data){
        this.data=data;
    }
}
public class basicList{
    Node head = null;
    //添加链表
    public void addNode(char data) {
        Node newNode = new Node(data);
        if (head == null)
        {
            head = newNode;
            return ;
        }
        Node temp = head;
        Node cur=null;
        while (temp.next != null)
        {
            if(temp.data==data)
            {
                cur = temp;
            }
            temp = temp.next;
        }
        temp.next = newNode;
        if(cur!=null){
            temp.next.next=cur;
        }
    }
    //判断是否有环
    public boolean isLoop() {
        Node fast = head;
        Node slow = head;
        if (fast == null) {
            return false;
        }
        while (fast != null && fast.next!= null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return !(fast == null || fast.next == null);
    }
    //打印链表
    public void printl(){
        Node temp=head;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        String s = src.nextLine();
        char[] data = s.toCharArray();
       basicList list = new basicList();
        list.addNode(data[0]);
        for (int i = 1; i < data.length - 1; i++) {
            if (data[i] == ',') {
                data[i] = data[i + 1];
                list.addNode(data[i]);
            }
        }
        //   list.printl();
        System.out.println(list.isLoop());
    }
}


