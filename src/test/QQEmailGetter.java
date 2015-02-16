package test;

import jack.utility.FileTools;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;

public class QQEmailGetter {

	public static void extract(File in,File out) throws Exception {
		Set<String> lines = FileTools.getLineSet(in);
		FileWriter w = new FileWriter(out);
	
		System.out.println("Loaded "+lines.size() +" lines from "+in.getName());
		int perc = Math.max(1, lines.size()/100);
		int lc=0;
		int prog=0;
		
		int c=0;
		for(String l : lines){
			//打印进度栏
			lc++;
			if(lc%perc==0){
				prog++;
				System.out.print("->"+prog+"%");
				if(prog%20==0){
					System.out.println();
				}
			}
			
			l = l.toLowerCase();
			if(l.contains("@qq.com")){
				l = l.substring(0, l.indexOf("@qq.com"));
				if(l.contains("\t")){
					l = l.substring(l.lastIndexOf("\t")+1);
				}
				if(l.contains(" ")){
					l = l.substring(l.lastIndexOf(" ")+1);
				}
//				l = l.replaceAll("@qq.com", "");
				try{
					Long n = Long.parseLong(l);
					w.write(n+"\n");
					c++;
				}catch(Exception e){
					continue;
				}				
			}
		}
		w.close();
		System.out.println();
		System.out.println("Found "+c+" QQ.");
	}

	public static void main(String[] args) throws Exception {
//		// new_csrdb    huishou  买来的email数据
//		File emailfile = new File("/home/bigbug/data/8.8M-emails/snemail-distinct-8.8M.txt");
//		File qqfile = new File("/home/bigbug/data/8.8M-emails/qq-HUISHOU.txt");
		
		// CIF库里的客户邮箱
//		File emailfile = new File("/bigdata/corpus/cif/cif_email.txt");
//		File qqfile = new File("/home/bigbug/data/8.8M-emails/cif-qq.txt");
		
		File emailfile = new File("/home/bigbug/data/官网注册用户/offical-utf8.txt");
		File qqfile = new File("/home/bigbug/data/官网注册用户/offical-QQ.txt");
		
		extract(emailfile,qqfile);

	}
}
