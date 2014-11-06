package jack.ml.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FeatureMap {
	Map<Integer,String> idx2name;
	Map<String,Integer> name2idx;
	
	public FeatureMap(File file,int idxOfName,int idxOfIndex,String sep,int offset){
		BufferedReader br = null;
		int idxError = 0;
		int intFormatError =0;
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(sep);
				if(cols.length< Math.max(idxOfName, idxOfIndex)){
					idxError++;
					continue;
				}
				try{
				int idx = Integer.parseInt(cols[idxOfIndex]);
				idx +=offset;
				String name = cols[idxOfName];
				idx2name.put(idx,name);
				name2idx.put(name, idx);
				}catch(Exception ee){
					intFormatError++;
					continue;
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
		if((idxError+intFormatError)>0){
			System.out.println("found "+idxError+" index overflow errors and "+intFormatError+" Integer format errors.");
		}
	}
	public int getIndex(String featureName){
		return name2idx.get(featureName);
	}
	public String getName(int featureIndex){
		return idx2name.get(featureIndex);
	}

}
