package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.InterviewMarkDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.InterviewMark;
import by.traning.task8.domain.type.SkillType;

public class InterviewMarkDAOImpl implements InterviewMarkDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_SKILL = "INSERT INTO interview_mark (id_mark, skill, mark, id_interview) VALUES (?, ?, ?, ?);";
	private static final String SQL_DELETE_SKILL = "DELETE interview_mark WHERE id_mark=?";
	private static final String SQL_UPDATE_SKILL = "UPDATE interview_mark SET  skill=?,  mark=?, WHERE id_mark=?;";
	private static final String SQL_FIND_SKILL_BY_INTERVIEW = "SELECT m.skill, m.mark "
			+ "FROM interview as i JOIN interview_mark as m ON i.id_interview = m.id_interview "
			+ "WHERE id_interview=?";

	@Override
	public boolean create(InterviewMark entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_SKILL);
			ps.setInt(1, entity.getIdMark());
			ps.setString(2, entity.getSkill());
			ps.setString(3, entity.getMark().getSkillType());
			ps.setInt(4, entity.getIdInterview());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create Interview mark: ", e);
			throw new DAOException("Faild create Interview mark: ", e);
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
	public void update(InterviewMark entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_SKILL);

			ps.setString(1, entity.getSkill());
			ps.setString(2, entity.getMark().getSkillType());

			ps.setInt(3, entity.getIdMark());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild update Interview mark: ", e);
			throw new DAOException("Faild update Interview mark: ", e);
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
	public void delete(int idMark) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_SKILL);
			ps.setInt(1, idMark);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete mark: ", e);
			throw new DAOException("Faild delete mark: ", e);
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
	public List<InterviewMark> fetchInterviewMark(int interviewId) throws DAOException, DataDoesNotExistException {
		List<InterviewMark> skill = new ArrayList<InterviewMark>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_SKILL_BY_INTERVIEW);
			ps.setInt(1, interviewId);
			rs = ps.executeQuery();
			if (rs.next()) {
				skill.add(getMarkFromResultSet(rs));
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

	private InterviewMark getMarkFromResultSet(ResultSet set) throws SQLException {
		InterviewMark skill = new InterviewMark();
		skill.setIdMark(set.getInt(SQLField.IMARK_ID));
		skill.setSkill(set.getString(SQLField.IMARK_SKILL));
		skill.setMark(SkillType.valueOf(set.getString(SQLField.IMARK_MARK)));
		skill.setIdInterview(set.getInt(SQLField.IMARK_ID_INERVIEW));
		return skill;

	}

}
