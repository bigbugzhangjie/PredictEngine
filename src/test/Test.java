package test;

import java.io.File;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
//		java.util.Date date=new java.util.Date();  
//		String str=sdf.format(date);  
//		System.out.println(str);
		
//		System.out.println(Long.MAX_VALUE);
		
		File dir = new File("/home/bigbug/.android/avd");
		System.out.println(dir.getAbsolutePath());
		System.out.println(dir.getCanonicalPath());
		System.out.println(dir.getName());
		System.out.println(dir.getPath());
	}

}
