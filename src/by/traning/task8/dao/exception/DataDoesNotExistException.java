package by.traning.task8.dao.exception;

public class DataDoesNotExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataDoesNotExistException(String msg, Exception e) {
		super(msg, e);
	}

	public DataDoesNotExistException(String msg) {
		super(msg);
	}

}
