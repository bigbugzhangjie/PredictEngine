package jack.security;

import jack.ml.data.RawSample;
import jack.security.datatype.SupportedType;
import jack.utility.DateFormatTool;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Traveler {
	Definer definer;
	Collection<RawSample> corpus;
	MappingRule rules = null;
	
	/**
	 * 出于加密的目的，不支持稀疏表示
	 * @param definer
	 * @param corpus
	 */
	public Traveler(Definer definer,Collection<RawSample> corpus){
		this.corpus = corpus;
		this.definer = definer;
	}
	
	/**
	 * 遍历corpus，对各个字段生成映射规则
	 */
	public void fetch(){
		if(corpus == null || corpus.size()==0){
			return;
		}
		//TODO 映射每列
		int len = -1; //总共有多少个字段；
		for(int i=0;i<len;i++){
			Iterator<RawSample> it = corpus.iterator();
			String dt = "";
			while(it.hasNext()){
				RawSample rs = it.next();
				if(dt.length()<1){
					dt = rs.getTypes().get(i);
				}
				
				if(len<=0){
					len = rs.getTypes().size();
				}
				
				String v = rs.getValues().get(i);
				switch(dt){
				case SupportedType.BOOLEAN:
					Boolean b = Boolean.parseBoolean(v);
					break;
				case SupportedType.DATE:
					Date d = DateFormatTool.StrToDate(v);
					break;
					//TODO
				}
				
				
			}
		}
		
		
		// 生成干扰字段
		
		generateRules();
	}
	

	private void generateRules() {
		rules = new MappingRule();
		//TODO
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
