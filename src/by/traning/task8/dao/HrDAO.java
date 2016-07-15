package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.Hr;

public interface HrDAO extends CommonDAO<Hr> {

	Hr findHrByEmailPass(String email, String pass) throws DAOException, DataDoesNotExistException;

	void delete(String email) throws DAOException;

	List<Hr> fetchCompanyHr(String loginCompany) throws DAOException, DataDoesNotExistException;

	Hr findHrByEmail(String email) throws DAOException, DataDoesNotExistException;

}
