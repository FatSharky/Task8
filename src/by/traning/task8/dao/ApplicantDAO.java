package by.traning.task8.dao;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.Applicant;

public interface ApplicantDAO extends CommonDAO<Applicant> {

	Applicant findApplicantByLoginPassword(String email, String password) throws DAOException, DataDoesNotExistException;

	Applicant findApplicantByLogin(String email) throws DAOException, DataDoesNotExistException;
}
