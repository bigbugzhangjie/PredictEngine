package jack.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTools {
 
    
    public static boolean isValidIDCard(String idcard){
    	return ChinaIDCard.validateCard(idcard);	
    }

	/**
	 * 验证IP地址
	 */
	public static boolean isValidIP(String ip){
		 Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		 Matcher matcher = pattern.matcher(ip);
		 return matcher.matches();
	}
	
	public static boolean isValidMobile(String mobile){    
		/*
2009年9月7日统计：
	 中国移动拥有号码段：139、138、137、136、135、134、159、158、157（3G）、152、151、150、188（3G）、187（3G）;14个号段
	 中国联通拥有号码段：130、131、132、155、156（3G）、186（3G）、185（3G）;6个号段
	 中国电信拥有号码段：133、153、189（3G）、180（3G）;4个号码段
	 移动:
	     2G号段(GSM网络)有139,138,137,136,135,134(0-8),159,158,152,151,150
	     3G号段(TD-SCDMA网络)有157,188,187
	     147是移动TD上网卡专用号段.
	 联通:
	     2G号段(GSM网络)有130,131,132,155,156
	     3G号段(WCDMA网络)有186,185
	 电信:
	     2G号段(CDMA网络)有133,153
	     3G号段(CDMA网络)有189,180
		 */
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");     
        Matcher m = p.matcher(mobile);     
        return m.matches();     
    } 
	
    public static boolean isValidEmail(String email){  
    	//  允许有:字母.-
    	//还可以写成：  \w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
    	//或者 String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
    	String str = "[//w//.//-]+@([//w//-]+//.)+[//w//-]+" ;
    	Pattern pattern = Pattern.compile(str,Pattern.CASE_INSENSITIVE);  
    	Matcher matcher = pattern.matcher(email);  
        if(matcher.matches()){  
            return true;
        }else{  
            return false;  
        }  
    }
    
    

	public static void examples(String[] args) {
		/*
		  1 匹配IP地址的正则表达式：  /(\d+)\.(\d+)\.(\d+)\.(\d+)/g //
		  2 整数或者小数：^[0-9]+\.{0,1}[0-9]{0,2}$
		  3 只能输入数字："^[0-9]*$"。
		  4 只能输入n位的数字："^\d{n}$"。
		  5 只能输入至少n位的数字："^\d{n,}$"。
		  6 只能输入m~n位的数字：。"^\d{m,n}$"
		  7 只能输入零和非零开头的数字："^(0|[1-9][0-9]*)$"。
		  8 只能输入有两位小数的正实数："^[0-9]+(.[0-9]{2})?$"。
		  9 只能输入有1~3位小数的正实数："^[0-9]+(.[0-9]{1,3})?$"。
		 10 只能输入非零的正整数："^\+?[1-9][0-9]*$"。
		 11 只能输入非零的负整数："^\-[1-9][]0-9"*$。
		 12 只能输入长度为3的字符："^.{3}$"。
		 13 只能输入由26个英文字母组成的字符串："^[A-Za-z]+$"。
		 14 只能输入由26个大写英文字母组成的字符串："^[A-Z]+$"。
		 15 只能输入由26个小写英文字母组成的字符串："^[a-z]+$"。
		 16 只能输入由数字和26个英文字母组成的字符串："^[A-Za-z0-9]+$"。
		 17 只能输入由数字、26个英文字母或者下划线组成的字符串："^\w+$"。
		 18 验证用户密码："^[a-zA-Z]\w{5,17}$"正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线。
		 19 验证是否含有^%&',;=?$\"等字符："[^%&',;=?$\x22]+"。
		 20 只能输入汉字："^[\u4e00-\u9fa5]{0,}$"
		 21 验证Email地址："^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"。
		 22 验证InternetURL："^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$"。
		 23 验证电话号码："^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$"正确格式为："XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX"。
		 24 验证身份证号（15位或18位数字）："^\d{15}|\d{18}$"。
		 25 验证一年的12个月："^(0?[1-9]|1[0-2])$"正确格式为："01"～"09"和"1"～"12"。
		 26 验证一个月的31天："^((0?[1-9])|((1|2)[0-9])|30|31)$"正确格式为；"01"～"09"和"1"～"31"。
		 27 匹配中文字符的正则表达式： [\u4e00-\u9fa5]
		 28 匹配双字节字符(包括汉字在内)：[^\x00-\xff]
		 29 应用：计算字符串的长度（一个双字节字符长度计2，ASCII字符计1）
		 30 String.prototype.len=function(){return this.replace(/[^\x00-\xff]/g,"aa").length;}
		 31 匹配空行的正则表达式：\n[\s| ]*\r
		 32 匹配html标签的正则表达式：<(.*)>(.*)<\/(.*)>|<(.*)\/>
		 33 匹配首尾空格的正则表达式：(^\s*)|(\s*$)
		 */
		
		Pattern pattern;
		Matcher matcher;
		
//	    验证邮政编码  
		pattern = Pattern.compile("[0-9]{6}"); //  "//d{6}"  也可以
		matcher = pattern.matcher("200038");
        System.out.println(matcher.matches());  
        
        
//		验证电话号码  
		pattern = Pattern.compile("[0-9]{3,4}//-?[0-9]+"); //  "//d{6}"  也可以
		matcher = pattern.matcher("02178989799");
        System.out.println(matcher.matches()); 
        
		
//		比如，在字符串包含验证时
		//查找以Java开头,任意结尾的字符串
		pattern = Pattern.compile("^Java.*");
		matcher = pattern.matcher("Java不是人");
		boolean b= matcher.matches();
		//当条件满足时，将返回true，否则返回false
		System.out.println(b);


//		以多条件分割字符串时
		pattern = Pattern.compile("[, |]+");
		String[] strs = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for (int i=0;i<strs.length;i++) {
		    System.out.println(strs[i]);
		}

//		文字替换（首次出现字符）
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		//替换第一个符合正则的数据
		System.out.println(matcher.replaceFirst("Java"));

//		文字替换（全部）
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		//替换第一个符合正则的数据
		System.out.println(matcher.replaceAll("Java"));


//		文字替换（置换字符）
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
		    matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());

		
//		验证是否为邮箱地址
		String str="ceponline@yahoo.com.cn";
		pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+",Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(str);
		System.out.println(matcher.matches());

//		去除html标记
		pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
		String string = matcher.replaceAll("");
		System.out.println(string);

		
//		查找html中对应条件字符串
		pattern = Pattern.compile("href=\"(.+?)\"");
		matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
		if(matcher.find()){
		  System.out.println(matcher.group(1));
		}


//		截取http://地址
		//截取url
		pattern = Pattern.compile("(http://|https://){1}[//w//.//-/:]+");
		matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){             
		    buffer.append(matcher.group());       
		    buffer.append("/r/n");             
		System.out.println(buffer.toString());
		}
	}

}
