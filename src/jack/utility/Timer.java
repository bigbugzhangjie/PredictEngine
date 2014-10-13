package jack.utility;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author bigbug
 * @since Oct 13, 2014
 * @modified Oct 13, 2014
 */
public class Timer {
	long start;//start timestamp
	long end;//end timestamp
	HashMap<String,Long> records = new HashMap<String,Long>(); //
	ArrayList<String> list = new ArrayList<String>();
	public Timer(){
		this.start = System.currentTimeMillis();
		String st = String.valueOf(start)+"-"+"0";
		records.put(st, start);
		list.add(st);
	}
	
	public void add(){
		String n = String.valueOf(start)+"-"+String.valueOf(records.size());
		add(n);
	}
	public void add(String name){
		records.put(name, System.currentTimeMillis());
		list.add(name);
	}
	public void end(){
		this.end = System.currentTimeMillis();
		String st = String.valueOf(start)+"-"+"end";
		records.put(st, end);
		list.add(st);
	}
	
	public long get(String name){
		if(!records.containsKey(name) || !list.contains(name) ){
			return -1L;
		}
		int idx = list.indexOf(name);
		if(idx<1){
			return -1L;
		}
		String lastname = list.get(idx-1);
		return records.get(name)-records.get(lastname);
	}
	public long get(int phase){
		return records.get(list.get(phase));
	}

	public long getTotalCost(){
		if(end<start){
			end();
		}
		return end-start;
	}
	public static String toString(long time){
		int total = (int)time/1000;
		int hour = Math.floorDiv(total, 3600);
		total = total - 3600*hour;
		int min = Math.floorDiv(total, 60);
		total = total - 60*min;
		String ret = " "+hour+"h"+min+"m"+total+"s";
//		System.out.println(ret);
		return ret;
	}
	public static void print(long t){
		System.out.println(Timer.toString(t));
	}
	public void printAll(){
		for(String s : list){
			System.out.println(s+"\t"+Timer.toString(get(s)));
		}
	}
	public long getStart(){
		return this.start;
	}
	public long getEnd(){
		return this.end;
	}
	public static void main(String[] args) throws InterruptedException{
		Timer t = new Timer();
		Thread.sleep(3000);
		t.add();
		Thread.sleep(2000);
		t.add("phase2");
		Thread.sleep(1000);
//		t.end();
		Timer.print(t.getTotalCost());
		Timer.print(t.get("phase2"));
		t.printAll();
	}
}
