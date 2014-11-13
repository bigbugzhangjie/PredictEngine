package jack.security;

import jack.exception.FileFormatException;
import jack.utility.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converter {

	ArrayList<MappingRule> rules = new ArrayList<MappingRule>();
	
	public static ArrayList<MappingRule> loadRules(File file) throws IOException, FileFormatException{
		List<String> lines = FileTools.getLineList(file);
		ArrayList<MappingRule> ret = new ArrayList<MappingRule>();
		for(String line : lines){
			MappingRule rule = MappingRule.load(line);
			ret.add(rule);
		}		
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
