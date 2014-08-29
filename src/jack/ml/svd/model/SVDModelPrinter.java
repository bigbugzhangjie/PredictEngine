package jack.ml.svd.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVDModelPrinter {
	public static void main(String[] args) {
		String dir = "";
		String fn = dir + "0060.model";
		File file = new File(fn);
		try {
			SVDModel model = new SVDModel(file);
			System.out.println(model);
			FileWriter w = new FileWriter(file.toString() + ".txt");
			w.write(model.toString());
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
