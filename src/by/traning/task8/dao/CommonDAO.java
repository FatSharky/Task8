package by.traning.task8.dao;


import by.traning.task8.dao.exception.DAOException;

public interface CommonDAO<T> {
	
	boolean create(T entity) throws DAOException;

	void update(T entity) throws DAOException;


}
