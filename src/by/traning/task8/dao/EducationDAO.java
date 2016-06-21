package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.Education;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface EducationDAO extends CommonDAO<Education> {
	void delete(int idEducation) throws DAOException;

	List<Education> fetchResumeEducation(int resumeId) throws DAOException, DataDoesNotExistException;

}
