package jack.security;

import jack.exception.FileFormatException;
import jack.security.datatype.NOMINAL;
import jack.security.datatype.SuperType;
import jack.security.datatype.SupportedType;
import jack.utility.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 严格按照原始数据各个字段的顺序，命名各个字段的数据类型
 * @author bigbug
 *
 */
public class Definer {
	String casename;
	List<SuperType> type = new ArrayList<SuperType>();
	
	public Definer(){
		
	}
	/**
	 * 从文件中读取数据类型的定义，定义文件中行的顺序要与数据文件中列的顺序一致
	 * column_name \t type_name \t aaa \t bbb \tccc
	 * @param file
	 * @throws IOException 
	 * @throws FileFormatException 
	 */
	public static List<SuperType> loadTypeDefinition(File file) throws IOException, FileFormatException{
		List<String> lines = FileTools.getLineList(file);
		List<SuperType> ret = new ArrayList<SuperType>();
		for(String line : lines){
			SuperType type = null;
			String[] cols = line.split("\t");
			if(cols.length>=2){
				String colname = cols[0];
				String typename = cols[1];
//				String params = cols[2];
				switch(typename){
				case SupportedType.NOMINAL:
					if(cols.length<3){
						throw new FileFormatException("Need all NOMINAL values for column: "+colname);
					}
					ArrayList<String> values = new ArrayList<String>();
					for(int j=2;j<cols.length;j++){
						values.add(cols[j]);
					}
					type = new NOMINAL(colname,values);
					break;
				case SupportedType.BOOLEAN:
					//TODO
					break;
				}
			}else{
				throw new FileFormatException();
			}
			ret.add(type);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
