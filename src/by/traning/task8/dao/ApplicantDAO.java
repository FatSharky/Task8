package by.traning.task8.dao;

import by.traning.task8.domains.Applicant;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface ApplicantDAO extends CommonDAO<Applicant> {

	Applicant findApplicantByLoginPassword(String email, String password) throws DAOException, DataDoesNotExistException;

	Applicant findApplicantByLogin(String email) throws DAOException, DataDoesNotExistException;
}
