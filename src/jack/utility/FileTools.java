package jack.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class FileTools extends FileUtils {
	
	public static List<String> getLines(File file){
		List<String> ret = new ArrayList<String>();
		BufferedReader br = null;
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				ret.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭BufferedReader
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;		
	}
	
	public static Set<String> getColumnSet(File file, int index, String sep) {
		return new HashSet<String>(getColumnList(file,index,sep,true));
	}
	
	/**
	 * 从文件中选取指定的某一列，存入List中。
	 * @param file
	 * @param index	目标列的序号，从0开始计数
	 * @param sep	列分隔符
	 * @param ignore	对index报错的处理：
	 * 		true:跳过该行继续处理下一行； 
	 * 		false:结果List中填入null,以保证List的size与file的行数一致;
	 * @return
	 */
	public static List<String> getColumnList(File file, int index, String sep,boolean ignore) {
		List<String> ret = new ArrayList<String>();
		BufferedReader br = null;

		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(sep);
				if(cols.length>=(index+1)){
					String col = cols[index];
					ret.add(col);
				}else{
//					 //将出错line文本打印到控制台
					System.err.println("index overflow error: "+line);
					if (!ignore){
						ret.add(null);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭BufferedReader
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
}
