package jack.security.exception;

import jack.exception.UndefinedException;

public class UndefinedFunction extends UndefinedException {
	public UndefinedFunction(){
		super();
	}
	public UndefinedFunction(String message){
		super(message);
	}
}
