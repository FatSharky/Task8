package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.WorkPlace;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface WorkPlaceDAO extends CommonDAO<WorkPlace> {
	void delete(int idWorkPlace) throws DAOException;

	List<WorkPlace> fetchResumeWorkPlace(int resumeId) throws DAOException, DataDoesNotExistException;

}
