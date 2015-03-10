package jack.utility;

import jack.example.FileNameSelector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class EncodingTools {

	public static final String ENCODE_GBK = "GBK"; // 如果文件编码为ANSI用GBK来读
	public static final String ENCODE_GB2312 = "GB2312";
	public static final String ENCODE_UNICODE = "UNICODE";
	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String ENCODE_UTF16BE = "UTF-16BE";
	public static final String ENCODE_UTF16LE = "UTF-16LE";
	

	private static String getEncoding(File sourceFile) {
		String charset = ENCODE_GBK;
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(sourceFile));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				bis.close();
				charset = ENCODE_GBK;
				return charset; // 文件编码为 ANSI
				
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = ENCODE_UTF16LE; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = ENCODE_UTF16BE; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = ENCODE_UTF8; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = ENCODE_UTF8;
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}

	/**
	 * 以指定编码方式读取文件，返回文件内容
	 * @param file	要转换的文件
	 * @param fromCharsetName	源文件的编码
	 * @return
	 * @throws Exception
	 */
	private static String readFile(String filename, String fromCharsetName)
			throws Exception {
		return readFile(new File(filename),fromCharsetName);
	}
	
	/**
	 * 以指定编码方式读取文件，返回文件内容
	 * @param file	要转换的文件
	 * @param fromCharsetName	源文件的编码
	 * @return
	 * @throws Exception
	 */
	public static String readFile(File file, String encode) {
		String fileContent = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, encode);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			for (String line = br.readLine(); line != null; line = br
					.readLine()) {
				sb.append(line);
			}
			fileContent = sb.toString();
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not exist：" + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	/**
	 * 以指定编码方式写文本文件，存在会覆盖
	 * 
	 * @param file
	 *            要写入的文件
	 * @param toCharsetName
	 *            要转换的编码
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public static void saveFile2Charset(File file, String toCharsetName,
			String content) throws Exception {
		if (!Charset.isSupported(toCharsetName)) {
			throw new UnsupportedCharsetException(toCharsetName);
		}
		OutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter outWrite = new OutputStreamWriter(outputStream,
				toCharsetName);
		outWrite.write(content);
		outWrite.close();
	}

	public static void convertGBK2UTF8(File file) throws Exception {
		convertFileEncode(file, ENCODE_GBK, ENCODE_UTF8);
	}

	public static void convertUTF82GBK(File file) throws Exception {
		convertFileEncode(file, ENCODE_UTF8, ENCODE_GBK);
	}

	public static void convertFileEncode(File file, String fromEncode,
			String toEncode) throws Exception {
		convert(file, fromEncode,toEncode);
	}



	public static String saveFile(String str, String encode, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(str);
			bw.close();
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not exist：" + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}


	/**
	 * 文件编码转换。递归的方式找到目录下所有符合要求的文件，并转码
	 * @param from
	 * @param to
	 * @param directory
	 * @throws Exception 
	 */
	public static void convert(String from,String to, File directory,FilenameFilter filter) throws Exception{
		if (directory.isDirectory()) {
			File[] fileList = null;
			if (filter == null) {
				fileList = directory.listFiles();
			} else {
				fileList = directory.listFiles(filter);
			}
			System.out.println("Found " + fileList.length
					+ " files in directory: " + directory.toString());
			for (File f : fileList) {
				convert(f, from, to);
			}
		} else {
			if (filter == null
					|| filter.accept(directory.getParentFile(), directory.getName())) {
				convert(directory,from, to);
			}
		}
	}
	
	/**
	 * 编码转换（适用于超大文件，不能一次性读入内存的）
	 * @param fromCharset	
	 * @param toCharset	要转换的编码
	 * @param infile	要转换的文件路径
	 * @return
	 * @throws Exception
	 */
	public static void convert(File infile,String fromCharset, String toCharset)
			throws Exception {
		if(fromCharset.equals(toCharset)){
			System.out.println("do nothing: converting from "+fromCharset +" to "+toCharset);
		}
		System.out.println("\t converting file: " + infile.getName());
		File srcFile = infile;
		InputStream in = new FileInputStream(infile);
		BufferedReader br = new BufferedReader(new InputStreamReader(in, fromCharset));
		StringBuffer sb = new StringBuffer();
		
		File newfile = new File(infile.getAbsolutePath()+"-utf8.tmp");
		newfile.createNewFile();
		OutputStream out = new FileOutputStream(newfile);
		OutputStreamWriter writer = new OutputStreamWriter(out, toCharset);
		BufferedWriter bw = new BufferedWriter(writer);
		
		String line;
		int lineCounter=0, counter=0;
		
		while ((line = br.readLine()) != null) {
			lineCounter++;
			counter++;
			if(counter%100000==0){
				System.out.println("\t\t\tread "+lineCounter+" lines.");
				// 重新以新编码写入文件并返回值
				bw.write(URLDecoder.decode(sb.toString(), toCharset));
				bw.flush();// 刷到文件中		
				// 复位
				counter=0;
				sb = new StringBuffer();				
			}
			String s = URLEncoder.encode(line, toCharset);
			sb.append(s + "\r\n");// 一行+回车			

		}
		bw.close();
		br.close();
		srcFile.delete();// 删除原来文件
		newfile.renameTo(infile);
		System.out.println("\t\t\tread "+lineCounter+" lines.");
		return ;
	}

	public static void main(String[] args) throws Exception {
		String from = "GBK";
		String to = "UTF8";
		
//		File file = new File("/bigdata/corpus/cif/寿险客户-手机信息_11.csv");
//		System.out.println(getEncoding(file));

		// File file = new File("/bigdata/corpus/cif/产险客户-房产信息.csv");
		// convert(file,from,to);

//		// 大文件转码
//		File file = new File("/home/bigbug/temp/客户地址_3.csv");
//		bigfileConvert(from, to, file);
		
//		// 大文件转码
		File dir = new File("/bigdata/corpus/cif/");
//		FilenameFilter filter = new FileNameSelector(".csv");
//		bigfileConvert(from, to, dir, filter);
		File[] list = FileTools.filter(dir, ".csv.bak", 2);
		for(File f:list){
			convert(f,from, to);
		}

	}

}
