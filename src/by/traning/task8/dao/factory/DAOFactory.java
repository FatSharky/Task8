package by.traning.task8.dao.factory;

import by.traning.task8.dao.ApplicantDAO;
import by.traning.task8.dao.CompanyDAO;
import by.traning.task8.dao.EducationDAO;
import by.traning.task8.dao.HrDAO;
import by.traning.task8.dao.InterviewDAO;
import by.traning.task8.dao.InterviewMarkDAO;
import by.traning.task8.dao.LanguageDAO;
import by.traning.task8.dao.ResumeDAO;
import by.traning.task8.dao.SkillDAO;
import by.traning.task8.dao.VacancyDAO;
import by.traning.task8.dao.VerifyDAO;
import by.traning.task8.dao.WorkPlaceDAO;

public abstract class DAOFactory {
	private static final int MY_SQL = 1;


	private static final DAOFactory mySQLDAOFactory = new MySQLDAOFactory();

	public abstract ApplicantDAO getApplicantDAO();

	public abstract CompanyDAO getCompanyDAO();

	public abstract EducationDAO getEducationDAO();

	public abstract HrDAO getHrDAO();

	public abstract InterviewDAO getInterviewDAO();

	public abstract InterviewMarkDAO getInterviewMarkDAO();

	public abstract LanguageDAO getLanguageDAO();

	public abstract ResumeDAO getResumeDAO();

	public abstract SkillDAO getSkillDAO();

	public abstract VacancyDAO getVacancyDAO();

	public abstract VerifyDAO getVerifyDAO();

	public abstract WorkPlaceDAO getWorkplaceDAO();

	public static DAOFactory getDAOFactory() {
		int factoryType = readConfig();

		switch (factoryType) {
		case MY_SQL:
			return mySQLDAOFactory;
		default:
			return null;
		}
	}

	private static int readConfig() {
		return MY_SQL;
	}

}
