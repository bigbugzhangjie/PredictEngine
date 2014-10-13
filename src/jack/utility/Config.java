package jack.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * 
 * @author bigbug
 * @since Oct 13, 2014
 * @modified Oct 13, 2014
 */
public class Config {
	Properties prop;
	public Config(File file){
		this.prop = new Properties();
		try{
			FileInputStream is = new FileInputStream(file);
			prop.load(is);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getValue(String key){
		return prop.get(key).toString();
	}
}
