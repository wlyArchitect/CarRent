import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 万浩
 * @data 2019/12/2 18:33
 * @description
 */
public class TestGetIp {
    @Test
    public void test() throws UnknownHostException {
        // TODO Auto-generated method stub
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println("本机名称是："+ localname);
            System.out.println("本机的ip是 ："+localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InetAddress ia1 = InetAddress.getLocalHost();//获取本地IP对象

    }
}
