package jack.dict;

import java.io.File;

/**
 * 
 * @author bigdata
 *
 */
public class Stopword {
	
	/**
	 * 是否是停用词
	 * @param str
	 * @return
	 */
	public boolean isStopword(String str){
		return false;
	}
	/**
	 * 是否是日期
	 * @param str
	 * @return
	 */
	public boolean isDate(String str){
		return false;
	}
	public boolean isTime(String str){
		return false;
	}
	public boolean isNumber(String str){
		return false;
	}
	public boolean isPunctuation(String str){
		return false;
	}
	public void loadFile(File file){
		
	}
	public void add(String str){
		
	}
}
