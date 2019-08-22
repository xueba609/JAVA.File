package VO;

import lombok.Data;
//服务器与客户端传递信息载体
@Data
public class MessageVO {
    //告诉服务器要进行的动作，1代表用户注册，2.表示私聊
    private String type;
    //发送到服务器的具体内容
    private String content;
    //私聊告知服务器要将信息发给那个用户
    private String to;
}
