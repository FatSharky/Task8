package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.resume.Language;

public interface LanguageDAO extends CommonDAO<Language> {
	void delete(int idLanguage) throws DAOException;

	List<Language> fetchResumeLanguage(int resumeId) throws DAOException, DataDoesNotExistException;

}
