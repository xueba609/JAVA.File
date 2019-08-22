package client.service;

import Utils.CommUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

public class ConnectServer {
    private static final String IP;
    private static  final int PORT;

    private Socket client;
    private InputStream in;
    private OutputStream out;

    static {
        Properties properties=CommUtils.loadProperties("socket.properties");
        IP=properties.getProperty("address");
        PORT= Integer.parseInt(properties.getProperty("port"));
    }
    public ConnectServer(){
        try {
            client=new Socket(IP,PORT);
            in=client.getInputStream();
            out=client.getOutputStream();
        } catch (IOException e) {
            System.out.println("与服务器建立连接失败");
            e.printStackTrace();
        }
    }
    public InputStream getIn(){
        return in;
    }
    public OutputStream getout(){
        return out;
    }
}
