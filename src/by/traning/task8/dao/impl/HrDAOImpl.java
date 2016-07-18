package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.HrDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.Hr;
import by.traning.task8.domain.role.Role;

public class HrDAOImpl implements HrDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_HR = "INSERT INTO hr(email, password, photo, surname, name, secondname, phone, company_login, role) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_HR = "UPDATE hr SET email=?, password=?, photo=?, surname=?, name=?, secondname=?, phone=? "
			+ "WHERE email=?;";
	private static final String SQL_DELETE_HR = "DELETE hr WHERE email=?";
	private static final String SQL_FETCH_HR_BY_EMAIL_PASS = "SELECT email FROM hr WHERE email=? and password=?";
	private static final String SQL_FETCH_HR_BY_COMPANY = "SELECT hr.mail, hr.photo, hr.surname, hr.name, hr.secondname, hr.phone "
			+ "FROM hr JOIN company as c ON hr.company_login = c.company_login " + "WHERE c.company_login=?";
	private static final String SQL_FETCH_HR_BY_EMAIL = "SELECT email,photo,surname,name,secondname,phone FROM hr WHERE email=?";

	@Override
	public boolean create(Hr entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_HR);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.setBytes(3, entity.getPhoto());
			ps.setString(4, entity.getSurname());
			ps.setString(5, entity.getName());
			ps.setString(6, entity.getSecondName());
			ps.setInt(7, entity.getPhone());
			ps.setString(8, entity.getCompanyLogin());
			ps.setString(9, entity.getRole().getRole());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException("Faild createHr: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}
	}

	@Override
	public void update(Hr entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_HR);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.setBytes(3, entity.getPhoto());
			ps.setString(4, entity.getSurname());
			ps.setString(5, entity.getName());
			ps.setString(6, entity.getSecondName());
			ps.setInt(7, entity.getPhone());
			ps.setString(8, entity.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to update Hr: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}
	}

	@Override
	public void delete(String email) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_HR);
			ps.setString(1, email);
			ps.executeQuery();
		} catch (SQLException e) {
			throw new DAOException("Faild delete Hr: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}

	}

	@Override
	public Hr findHrByEmailPass(String email, String pass) throws DAOException, DataDoesNotExistException {
		Hr hr = new Hr();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FETCH_HR_BY_EMAIL_PASS);
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				hr = getHrFromResultSet(rs);
			} else {
				throw new DataDoesNotExistException("Applicant not found!");
			}
		} catch (SQLException e) {
			throw new DAOException("Faild ifind Applicant: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps, rs);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}

		return hr;

	}

	@Override
	public Hr findHrByEmail(String email) throws DAOException, DataDoesNotExistException {
		Hr hr = new Hr();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FETCH_HR_BY_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				hr = getHrFromResultSet(rs);
			} else {
				throw new DataDoesNotExistException("Applicant not found!");
			}
		} catch (SQLException e) {
			throw new DAOException("Faild ifind Applicant: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps, rs);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}

		return hr;

	}

	@Override
	public List<Hr> fetchCompanyHr(String loginCompany) throws DAOException, DataDoesNotExistException {
		List<Hr> hr = new ArrayList<Hr>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FETCH_HR_BY_COMPANY);
			ps.setString(1, loginCompany);
			rs = ps.executeQuery();
			if (rs.next()) {
				hr.add(getHrFromResultSet(rs));
			} else {
				throw new DataDoesNotExistException("Company not found!");
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find Company: ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool problems!", e);
		} finally {
			try {
				ConnectionPool.getInstance().closeConnection(conn, ps, rs);
			} catch (ConnectionPoolException e) {
				LOG.error("Faild to close connection", e);
			}
		}

		return hr;

	}

	private Hr getHrFromResultSet(ResultSet set) throws SQLException {
		Hr hr = new Hr();
		hr.setEmail(set.getString(SQLField.HR_EMAIL));
		hr.setPassword(set.getString(SQLField.HR_PASSWORD));
		hr.setPhoto(set.getBytes(SQLField.HR_PHOTO));
		hr.setSurname(set.getString(SQLField.HR_SURNAME));
		hr.setName(set.getString(SQLField.HR_NAME));
		hr.setSecondName(set.getString(SQLField.HR_SECOND_NAME));
		hr.setPhone(set.getInt(SQLField.HR_PHONE));
		hr.setCompanyLogin(set.getString(SQLField.HR_COMPANY_LOGIN));
		hr.setRole(Role.valueOf(set.getString(SQLField.HR_ROLE)));
		return hr;

	}

}
