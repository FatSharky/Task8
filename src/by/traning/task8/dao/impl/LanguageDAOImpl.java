package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.LanguageDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.resume.Language;
import by.traning.task8.domain.type.LanguageLevelType;

public class LanguageDAOImpl implements LanguageDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_LANGUAGE = "INSERT INTO language (id_language, name, raiting, id_resume) "
			+ "VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_LANGUAGE = "DELETE language WHERE id_language=?";
	private static final String SQL_UPDATE_LANGUAGE = "UPDATE language SET name=?, raiting=? WHERE id_language=?";
	private static final String SQL_FIND_LANGUAGE_BY_RESUME = "SELECT l.name, l.raiting "
			+ "FROM resume as r JOIN language as l ON r.id_resume = l.id_resume " + "WHERE r.id_resume=?";

	@Override
	public boolean create(Language entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_LANGUAGE);
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getRaiting().getLanguageLevelType());
			ps.setInt(3, entity.getIdResume());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create Language: ", e);
			throw new DAOException("Faild create Language: ", e);
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
	public void update(Language entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_LANGUAGE);

			ps.setString(1, entity.getName());
			ps.setString(2, entity.getRaiting().getLanguageLevelType());

			ps.setInt(3, entity.getIdLanguage());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild update Language: ", e);
			throw new DAOException("Faild update Language: ", e);
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
	public void delete(int idLanguage) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_LANGUAGE);
			ps.setInt(1, idLanguage);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete Language: ", e);
			throw new DAOException("Faild delete Language: ", e);
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
	public List<Language> fetchResumeLanguage(int resumeId) throws DAOException, DataDoesNotExistException {
		List<Language> language = new ArrayList<Language>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_LANGUAGE_BY_RESUME);
			ps.setInt(1, resumeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				language.add(getLanguageFromResultSet(rs));
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
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
				throw new DAOException("Faild to close connection", e);
			}
		}
		return language;
	}

	private Language getLanguageFromResultSet(ResultSet set) throws SQLException {
		Language language = new Language();
		language.setIdLanguage(set.getInt(SQLField.LANGUAGE_ID));
		language.setName(set.getString(SQLField.LANGUAGE_NAME));
		language.setRaiting(LanguageLevelType.valueOf(set.getString(SQLField.LANGUAGE_RAITING)));
		language.setIdResume(set.getInt(SQLField.LANGUAGE_ID_RESUME));
		return language;

	}
}
