package jack.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

	public static void main(String[] args) {
		// test: count(String str, String sub)
		// finished!
		String str = "aaaaaa";
		String sub = "aaaa";
		System.out.println(count(str,sub));

	}
	

}
