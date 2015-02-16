package jack.example;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameSelector implements FilenameFilter {
	String extension = ".";

	/**
	 * 
	 * @param fileExtensionNoDot	目标文件的扩展名(不含.)
	 */
	public FileNameSelector(String fileExtensionNoDot) {
		extension += fileExtensionNoDot;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}

	public static void main(String[] args) {
		File directory = new File("/home/bigbug/adt-workspace/vcards");
		String extendfilename = "vcf";
		int i=0;
		
		// 列出所有.vcf文件
		File[] retfiles = directory.listFiles(new FileNameSelector(extendfilename));
		System.out.println("=============所有."+extendfilename+"文件==============");
		i=0;
		for (File file : retfiles) {
			i++;
			System.out.println("\t"+i+":\t" + file.getName());
		}
		
		// 列出所有文件
		File[] files = directory.listFiles();
		System.out.println("目标目录：" + directory.getPath());
		System.out.println("=============所有文件==============");
		i=0;
		for (File file : files) {
			i++;
			System.out.println("\t"+i+":\t" + file.getName());
		}
		
		


	}

}
