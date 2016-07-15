package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.traning.task8.dao.ApplicantDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.Applicant;
import by.traning.task8.domain.role.Role;
import by.traning.task8.domain.type.ContactPhoneType;
import by.traning.task8.domain.type.MilitaryType;

public class ApplicantDAOImpl implements ApplicantDAO {

	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_APPLICANT = "INSERT INTO applicant (email, password, surname, name, secondName, photo, work_phone, home_phone, mob_phone, contact_phone, skype, birth_date, millatry, role)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_APPLICANT = "UPDATE applicant"
			+ "SET email=?, password=?, surname=?, name=?, secondName=?, photo=?, work_phone=?, home_phone=?, mob_phone=?, contact_phone=?, skype=?, birth_date=?, millatry=?"
			+ "WHERE email=?";
	private static final String SQL_FIND_APPLICANT_BY_EMAIL_PASS = "SELECT email FROM applicant WHERE email=? and password=?";
	private static final String SQL_FIND_APPLICANT_BY_EMAIL = "SELECT email FROM applicant WHERE email=?";

	@Override
	public boolean create(Applicant entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_APPLICANT);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.setString(3, entity.getSurname());
			ps.setString(4, entity.getName());
			ps.setString(5, entity.getSecondName());
			ps.setBytes(6, entity.getPhoto());
			ps.setInt(7, entity.getWorkPhone());
			ps.setInt(8, entity.getHomePhone());
			ps.setInt(9, entity.getMobPhone());
			ps.setString(10, entity.getContactPhone().getContactPhoneType());
			ps.setString(11, entity.getSkype());
			ps.setDate(12, new Date(entity.getBirthDate().getTime()));
			ps.setString(13, entity.getMilitary().getMillatryType());
			ps.setString(14, entity.getRole().getRole());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild insert into Applicant: ", e);
			throw new DAOException("Faild insert into Applicant: ", e);
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
	public void update(Applicant entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_APPLICANT);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.setString(3, entity.getSurname());
			ps.setString(4, entity.getName());
			ps.setString(5, entity.getSecondName());
			ps.setBytes(6, entity.getPhoto());
			ps.setInt(7, entity.getWorkPhone());
			ps.setInt(8, entity.getHomePhone());
			ps.setInt(9, entity.getMobPhone());
			ps.setString(10, entity.getContactPhone().getContactPhoneType());
			ps.setString(11, entity.getSkype());
			ps.setDate(12, new Date(entity.getBirthDate().getTime()));
			ps.setString(13, entity.getMilitary().getMillatryType());
			ps.setString(14, entity.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild to update Applicant: ", e);
			throw new DAOException("Faild insert into Applicant: ", e);
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
	public Applicant findApplicantByLoginPassword(String email, String password)
			throws DAOException, DataDoesNotExistException {
		Applicant applicant = new Applicant();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_APPLICANT_BY_EMAIL_PASS);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				applicant = getApplicantFromresultSet(rs);
			} else {
				throw new DataDoesNotExistException("Applicant not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Applicant: ", e);
			throw new DAOException("Faild ifind Applicant: ", e);
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

		return applicant;

	}

	@Override
	public Applicant findApplicantByLogin(String email) throws DAOException, DataDoesNotExistException {
		Applicant applicant = new Applicant();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_APPLICANT_BY_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				applicant = getApplicantFromresultSet(rs);
			} else {
				throw new DataDoesNotExistException("Applicant not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Applicant: ", e);
			throw new DAOException("Faild ifind Applicant: ", e);
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

		return applicant;

	}

	private Applicant getApplicantFromresultSet(ResultSet set) throws SQLException {
		Applicant applicant = new Applicant();
		applicant.setEmail(set.getString(SQLField.APPLICANT_EMAIL));
		applicant.setPassword(set.getString(SQLField.APPLICANT_PASSWORD));
		applicant.setSurname(set.getString(SQLField.APPLICANT_SURNAME));
		applicant.setName(set.getString(SQLField.APPLICANT_NAME));
		applicant.setSecondName(set.getString(SQLField.APPLICANT_SECONDNAME));
		applicant.setPhoto(set.getBytes(SQLField.APPLICANT_PHOTO));
		applicant.setWorkPhone(set.getInt(SQLField.APPLICANT_WORK_PHONE));
		applicant.setHomePhone(set.getInt(SQLField.APPLICANT_HOME_PHONE));
		applicant.setMobPhone(set.getInt(SQLField.APPLICANT_MOB_PHONE));
		applicant.setContactPhone(ContactPhoneType.valueOf(set.getString(SQLField.APPLICANT_CONTACT_PHONE)));
		applicant.setSkype(set.getString(SQLField.APPLICANT_SKYPE));
		applicant.setBirthDate(set.getDate(SQLField.APPLICANT_BIRTH_DATE));
		applicant.setMilitary(MilitaryType.valueOf(set.getString(SQLField.APPLICANT_MILLATRY)));
		applicant.setRole(Role.valueOf(set.getString(SQLField.APPLICANT_ROLE)));
		return applicant;

	}

}
