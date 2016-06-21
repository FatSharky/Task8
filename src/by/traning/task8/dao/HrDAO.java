package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.Hr;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface HrDAO extends CommonDAO<Hr> {

	Hr findHrByEmailPass(String email, String pass) throws DAOException, DataDoesNotExistException;

	void delete(String email) throws DAOException;

	List<Hr> fetchCompanyHr(String loginCompany) throws DAOException, DataDoesNotExistException;

	Hr findHrByEmail(String email) throws DAOException, DataDoesNotExistException;

}
