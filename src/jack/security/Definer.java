package jack.security;

import jack.exception.FileFormatException;
import jack.security.datatype.BOOLEAN;
import jack.security.datatype.DATE;
import jack.security.datatype.FLOAT;
import jack.security.datatype.INT;
import jack.security.datatype.NOMINAL;
import jack.security.datatype.ORDINAL;
import jack.security.datatype.STRING;
import jack.security.datatype.SuperType;
import jack.security.datatype.SupportedType;
import jack.security.exception.UndefinedType;
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
	List<SuperType> typelist = new ArrayList<SuperType>();
	
	public Definer(){
		
	}
	public void setName(String name){
		this.casename = name;
	}
	public void loadTypeList(File f) throws IOException, FileFormatException, UndefinedType{
		this.typelist = loadTypeDefinition(f);
	}
	public void setTypeList(List<SuperType> type) {
		this.typelist = type;
	}
	public int getSize(){
		return typelist.size();
	}
	public SuperType getType(int idx){
		return typelist.get(idx);
	}
	
	/**
	 * 从文件中读取数据类型的定义，定义文件中行的顺序要与数据文件中列的顺序一致；
	 * 已#开头的行认为是注释，跳过；
	 * column_name \t type_name \t aaa \t bbb \tccc
	 * 例：   sex  NONIMAL  female  male
	 * @param file
	 * @throws IOException 
	 * @throws FileFormatException 
	 * @throws UndefinedType 
	 */
	public static List<SuperType> loadTypeDefinition(File file)
			throws IOException, FileFormatException, UndefinedType {
		List<String> lines = FileTools.getLineList(file);
		List<SuperType> ret = new ArrayList<SuperType>();

		for (String line : lines) {
			if (line.startsWith("#")) {
				continue;
			}
			SuperType type = null;
			String[] cols = line.split("\t");
			if (cols.length < 2) {
				throw new FileFormatException();
			}

			String colname = cols[0];
			String typename = cols[1];

			ArrayList<String> params = new ArrayList<String>();
			if (cols.length >= 3) {
				for (int j = 2; j < cols.length; j++) {
					params.add(cols[j]);
				}
			}

			switch (typename) {
			case SupportedType.BOOLEAN:
				type = new BOOLEAN(colname);
				break;
			case SupportedType.DATE:
				if(params.size()>0){
					type = new DATE(colname,params.get(0));
				}else{
					type = new DATE(colname);
				}
				break;
			case SupportedType.FLOAT:
				type = new FLOAT(colname);
				break;
			case SupportedType.INT:
				type = new INT(colname);
				break;
			case SupportedType.NOMINAL:
				if (params.size() == 0) {
					throw new FileFormatException(
							"Need all NOMINAL values for column: " + colname);
				}
				type = new NOMINAL(colname, params);
				break;
			case SupportedType.ORDINAL:
				if (params.size() == 0) {
					throw new FileFormatException(
							"Need all NOMINAL values for column: " + colname);
				}
				type = new ORDINAL(colname, params);
				break;
			case SupportedType.STRING:
				type = new STRING(colname);
				break;
			default:
				throw new UndefinedType(typename);
			}

			ret.add(type);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
