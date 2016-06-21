package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.SkillDAO;
import by.traning.task8.dao.pull.ConnectionPool;
import by.traning.task8.dao.pull.exception.ConnectionPoolException;
import by.traning.task8.domains.Skill;
import by.traning.task8.domains.type.SkillType;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public class SkillDAOImpl implements SkillDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_SKILL = "INSERT INTO skill (name, raiting, id_resume) "
			+ "VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_SKILL = "DELETE skill WHERE id_skill=?";
	private static final String SQL_UPDATE_SKILL = "UPDATE skill SET  name`=?, raiting=?, WHERE id_skill=?;";
	private static final String SQL_FIND_SKILL_BY_RESUME = "SELECT s.name, s.raiting "
			+ "FROM resume as r JOIN skill as s ON r.id_resume = s.id_resume " + "WHERE r.id_resume=?";

	@Override
	public boolean create(Skill entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_SKILL);
			ps.setString(1, entity.getName());
			ps.setString(3, entity.getRaiting().getSkillType());
			ps.setInt(3, entity.getIdResume());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create Skill: ", e);
			throw new DAOException("Faild create Skill: ", e);
		} catch (ConnectionPoolException e) {
			LOG.error("Connection pool problems!", e);
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
				throw new DAOException("Faild to close connection", e);
			}
		}
	}

	@Override
	public void update(Skill entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_SKILL);

			ps.setString(1, entity.getName());
			ps.setString(2, entity.getRaiting().getSkillType());

			ps.setInt(3, entity.getIdSkill());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild update Skill: ", e);
			throw new DAOException("Faild update Skill: ", e);
		} catch (ConnectionPoolException e) {
			LOG.error("Connection pool problems!", e);
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
				throw new DAOException("Faild to close connection", e);
			}
		}

	}

	@Override
	public void delete(int idSkill) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_SKILL);
			ps.setInt(1, idSkill);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete Skill: ", e);
			throw new DAOException("Faild delete Skill: ", e);
		} catch (ConnectionPoolException e) {
			LOG.error("Connection pool problems!", e);
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
				throw new DAOException("Faild to close connection", e);
			}
		}

	}

	@Override
	public List<Skill> fetchResumeSkill(int resumeId) throws DAOException, DataDoesNotExistException {
		List<Skill> skill = new ArrayList<Skill>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_SKILL_BY_RESUME);
			ps.setInt(1, resumeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				skill.add(CreateEntity(rs));
			} else {
				throw new DataDoesNotExistException("Company not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Languages: ", e);
			throw new DAOException("Faild to find Languages: ", e);
		} catch (ConnectionPoolException e) {
			LOG.error("Connection pool problems!", e);
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps, rs);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
				throw new DAOException("Faild to close connection", e);
			}
		}
		return skill;
	}

	private Skill CreateEntity(ResultSet set) throws SQLException {
		Skill skill = new Skill();
		skill.setIdSkill(set.getInt(1));
		skill.setName(set.getString(2));
		skill.setRaiting(SkillType.valueOf(set.getString(3)));
		skill.setIdResume(set.getInt(4));
		return skill;

	}
}
