package by.traning.task8.dao;

import by.traning.task8.domains.Company;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface CompanyDAO extends CommonDAO<Company> {
	Company findCompanyByLoginPass(String login, String pass) throws DAOException, DataDoesNotExistException;

	Company findCompanyByLogin(String login) throws DAOException, DataDoesNotExistException;

}
