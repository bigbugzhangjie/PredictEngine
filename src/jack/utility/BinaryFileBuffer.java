package jack.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryFileBuffer {
	public static final int SIZE_BYTE = 1;
	public static final int SIZE_INT = 4;
	public static final int SIZE_FLOAT = 4;
	public static final int BUFF_SIZE = 4096;
	public static final ByteOrder rd = ByteOrder.LITTLE_ENDIAN;
	
	protected ByteBuffer rbuf;
	InputStream is;
	boolean end;
	
	public BinaryFileBuffer(InputStream fis) throws IOException{
		int len;
		byte[] arr = new byte[BUFF_SIZE];
		is = fis;
		len = is.read(arr);
		rbuf = ByteBuffer.wrap(arr,0,len).order(rd);
		end = false;
	}
	
	protected void check(int len){
		int pos = rbuf.position();
		int off = rbuf.limit() - pos;
		int alen;
		byte[] arr,org;
		try{
			if(!end && len > off){
				arr = new byte[len>BUFF_SIZE ? len+BUFF_SIZE : BUFF_SIZE];
				alen = is.read(arr, off, arr.length-off);
				if(off>0){
					org = rbuf.array();
					System.arraycopy(org, pos, arr, 0, off);
				}
				rbuf =  ByteBuffer.wrap(arr, 0, alen).order(rd);
			}
		}catch(IOException e){
			end = true;
			e.printStackTrace();
		}
	}
	
	public boolean end(){
		return end;
	}
	
	public void close(){
		try{
			is.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getInt() {
		check(SIZE_INT);
		return rbuf.getInt();
	}
	
	public byte get(){
		check(SIZE_BYTE);
		return rbuf.get();
	}

	public float getFloat() {
		check(SIZE_FLOAT);
		return rbuf.getFloat();
	}

	public void skip(int len) {
		check(len);
		rbuf.position(rbuf.position()+len);		
	}

}
