package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.resume.WorkPlace;

public interface WorkPlaceDAO extends CommonDAO<WorkPlace> {
	void delete(int idWorkPlace) throws DAOException;

	List<WorkPlace> fetchResumeWorkPlace(int resumeId) throws DAOException, DataDoesNotExistException;

}
