package inventarium.helper;

public enum EntityStatus {
	ACTIVE('A'),
	ARCHIVED('X');
	
	private char statusCode;
	
	private EntityStatus(char c){
		statusCode = c;
	}
	
	public char getEntityStatus(){
		return statusCode;
	}
	
	public static EntityStatus getEntityStatus(char c){
		String s = Character.toString(c);
		return EntityStatus.valueOf(s);
	}
}
