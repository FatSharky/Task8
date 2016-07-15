package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.InterviewMark;

public interface InterviewMarkDAO extends CommonDAO<InterviewMark> {
	void delete(int idMark) throws DAOException;

	List<InterviewMark> fetchInterviewMark(int interviewId) throws DAOException, DataDoesNotExistException;

}
