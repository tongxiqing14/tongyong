package test;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: tongxiqing
 * Date: 14-12-5
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class TestMain {     //在J2ME里操作session //

    public void run()
    {
        StringBuffer url=new StringBuffer();
        String data=null;
        HttpConnection http;
        DataInputStream dis;
//        try
//        {
//            while(!stop)
//            {
//                url.delete(0,url.length());
//                url.append(URL);
//                synchronized(requests)
//                {
//                    while(requests.empty())
//                    {
//                        if(stop)
//                            return;
//
//                        requests.wait(REFRESH_TIME);
//                        if(requests.empty())
//                            _0X07();
//                    }
//
//                    while(!requests.empty())
//                    {
//                        url.append((String)requests.pop());
//                    }
//                }
//                if(DEBUG)
//                    System.out.println(url.toString());
//                http=(HttpConnection) Connector.open(url.toString());
//                http.setRequestProperty("User-Agent","com.dgtdg.games.Five");
//                if(null!=cookie)
//                    http.setRequestProperty("Cookie",cookie);
//                if(HttpConnection.HTTP_OK!=http.getResponseCode())
//                {
//                    http.close();
//                    continue;
//                }
//
//                if(null==cookie)
//                {
//                    String s=http.getHeaderField("Set-Cookie");
//                    if(null!=s)
//                    {
//                        if(DEBUG)
//                            System.out.println("Set-Cookie:"+s);
//                        int start=s.indexOf("session=");
//                        int end=(-1==s.indexOf(',',-1==start?0:start)?s.length():s.indexOf(',',-1==start?0:start));
//                        if(-1!=start && -1!=end)
//                            cookie=s.substring(start,end);
//                    }
//                }
//                dis=http.openDataInputStream();
//
//                if(DEBUG)
//                {
//                    byte[] temp=new byte[256];
//                    int len=dis.read(temp);
//                    if(-1!=len && 0!=len)
//                    {
//                        byte[] tmp=new byte[len];
//                        System.arraycopy(temp,0,tmp,0,len);
//                        HexDump.dump(tmp,0,System.out,0);
//                        dis=new DataInputStream(new ByteArrayInputStream(tmp));
//                    }
//                }
//
//                pop(dis);
//
//                dis.close();
//                http.close();
//            }
//        }
//        catch(java.io.IOException ioe){}
//        catch(java.lang.InterruptedException ie){}
    }

}
