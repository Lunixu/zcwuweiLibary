package wx.wuwei.service;

import java.io.File;
import java.util.Hashtable;

import wx.wuwei.util.QRCodeUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


public class createQRCodeService {
	/** 
     * @param args 
     * @throws Exception  
     */  
	//产生借书二维码
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  String createBorrowQRCodeURL(String content) throws Exception
    {  
    	String URL="";
    	int width = 300;  
        int height = 300;
        String format = "gif";  
        Hashtable hints = new Hashtable(); 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints); 
        File outputFile = new File("C:"+File.separator+"Program Files"+File.separator+"Apache Software Foundation"+File.separator+"Tomcat 7.0"+File.separator+"webapps"+File.separator+"WeiXinTest"+File.separator+"QRcode"+File.separator+"BorrowQRCode.gif");  
        //File outputFile = new File("F:"+File.separator+"DynamicWeb"+File.separator+"workspace"+File.separator+"WeiXinTest"+File.separator+"WebContent"+File.separator+"QRcode"+File.separator+"BorrowQRCode.gif");
        QRCodeUtil.writeToFile(bitMatrix, format, outputFile); 
        URL="QRcode"+File.separator+"BorrowQRCode.gif";
        System.out.println(URL);
    	return URL;
    }
	//产生还书二维码
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  String createReturnQRCodeURL(String content) throws Exception
    {  
    	String URL="";
    	int width = 300;  
        int height = 300;
        String format = "gif";  
        Hashtable hints = new Hashtable(); 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints); 
        File outputFile = new File("C:"+File.separator+"Program Files"+File.separator+"Apache Software Foundation"+File.separator+"Tomcat 7.0"+File.separator+"webapps"+File.separator+"WeiXinTest"+File.separator+"QRcode"+File.separator+"ReturnQRCode.gif");  
        //F:\DynamicWeb\workspace\WeiXinTest\WebContent\QRcode
        //File outputFile = new File("F:"+File.separator+"DynamicWeb"+File.separator+"workspace"+File.separator+"WeiXinTest"+File.separator+"WebContent"+File.separator+"QRcode"+File.separator+"ReturnQRCode.gif");
        QRCodeUtil.writeToFile(bitMatrix, format, outputFile); 
        URL="QRcode"+File.separator+"ReturnQRCode.gif";
        System.out.println(URL);
    	return URL;
    }
}
