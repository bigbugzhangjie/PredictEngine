package jack.example;

import jack.utility.FileTools;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws Exception {
//		File cif = new File("/home/bigbug/data/8.8M-emails/cif-qq-2.47M.txt");
//		File huishou =new File("/home/bigbug/data/8.8M-emails/qq-HUISHOU-80W.txt");
//		Set<String> qq_cif = FileTools.getLineSet(cif);
//		Set<String> qq_huishou = FileTools.getLineSet(huishou);
//		
//		int c=0;
//		for(String qq : qq_cif){
//			if(qq_huishou.contains(qq)){
//				c++;			
//			}
//		}
//		System.out.println("Found "+c+" QQ");
		
		String str = "宗晓蓓                  ";
		str = str.trim();
		System.out.println(str);
		System.out.println(str.length());
	}

}
