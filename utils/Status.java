package inventarium.utils;

/**
 * @author mhoffman
 */

public enum Status {
	ACTIVE('A'),
	ARCHIVED('X');
	
	private char statusCode;
	
	private Status(char c){
		statusCode = c;
	}
	
	public char getEntityStatus(){
		return statusCode;
	}
	
	public static Status getEntityStatus(char c){
		String s = Character.toString(c);
		return Status.valueOf(s);
	}
}
