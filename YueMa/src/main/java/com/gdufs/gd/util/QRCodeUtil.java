package com.gdufs.gd.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码生成识别的工具类
 * @author Administrator
 *
 */
public class QRCodeUtil {
	/**
	 * 生成二维码
	 * @param content
	 * @param width
	 * @param height
	 * @param format
	 * @param filePath
	 * @param fileName
	 * @throws WriterException
	 * @throws IOException
	 */
	 public static void encodeQRCode(String content, int width, int height,String format,String filePath,String fileName) throws WriterException, IOException{
   	  Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
         hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
         BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                 BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
         Path path = FileSystems.getDefault().getPath(filePath, fileName);
         MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
         System.out.println("输出成功.");  
   }
 
	 /**
	  * 解析二维码
	  * @param filePath
	  * @return
	  */
   public static String decodeQRCode(String filePath) {  
       BufferedImage image;
       String content=null;
       try {  
           image = ImageIO.read(new File(filePath));  
           LuminanceSource source = new BufferedImageLuminanceSource(image);  
           Binarizer binarizer = new HybridBinarizer(source);  
           BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
           Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
           hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
           Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
           content=result.getText(); 
       } catch (IOException e) {  
           e.printStackTrace();  
       } catch (NotFoundException e) {  
           e.printStackTrace();  
       }
		return content;  
   }  
}
