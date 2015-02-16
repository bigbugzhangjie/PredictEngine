package jack.example;

import java.io.File;
import java.io.IOException;

public class TestFilenameFilepath {

	public static void main(String[] args) throws IOException {
		File test = new File("/home/bigbug/workspace/AppCrawler/templates/template.sh");
		System.out.println(test.getPath()); //  /home/bigbug/workspace/AppCrawler/templates/template.sh
		System.out.println(test.getAbsolutePath());// 同上
		System.out.println(test.getName()); //  template.sh
		System.out.println(test.getParent()); //  /home/bigbug/workspace/AppCrawler/templates
		
		System.out.println("==========================");
		test = new File("test/temp.txt");
		System.out.println(test.getPath()); //  test/test.txt
		System.out.println(test.getName()); //  test.txt
		System.out.println(test.getParent()); //  test
		System.out.println(test.getAbsoluteFile().getParent()); // /home/bigbug/workspace/PredictEngine/test
		System.out.println(test.getAbsolutePath());// /home/bigbug/workspace/PredictEngine/test/test.txt
		
		
//		File dir = new File("/home/bigbug/.android/avd");
//		System.out.println(dir.getAbsolutePath());
//		System.out.println(dir.getCanonicalPath());
//		System.out.println(dir.getName());
//		System.out.println(dir.getPath());
		
	}

}
