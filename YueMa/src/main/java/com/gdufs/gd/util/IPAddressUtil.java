package com.gdufs.gd.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * IP地址类的工具包
 * @author Administrator
 *
 */
public class IPAddressUtil {
	public static InetAddress getInetAddress(){  
		  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            System.out.println("unknown host!");  
        }  
        return null;  
  
    }  
  
    public static String getHostIp(){  
        String ip=null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //get the ip address  
        return ip;  
    }  
  
    public static String getHostName(InetAddress netAddress){  
    	String name=null;
 		try {
 			name = InetAddress.getLocalHost().getHostName();
 		} catch (UnknownHostException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} //get the ip address  
        return name;  
    }  
}
