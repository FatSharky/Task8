package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.domain.resume.Skill;

public interface SkillDAO extends CommonDAO<Skill>{
	void delete(int idSkill) throws DAOException;

	List<Skill> fetchResumeSkill(int resumeId) throws DAOException, DataDoesNotExistException;

}
