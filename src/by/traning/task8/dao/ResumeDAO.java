package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.resume.Resume;

public interface ResumeDAO extends CommonDAO<Resume> {

	void delete(int idResume) throws DAOException;
	
	Resume findResumeByApplicant(String email) throws DAOException, DataDoesNotExistException;

	List<Resume> findListAplicantResume(String email) throws DAOException, DataDoesNotExistException;

	void ApplayforJob(int idResume, int idVacancy)throws DAOException;

}
