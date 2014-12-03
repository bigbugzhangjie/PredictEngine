package jack.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
/**
 * 
 * @author bigbug
 * @since Sep 3, 2014
 * @modified Nov 6, 2014
 */
public class FileTools extends FileUtils {
	/**
	 * 统计file中str出现的次数
	 * @param str
	 * @return
	 */
	public static int count(File file,String str){
		int ret = 0;
		BufferedReader br = null;
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				ret += StringTools.count(line, str);
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
	
	/**
	 * 获取file中不重复的行
	 * @param file
	 * @return
	 */
	public static Set<String> getLineSet(File file){
		Set<String> ret = new HashSet<String>();
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
	
	/**
	 * 按顺序读取文件中的每一行
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String> getLineList(File file) throws IOException{
		List<String> ret = new ArrayList<String>();
		BufferedReader br = null;
//		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				ret.add(line);
			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
			// 关闭BufferedReader
			if (br != null) {
//				try {
					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
//		}
		return ret;		
	}
	
	/**
	 * 获取file中的某一列
	 * @param file
	 * @param index	列号，从0开始计数
	 * @param sep	列分隔符
	 * @return
	 */
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
	
	/**
	 * 从file中的每行抽取指定的多列
	 * @param file	目标文件
	 * @param indecies	需要抽取的各个列的index（从0开始计数）
	 * @param sep	列分割符
	 * @param ignore	对index报错的处理： true:跳过该行继续处理下一行；	false:出错列中填入null,以保证List的size与file的行数一致;
	 * @return
	 */
	public static HashMap<Integer,ArrayList<String>> getMultiColumn(File file, ArrayList<Integer> indecies, String sep,boolean ignore) {
		HashMap<Integer,ArrayList<String>> ret = new HashMap<Integer,ArrayList<String>>();
		if(indecies==null || indecies.size()==0){
			return ret;
		}		
		
		int maxIdx = -1;
		for(int i : indecies){
			if(i>maxIdx){
				maxIdx =i;
			}
		}
		
		BufferedReader br = null;
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			int linenum = 0;
			boolean overFlow = false;
			while ((line = br.readLine()) != null) {
				ArrayList<String> tar = new ArrayList<String>();
				overFlow = false;
				String[] cols = line.split(sep);
				for(int i : indecies){
					try{
						tar.add(cols[i]);
					}catch(Exception overflow){
						if (ignore){
							System.err.println("index overflow error in "+linenum+" line.");
							overFlow = true;
							break;
						}else{
							tar.add(null);
						}
					}
				}	
				if(!overFlow){
					ret.put(linenum, tar);
				}
				linenum++;
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
	

	/**
	 * 从file中随机的抽取n行
	 * @param file
	 * @param n
	 * @return
	 */
	public static List<String> sampling(File file,int n){
		return null;
	}
	
	/**
	 * 将source文件中的各个行打乱顺序后写入target文件
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void shuffle(File source,File target) throws IOException{
		List<String> lines = getLineList(source);
	    Collections.shuffle(lines);  
	    writeFile(lines,target,false);
	}
	public static void copy(File source,File target){
		
	}
//	public static void move(File source,File target){
//		
//	}
	/**
	 * 
	 * @param lines	
	 * @param file	要写入的目标文件
	 * @param append	是否以追加的方式写入。true：在已有文件的结尾写入；false：覆盖原文件
	 * @throws IOException 
	 */
	public static void writeFile(List<String> lines,File file,boolean append) throws IOException{
		FileWriter w = new FileWriter(file,append);
		for(String l : lines){
			w.write(l+"\n");
		}
		w.close();		
	}
//	public void mergeFilesBy......
	public static void merge(List<File> files,File target){
		
	}
	public static void cat(List<File> files, File target){
		merge(files, target);
	}
	/**
	 * 递归形式遍历目录，返回目录及子目录下所有文件
	 * @param dir
	 * @return
	 */
	public static List<File> getAllFiles(File dir){
		List<File> ret = new ArrayList<File>();
        File[] fs = dir.listFiles();
	 
	        if (fs == null) {
	            return ret;
	        }
	 
	        for (File file : fs) {
	            if (file.isFile()) {
	                ret.add(file);
	            } else {
	                ret.addAll(getAllFiles(file));
	            }
	        }
		return ret;
	}
	
	/**
	 * 按照关键字kw，过滤目录dir下的文件
	 * @param dir	目标目录
	 * @param kw	关键词
	 * @param type	0:文件名中任何位置包含kw;  1:以kw开头;  2:以kw结尾;  3:以kw开头或结尾; 
	 * @param recursive	 是否递归处理子目录
	 * @return
	 */
	public static List<File> filter(File dir, String kw, int type,boolean recursive) {
		List<File> ret  = new ArrayList<File>();
		List<File> fs = null;
		if (recursive){
			fs = getAllFiles(dir);
		}else{
			fs = new ArrayList<File>();
			for(File f : dir.listFiles()){
				fs.add(f);
			}
		}

		for (File f : fs) {
			String name = f.getName();
			switch (type) { // 1:以kw开头；2:以kw结尾；3:以kw开头或结尾; 4:包含kw
			case 1:
				if (name.startsWith(kw)) {
					ret.add(f);
				}
				break;
			case 2:
				if (name.endsWith(kw)) {
					ret.add(f);
				}
				break;
			case 3:
				if (name.startsWith(kw) || name.endsWith(kw)) {
					ret.add(f);
				}
				break;
			case 0:
				if (name.contains(kw)) {
					ret.add(f);
				}
				break;
			}
		}
		System.out.println("Got "+ret.size()+" files from "+dir.getName());
		return ret;
	}
	/**
	 * 按照关键字kw，过滤目录dir下的文件
	 * @param dir
	 * @param kws
	 * @param type	0:文件名中任何位置包含kw;  1:以kw开头;  2:以kw结尾;  3:以kw开头或结尾; 
	 * @return
	 */
	public static File[] filter(File dir, String kw, int type) {
		final int t = type;
		final String keyword = kw;
		// 因为过滤器FilenameFilter接口中只有一个方法需要实现,所以就用匿名内部类
		return dir.listFiles(
				new FilenameFilter(){
					@Override
					public boolean accept(File file, String name){
						// 这里的file是dir引用所指的对象,name就是需要过滤的内容
						switch (t) { // 1:以kw开头；2:以kw结尾；3:以kw开头或结尾; 4:包含kw
						case 1:
							return name.startsWith(keyword);
						case 2:
							return name.endsWith(keyword);
						case 3:
							return (name.startsWith(keyword) || name.endsWith(keyword));
						case 0:
							return name.contains(keyword);
						default:
							return false;
						}
					}		
				}
		);

	}
	
	
	/**
	 * 将输入文件按照行数分片，存于指定目录中。
	 * @param sourceFile	输入文件
	 * @param targetDir	输出文件所在目录
	 * @param rows	分片文件的最大行数
	 * @return	返回值为分割文件数，出错时返回-1
	 */
	public static int splitByLine(File sourceFile, File targetDir,int rows) {
		int ret=0;
		if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
			System.out.println("源文件不存在或者输入了错误的行数");
			return -1;
		}
		if (targetDir.exists()) {
			if (!targetDir.isDirectory()) {
				System.out.println("目标文件夹错误,不是一个文件夹");
				return -1;
			}
		} else {
			targetDir.mkdirs();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
			BufferedWriter bw = null;
			String str = "";
			String tempData = br.readLine();
			int i = 1;
			while (tempData != null) {
				str += tempData + "\r\n";
				if (i % rows == 0) {
					bw = new BufferedWriter(new FileWriter(new File(
							targetDir.getAbsolutePath() + "/" + ret + "_"
									+ sourceFile.getName())));
					bw.write(str);
					bw.close();
					str = "";
					ret += 1;
				}
				i++;
				tempData = br.readLine();
			}
			if ((i - 1) % rows != 0) {
				bw = new BufferedWriter(new FileWriter(new File(
						targetDir.getAbsolutePath() + "/" + ret + "_"
								+ sourceFile.getName())));
				bw.write(str);
				bw.close();
				br.close();
				ret += 1;
			}
			System.out.println("文件分割结束,共分割成了" + ret + "个文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
		
	public static void main(String[] args) throws Exception{
		// unitest: count()
//		String dir = "E:/corpus/开放数据/微博/";
//		File file = new File(dir+"relsemple.json");//relsemple.json
//		String str = ",";
//		System.out.println(count(file,str));
		
		
//		// unitest: filter()		
//		File directory = new File("/home/bigbug/adt-workspace/vcards");
//		String extendfilename = ".vcf";//".vcf"; "0";
//		int type = 2,i=0;		
//		// 列出所有.vcf文件
//		File[] retfiles = FileTools.filter(directory, extendfilename, type);
//		System.out.println("type:"+ type+"\tnumber:"+retfiles.length);
//		for (File file : retfiles) {
//			i++;
//			System.out.println("\t"+i+":\t" + file.getName());
//		}
		
		
		File in = new File("/home/bigbug/adt-workspace/data/zhangyun/test400");
		File out = new File("/home/bigbug/adt-workspace/data/zhangyun/test400-2");
		shuffle(in,out);
		
		
		System.out.println("Finished!");
	}
}
