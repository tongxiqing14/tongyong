package download;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;

public class Download {
	
	public byte[] DownLoading(String url){
		byte data[] = null;
		try {
			HttpConnection httpConn = (HttpConnection)Connector.open(url);
			httpConn.setRequestMethod(HttpConnection.GET);
//			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			DataInputStream in = httpConn.openDataInputStream();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len=(int)httpConn.getLength();
            if(len!=-1){
            	data=new byte[len];
                in.readFully(data);
            }
            else {
            int ch = 0;
            while ((ch = in.read()) != -1)
            {
                baos.write(ch);
            }
            data = baos.toByteArray();
            }

			
			httpConn.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static String downloadImageURL="";
	public Image creatImage(String url){
		Image img = null;
		byte data[] = DownLoading(downloadImageURL+url);
		img = Image.createImage(data, 0, data.length);
		return img;
	}
	
}
