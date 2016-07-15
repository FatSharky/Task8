package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.WorkPlaceDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.resume.WorkPlace;

public class WorkplaceDAOImpl implements WorkPlaceDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_WORKPLACE = "INSERT INTO workplace (company_name, position, date_begin, date_end, id_resume) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_WORKPLACE = "UPDATE workplace SET company_name=?, position=?, date_begin=?, date_end=?"
			+ " WHERE id_workplace=?";
	private static final String SQL_DELETE_WORKPLACE = "DELETE workplace WHERE id_workplace=?";
	private static final String SQL_FIND_WORLPACE_BY_RESUME = "SELECT w.company_name, w.position, w.date_begin, w.date_end "
			+ "FROM resume as r JOIN workplace as w ON r.id_resume = w.id_resume " + "WHERE r.id_resume=?";

	@Override
	public boolean create(WorkPlace entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_WORKPLACE);
			ps.setString(1, entity.getCompanyName());
			ps.setDate(2, new Date(entity.getDateBegin().getTime()));
			ps.setDate(3, new Date(entity.getDateEnd().getTime()));
			ps.setInt(4, entity.getIdResume());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create WorkPlace: ", e);
			throw new DAOException("Faild create WorkPlace: ", e);
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
	public void update(WorkPlace entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_WORKPLACE);
			ps.setString(1, entity.getCompanyName());
			ps.setDate(2, new Date(entity.getDateBegin().getTime()));
			ps.setDate(3, new Date(entity.getDateEnd().getTime()));
			ps.setInt(4, entity.getIdWorkPlace());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild update WorkPlace: ", e);
			throw new DAOException("Faild udpate WorkPlace: ", e);
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
	public void delete(int idWorkPlace) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_WORKPLACE);
			ps.setInt(1, idWorkPlace);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete WorkPlace: ", e);
			throw new DAOException("Faild delete WorkPlace: ", e);
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
	public List<WorkPlace> fetchResumeWorkPlace(int resumeId) throws DAOException, DataDoesNotExistException {
		List<WorkPlace> workPlace = new ArrayList<WorkPlace>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_WORLPACE_BY_RESUME);
			ps.setInt(1, resumeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				workPlace.add(getWorkPlaceFromResultSet(rs));
			} else {
				throw new DataDoesNotExistException("WorkPlace not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find WorpPlace: ", e);
			throw new DAOException("Faild to find WorkPlace: ", e);
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
		return workPlace;
	}

	private WorkPlace getWorkPlaceFromResultSet(ResultSet set) throws SQLException {
		WorkPlace workPlace = new WorkPlace();
		workPlace.setIdWorkPlace(set.getInt(SQLField.WORKPLACE_ID ));
		workPlace.setCompanyName(set.getString(SQLField.WORKPLACE_COMPANY_NAME));
		workPlace.setPosition(set.getString(SQLField.WORKPLACE_POSITION));
		workPlace.setDateBegin(set.getDate(SQLField.WORKPLACE_DATE_BEGIN));
		workPlace.setDateEnd(set.getDate(SQLField.WORKPLACE_DATE_END));
		workPlace.setIdResume(set.getInt(SQLField.WORKPLACE_ID_RESUME));
		return workPlace;

	}

}
