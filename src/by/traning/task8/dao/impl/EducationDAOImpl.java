package by.traning.task8.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import by.traning.task8.dao.EducationDAO;
import by.traning.task8.dao.exception.DAOException;
import by.traning.task8.dao.exception.DataDoesNotExistException;
import by.traning.task8.dao.impl.constant.SQLField;
import by.traning.task8.dao.pool.ConnectionPool;
import by.traning.task8.dao.pool.exception.ConnectionPoolException;
import by.traning.task8.domain.resume.Education;
import by.traning.task8.domain.type.EducationType;
import by.traning.task8.domain.type.PostgraduateType;

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
			ps.setDate(6, new java.sql.Date(entity.getGrandYear().getTime()));
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
			if (entity.getGrandYear() != null) {
				ps.setDate(7, new java.sql.Date(entity.getGrandYear().getTime()));
			} else {
				ps.setDate(7, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			}
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
				education.add(getEducationFromResultSet(rs));
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

	private Education getEducationFromResultSet(ResultSet set) throws SQLException {
		Education education = new Education();
		education.setIdEducation(set.getInt(SQLField.EDUCATION_ID));
		education.setInstitution(set.getString(SQLField.EDUCATION_INSTITUTION));
		education.setFaculty(set.getString(SQLField.EDUCATION_FACULTY));
		education.setDepartmnet(set.getString(SQLField.EDUCATION_DEPARTMENT));
		education.setEducation(EducationType.valueOf(set.getString(SQLField.EDUCATION_EDUCATION)));
		education.setCourse(set.getInt(SQLField.EDUCATION_COURSE));
		education.setGrandYear(set.getDate(SQLField.EDUCATION_GRAND_YEAR));
		education.setPostgraduate(PostgraduateType.valueOf(set.getString(SQLField.EDUCATION_POSTGRADUATE)));
		education.setIdResume(set.getInt(SQLField.EDUCATION_ID_RESUME));
		return education;

	}
}
