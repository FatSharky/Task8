package by.traning.task8.dao.exception;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(String msg, Exception e) {
		super(msg, e);
	}
}
