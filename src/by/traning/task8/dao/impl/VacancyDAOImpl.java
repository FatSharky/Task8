package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.VacancyDAO;
import by.traning.task8.dao.pull.ConnectionPool;
import by.traning.task8.dao.pull.exception.ConnectionPoolException;

import by.traning.task8.domains.Vacancy;
import by.traning.task8.domains.type.CurrencyType;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public class VacancyDAOImpl implements VacancyDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_VACANCY = "INSERT INTO vacancy (name, salary, currency, publish_date, description, requirements, conditions, employment_type, email) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_VACANCY = "UPDATE vacancy SET id_vacancy=?, name=?, salary=?, currency=?, publish_date=?, description=?, requirements=?, conditions=?, employment_type=?, email=? WHERE id_vacancy=?";
	private static final String SQL_DELETE_VACANCY = "DELETE vacancy WHERE id_vacancy=?";
	private static final String SQL_FIND_VACACY_BY_COMPANY = "SELECT name, salary, currency, publish_date, description, requirements, conditions, employment_type "
			+ "FROM vacancy as v JOIN hr ON v.email = hr.email JOIN company as c ON hr.company_login= c.company_login "
			+ "WHERE c.company_login=?";
	private static final String SQL_FIND_VACACY_BY_HR = "SELECT name, salary, currency, publish_date, description, requirements, conditions, employment_type "
			+ "FROM vacancy as v JOIN hr ON v.email = hr.email " + "WHERE hr.email=?";
	private static final String SQL_FIND_VACACY_BY_ID = "SELECT name, salary, currency, publish_date, description, requirements, conditions, employment_type "
			+ "FROM vacancy " + "WHERE id_vacancy=?";
	private static final String SQL_FIND_ALL_VACANCY = "SELECT name, salary, currency, publish_date, description, requirements, conditions, employment_type "
			+ "FROM vacancy";
	private static final String SQL_FIND_VACACY_BY_NAME = "SELECT name, salary, currency, publish_date, description, requirements, conditions, employment_type "
			+ "FROM vacancy " + "WHERE name LIKE ?";

	@Override
	public boolean create(Vacancy entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_VACANCY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getSalary());
			ps.setString(3, entity.getCurrency().getCurrencyType());
			if (entity.getPublishDate() != null) {
				ps.setDate(4, new Date(entity.getPublishDate().getTime()));
			} else {
				ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			}
			ps.setString(5, entity.getDescription());
			ps.setString(6, entity.getRequirement());
			ps.setString(7, entity.getConditions());
			ps.setString(8, entity.getEmployment_type());
			ps.setString(9, entity.getEmail());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create Vacancy: ", e);
			throw new DAOException("Faild create Vacancy: ", e);
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
	public void update(Vacancy entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_VACANCY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getSalary());
			ps.setString(3, entity.getCurrency().getCurrencyType());
			if (entity.getPublishDate() != null) {
				ps.setDate(4, new Date(entity.getPublishDate().getTime()));
			} else {
				ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			}
			ps.setString(5, entity.getDescription());
			ps.setString(6, entity.getRequirement());
			ps.setString(7, entity.getConditions());
			ps.setString(8, entity.getEmployment_type());
			ps.setString(9, entity.getEmail());
			ps.setInt(10, entity.getIdVacancy());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild create Vacancy: ", e);
			throw new DAOException("Faild create Vacancy: ", e);
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
	public void delete(int idVacancy) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_VACANCY);
			ps.setInt(1, idVacancy);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete Vacancy: ", e);
			throw new DAOException("Faild delete Vacancy: ", e);
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
	public Vacancy findVacancyByComany(String companyLogin) throws DAOException, DataDoesNotExistException {
		Vacancy vacancy = new Vacancy();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_VACACY_BY_COMPANY);
			ps.setString(1, companyLogin);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy = CreateEntity(rs);
			} else {
				throw new DataDoesNotExistException("Vacancy not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Vacancy: ", e);
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

		return vacancy;
	}

	@Override
	public Vacancy findVacancyByHr(String hrEmail) throws DAOException, DataDoesNotExistException {
		Vacancy vacancy = new Vacancy();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_VACACY_BY_HR);
			ps.setString(1, hrEmail);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy = CreateEntity(rs);
			} else {
				throw new DataDoesNotExistException("Vacancy not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Vacancy: ", e);
			throw new DAOException("Faild to find Vacancy: ", e);
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

		return vacancy;
	}

	@Override
	public Vacancy findVacancyById(int idVacancy) throws DAOException, DataDoesNotExistException {
		Vacancy vacancy = new Vacancy();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_VACACY_BY_ID);
			ps.setInt(1, idVacancy);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy = CreateEntity(rs);
			} else {
				throw new DataDoesNotExistException("Vacancy not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Vacancy: ", e);
			throw new DAOException("Faild to find Vacancy: ", e);
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

		return vacancy;
	}

	@Override
	public List<Vacancy> findAllVacancy() throws DAOException, DataDoesNotExistException {
		List<Vacancy> vacancy = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_ALL_VACANCY);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy.add(CreateEntity(rs));
			} else {
				throw new DataDoesNotExistException("Vacancy not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Vacancy: ", e);
			throw new DAOException("Faild to find Vacancy: ", e);
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
		return vacancy;
	}

	@Override
	public List<Vacancy> findVacancyLike(String name) throws DAOException, DataDoesNotExistException {
		List<Vacancy> vacancy = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_VACACY_BY_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy.add(CreateEntity(rs));
			} else {
				throw new DataDoesNotExistException("Vacancy not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Vacancy: ", e);
			throw new DAOException("Faild to find Vacancy: ", e);
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
		return vacancy;
	}

	private Vacancy CreateEntity(ResultSet set) throws SQLException {
		Vacancy vacancy = new Vacancy();
		vacancy.setIdVacancy(set.getInt(1));
		vacancy.setName(set.getString(2));
		vacancy.setSalary(set.getInt(3));
		vacancy.setCurrency(CurrencyType.valueOf(set.getString(4)));
		vacancy.setPublishDate(set.getDate(5));
		vacancy.setDescription(set.getString(6));
		vacancy.setRequirement(set.getString(7));
		vacancy.setConditions(set.getString(8));
		vacancy.setEmployment_type(set.getString(9));
		vacancy.setEmail(set.getString(10));
		return vacancy;

	}
}
