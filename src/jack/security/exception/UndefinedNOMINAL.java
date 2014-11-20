package jack.security.exception;

import jack.exception.UndefinedException;

public class UndefinedNOMINAL extends UndefinedException {
	public UndefinedNOMINAL(){
		super();
	}
	public UndefinedNOMINAL(String message){
		super(message);
	}
}
