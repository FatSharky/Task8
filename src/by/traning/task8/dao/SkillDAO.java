package by.traning.task8.dao;

import java.util.List;

import by.traning.task8.domains.Skill;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public interface SkillDAO extends CommonDAO<Skill>{
	void delete(int idSkill) throws DAOException;

	List<Skill> fetchResumeSkill(int resumeId) throws DAOException, DataDoesNotExistException;

}
