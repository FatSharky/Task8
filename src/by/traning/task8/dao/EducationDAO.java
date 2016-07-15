package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.resume.Education;

public interface EducationDAO extends CommonDAO<Education> {
	void delete(int idEducation) throws DAOException;

	List<Education> fetchResumeEducation(int resumeId) throws DAOException, DataDoesNotExistException;

}
