package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.Vacancy;

public interface VacancyDAO extends CommonDAO<Vacancy> {

	void delete(int idVacancy) throws DAOException;

	List<Vacancy> findVacancyByComany(String companyLogin) throws DAOException, DataDoesNotExistException;

	List<Vacancy> findVacancyByHr(String hrEmail) throws DAOException, DataDoesNotExistException;

	Vacancy findVacancyById(int idVacancy) throws DAOException, DataDoesNotExistException;

	List<Vacancy> findAllVacancy() throws DAOException, DataDoesNotExistException;

	List<Vacancy> findVacancyLike(String name) throws DAOException, DataDoesNotExistException;

}
