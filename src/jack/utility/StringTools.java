package jack.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author bigbug
 * @since Sep 3, 2014
 * @modified Oct 13, 2014
 */
public class StringTools {
    


	/**
	 * 统计字符串str中子字符串sub的出现次数;	 
	 * 如果str或者sub的长度为0,或者为null，则返回0;
	 * [注]:每匹配到一个，则截掉已匹配字串，如：str=aaaa,sub=aaa,返回值为1,而不是2
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int count(String str, String sub) {
		if(sub==null || sub.length()<=0){
			return 0;
		}
		if(str==null || str.length()<=0){
			return 0;
		}
		if(str.contains(sub)){
			int idx = str.indexOf(sub);
			if(idx<0){
				return 0;
			}
			String remain = str.substring(idx+sub.length());
			return 1+count(remain,sub);
		}else{
			return 0;
		}		
	}

	/**
	 * 统计字符串str中字符c出现的次数
	 * @param str
	 * @param c
	 * @return
	 */
	public static int counter(String str, char c) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}
	
    /** 
     * 中文提取 
     * @param str  
     */  
    public static String extractChinese(String str){  
    	String ret="";
        String regex = "[//u4E00-//u9FFF]+";	//[//u4E00-//u9FFF]为汉字   
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        ret=sb.toString();
        return ret;
    } 
	
	public static int getTermCount(Collection<String> dict, String term){
		return 0;
	}
	
	/**
	 * 
	 * @param sentence
	 * @return
	 */
	public static List<String> chunk(String sentence){
		List<String> ret = new ArrayList<String>();
		return ret;
	}
	/**
	 * 以多种字符分割字符串
	 * @param sep	除空格外，其他的分割符
	 * @param str	待分割的字符串
	 * @return
	 */
	public static String[] split(String sep,String str){
	    Pattern pattern = Pattern.compile("["+sep+" ]+");   
	    String[] strs = pattern.split(str);
	    return strs;
//	    for (int i=0;i<strs.length;i++) {   
//	      System.out.println(strs[i]);   
//	    } 
	}	
	/**
	 * 以多种字符分割字符串，分割符包括： ,， 空格 |
	 * 注：重复的分割符都会被去掉
	 */
	public static String[] split(String str){
	    Pattern pattern = Pattern.compile("[,， |]+");   
	    String[] strs = pattern.split(str);
	    return strs;
//	    for (int i=0;i<strs.length;i++) {   
//	      System.out.println(strs[i]);   
//	    } 
	}
	
	public static String trim(String str){
		String ret = str;
		ret = ret.trim();
		//TODO add other logic below
		return ret;
	}
	public static String[] trim(String[] str){
		String[] ret = new String[]{};
		for(int i=0; i<str.length;i++){
			ret[i] = trim(str[i]);
		}
		return ret;
	}
	
	/**
	 * 对pos中指定位置的字符，用m代替。如：13987654321->139****4321
	 * @param in
	 * @param m	 例“*”
	 * @param pos	需要替换的字符所在的位置（从0计数，小于0忽略，大于字符串长度也忽略）
	 * @return
	 */
	public static String replace(String in, char m,int[] pos){
		if(pos==null || pos.length<=0){
			return in;
		}
		String ret = "";
		char[] ca = in.toCharArray();
		int len = ca.length;
		for(int i=0;i<pos.length;i++){
			int idx = pos[i];
			if(idx<len && idx>=0){
				ca[idx]=m;
			}
		}
		for(int i=0;i<len;i++){
			ret +=ca[i];
		}		
		return ret;
	}

	public static void main(String[] args) {
//		// test: count(String str, String sub)
//		String str = "aaaaaa";
//		String sub = "aaaa";
//		System.out.println(count(str,sub));
		
//		//replace(String in, char m,int[] pos)
//		String in = "13987654321";
//		char m = '*';
//		int[] p = {3,8,5,0};
//		System.out.println(replace(in,m,p));

	}
	

}
