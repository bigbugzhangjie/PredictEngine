package jack.utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTool {
	
	/**
	* 日期转换成字符串
	* @param date
	* @return str
	*/
	public static String DateToStr(Date date) {	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	}
	
	/**
	 * 日期转换成字符串
	 * @param date
	 * @param strFormat	希望的格式，如"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String DateToStr(Date date,String strFormat) {	  
	   SimpleDateFormat format = new SimpleDateFormat(strFormat);
	   String str = format.format(date);
	   return str;
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @param strFormat	str中各个部分的格式，如"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Date StrToDate(String str,String strFormat) {	  
	   SimpleDateFormat format = new SimpleDateFormat(strFormat);
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * 字符串转换成日期
	 * @param str
	 * @return
	 */
	public static Date StrToDate(String str) {	  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   return date;
		}

	public static void main(String[] args) throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
		java.util.Date date=new java.util.Date();  
		String str=sdf.format(date);  
		System.out.println(str);
		
//		System.out.println(Long.MAX_VALUE);

	}

}
