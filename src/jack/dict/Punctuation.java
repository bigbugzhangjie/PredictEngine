package jack.dict;

import java.io.File;

public class Punctuation extends Dict {

	public Punctuation(File file, int index, String sep) {
		super(file, index, sep);
	}

	public Dict getNumbers(){
		return null;
	}
	public Dict getTabulars(){
		return null;
	}
	public Dict getUnits(){
		return null;
	}
	public Dict getSeparators(){
		return null;
	}
	public Dict getAll(){
		return null;
	}
	public boolean isAllPuncts(){
		return false;
	}
	/**
	 * trim punctuation at head and tail
	 * @param str
	 * @return
	 */
	public String trim(String str){
		return null;
	}
	/**
	 * remove all the punctuation in given string 
	 * @param str
	 * @return
	 */
	public String removePuncts(String str){
		return null;
	}
	
}
