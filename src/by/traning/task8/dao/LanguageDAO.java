package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.Language;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface LanguageDAO extends CommonDAO<Language> {
	void delete(int idLanguage) throws DAOException;

	List<Language> fetchResumeLanguage(int resumeId) throws DAOException, DataDoesNotExistException;

}
