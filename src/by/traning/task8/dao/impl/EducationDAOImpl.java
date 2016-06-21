package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.EducationDAO;
import by.traning.task8.dao.pull.ConnectionPool;
import by.traning.task8.dao.pull.exception.ConnectionPoolException;
import by.traning.task8.domains.Education;
import by.traning.task8.domains.type.EducationType;
import by.traning.task8.domains.type.PostgraduateType;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public class EducationDAOImpl implements EducationDAO {
	private static final Logger LOG = Logger.getLogger(ApplicantDAOImpl.class);
	private static final String SQL_CREATE_EDUCATION = "INSERT INTO education (institution, faculty, department, education, course, grad_year, postgraduate, id_resume)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_EDUCATION = "UPDATE education SET institution=?, faculty=?, department=?, education=?, course=?, grad_year=?, postgraduate=?, id_resume=?"
			+ " WHERE id_education=?";
	private static final String SQL_DELETE_EDUCATION = "DELETE education WHERE id_education=?";
	private static final String SQL_FIND_EDUCATION_BY_RESUME = "SELECT e.institution, e.institution, e.faculty, e.department, e.education, e.course, e.grad_year, e.postgraduate "
			+ "FROM resume as r JOIN education as e ON v.id_resume = e.id_resume " + "WHERE r.id_resume=?";

	@Override
	public boolean create(Education entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_CREATE_EDUCATION);
			ps.setString(1, entity.getInstitution());
			ps.setString(2, entity.getFaculty());
			ps.setString(3, entity.getDepartmnet());
			ps.setString(4, entity.getEducation().getEducationType());
			ps.setInt(5, entity.getCourse());
			ps.setDate(6, entity.getGrandYear());
			ps.setString(7, entity.getPostgraduate().getPostgraduateType());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOG.error("Faild create Education: ", e);
			throw new DAOException("Faild create Education: ", e);
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
	public void update(Education entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_EDUCATION);
			ps.setInt(1, entity.getIdEducation());
			ps.setString(2, entity.getInstitution());
			ps.setString(3, entity.getFaculty());
			ps.setString(4, entity.getDepartmnet());
			ps.setString(5, entity.getEducation().getEducationType());
			ps.setInt(6, entity.getCourse());
			ps.setDate(7, entity.getGrandYear());
			ps.setString(8, entity.getPostgraduate().getPostgraduateType());
			ps.setInt(9, entity.getIdEducation());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Faild update Education: ", e);
			throw new DAOException("Faild update Education: ", e);
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
	public void delete(int idEducation) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_EDUCATION);
			ps.setInt(1, idEducation);
			ps.executeQuery();
		} catch (SQLException e) {
			LOG.error("Faild delete Education: ", e);
			throw new DAOException("Faild delete Education: ", e);
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
	public List<Education> fetchResumeEducation(int resumeId) throws DAOException, DataDoesNotExistException {
		List<Education> education = new ArrayList<Education>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_FIND_EDUCATION_BY_RESUME);
			ps.setInt(1, resumeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				education.add(CreateEntity(rs));
			} else {
				throw new DataDoesNotExistException("Company not found!");
			}
		} catch (SQLException e) {
			LOG.error("Faild to find Company: ", e);
			throw new DAOException("Faild to find Company: ", e);
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

		return education;

	}

	private Education CreateEntity(ResultSet set) throws SQLException {
		Education education = new Education();
		education.setIdEducation(set.getInt(1));
		education.setInstitution(set.getString(2));
		education.setDepartmnet(set.getString(3));
		education.setEducation(EducationType.valueOf(set.getString(4)));
		education.setCourse(set.getInt(5));
		education.setGrandYear(set.getDate(6));
		education.setPostgraduate(PostgraduateType.valueOf(set.getString(7)));
		education.setIdResume(set.getInt(8));
		return education;

	}
}
