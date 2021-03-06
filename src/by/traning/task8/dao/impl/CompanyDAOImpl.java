package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.traning.task8.dao.CompanyDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.Company;
import by.traning.task8.domain.role.Role;

public class CompanyDAOImpl implements CompanyDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_COMPANY = "INSERT INTO company (company_login, password, logo, name, web_site, city, role)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_COMPANY = "UPDATE company SET company_login=?, password=?, logo=?, name=?, web_site=?, city=?"
			+ " WHERE company_login=?";
	private static final String SQL_FIND_BY_LOGIN_AND_PASS = "SELECT company_login FROM company WHERE company_login=? and password=?";
	private static final String SQL_FIND_BY_LOGIN = "SELECT company_login FROM company WHERE company_login=?";

	@Override
	public boolean create(Company entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_COMPANY);
			ps.setString(1, entity.getCompanyLogin());
			ps.setString(2, entity.getPassword());
			ps.setBytes(3, entity.getLogo());
			ps.setString(4, entity.getName());
			ps.setString(5, entity.getWebSite());
			ps.setString(6, entity.getCity());
			ps.setString(7, entity.getRole().getRole());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException("Faild insert into Company: ", e);
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
	public void update(Company entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_COMPANY);
			ps.setString(1, entity.getCompanyLogin());
			ps.setString(2, entity.getPassword());
			ps.setBytes(3, entity.getLogo());
			ps.setString(4, entity.getName());
			ps.setString(5, entity.getWebSite());
			ps.setString(6, entity.getCity());
			ps.setString(7, entity.getCompanyLogin());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Faild update Company: ", e);
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
	public Company findCompanyByLoginPass(String login, String pass) throws DAOException, DataDoesNotExistException {
		Company company = new Company();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASS);
			ps.setString(1, login);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				company = getCompanyFromResultSet(rs);
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

		return company;

	}

	@Override
	public Company findCompanyByLogin(String login) throws DAOException, DataDoesNotExistException {
		Company company = new Company();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_BY_LOGIN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				company = getCompanyFromResultSet(rs);
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

		return company;

	}

	private Company getCompanyFromResultSet(ResultSet set) throws SQLException {
		Company company = new Company();
		company.setCompanyLogin(set.getString(SQLField.COMPANY_LOGIN));
		company.setPassword(set.getString(SQLField.COMPANY_PASSWORD));
		company.setLogo(set.getBytes(SQLField.COMPANY_LOGO));
		company.setName(set.getString(SQLField.COMPANY_NAME));
		company.setWebSite(set.getString(SQLField.COMPANY_SITE));
		company.setCity(set.getString(SQLField.COMPANY_CITY));
		company.setRole(Role.valueOf(set.getString(SQLField.COMPANY_ROLE)));
		return company;

	}

}
