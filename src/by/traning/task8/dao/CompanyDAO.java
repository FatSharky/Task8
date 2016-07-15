package by.traning.task8.dao;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.Company;

public interface CompanyDAO extends CommonDAO<Company> {
	Company findCompanyByLoginPass(String login, String pass) throws DAOException, DataDoesNotExistException;

	Company findCompanyByLogin(String login) throws DAOException, DataDoesNotExistException;

}
