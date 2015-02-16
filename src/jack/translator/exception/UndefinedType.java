package jack.translator.exception;

import jack.exception.UndefinedException;

public class UndefinedType extends UndefinedException {
	public UndefinedType(){
		super();
	}
	public UndefinedType(String message){
		super(message);
	}
}
