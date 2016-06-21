package by.traning.task8.dao;

import java.util.List;
import by.traning.task8.domains.Vacancy;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface VacancyDAO extends CommonDAO<Vacancy> {

	void delete(int idVacancy) throws DAOException;

	Vacancy findVacancyByComany(String companyLogin) throws DAOException, DataDoesNotExistException;

	Vacancy findVacancyByHr(String hrEmail) throws DAOException, DataDoesNotExistException;

	Vacancy findVacancyById(int idVacancy) throws DAOException, DataDoesNotExistException;

	List<Vacancy> findAllVacancy() throws DAOException, DataDoesNotExistException;

	List<Vacancy> findVacancyLike(String name) throws DAOException, DataDoesNotExistException;

}
