package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.InterviewMark;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface InterviewMarkDAO extends CommonDAO<InterviewMark> {
	void delete(int idMark) throws DAOException;

	List<InterviewMark> fetchInterviewMark(int interviewId) throws DAOException, DataDoesNotExistException;

}
