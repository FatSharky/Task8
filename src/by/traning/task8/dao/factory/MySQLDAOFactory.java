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
import by.traning.task8.dao.impl.ApplicantDAOImpl;
import by.traning.task8.dao.impl.CompanyDAOImpl;
import by.traning.task8.dao.impl.EducationDAOImpl;
import by.traning.task8.dao.impl.HrDAOImpl;
import by.traning.task8.dao.impl.InterviewDAOImpl;
import by.traning.task8.dao.impl.InterviewMarkDAOImpl;
import by.traning.task8.dao.impl.LanguageDAOImpl;
import by.traning.task8.dao.impl.ResumeDAOImpl;
import by.traning.task8.dao.impl.SkillDAOImpl;
import by.traning.task8.dao.impl.VacancyDAOImpl;
import by.traning.task8.dao.impl.VerifyDAOImpl;
import by.traning.task8.dao.impl.WorkplaceDAOImpl;

public class MySQLDAOFactory extends DAOFactory {
	private final ApplicantDAO applicantDAOImpl = new ApplicantDAOImpl();
	private final CompanyDAO companyDAOImpl = new CompanyDAOImpl();
	private final EducationDAO educationDAOImpl = new EducationDAOImpl();
	private final HrDAO hrDAOImpl = new HrDAOImpl();
	private final InterviewDAO interviewDAOimpl = new InterviewDAOImpl();
	private final InterviewMarkDAO interviewMarkDAOImpl = new InterviewMarkDAOImpl();
	private final LanguageDAO languageDAOImpl = new LanguageDAOImpl();
	private final ResumeDAO resumeDAOImpl = new ResumeDAOImpl();
	private final SkillDAO skillDAOImpl = new SkillDAOImpl();
	private final VacancyDAO vacancyDAOImpl = new VacancyDAOImpl();
	private final VerifyDAO verfyDAOImpl = new VerifyDAOImpl();
	private final WorkPlaceDAO workPlaceDAOImpl = new WorkplaceDAOImpl();

	@Override
	public ApplicantDAO getApplicantDAO() {
		return applicantDAOImpl;
	}

	@Override
	public CompanyDAO getCompanyDAO() {
		return companyDAOImpl;
	}

	@Override
	public EducationDAO getEducationDAO() {
		return educationDAOImpl;
	}

	@Override
	public HrDAO getHrDAO() {
		return hrDAOImpl;
	}

	@Override
	public InterviewDAO getInterviewDAO() {
		return interviewDAOimpl;
	}

	@Override
	public InterviewMarkDAO getInterviewMarkDAO() {
		return interviewMarkDAOImpl;
	}

	@Override
	public LanguageDAO getLanguageDAO() {
		return languageDAOImpl;
	}

	@Override
	public ResumeDAO getResumeDAO() {
		return resumeDAOImpl;
	}

	@Override
	public SkillDAO getSkillDAO() {
		return skillDAOImpl;
	}

	@Override
	public VacancyDAO getVacancyDAO() {
		return vacancyDAOImpl;
	}

	@Override
	public VerifyDAO getVerifyDAO() {
		return verfyDAOImpl;
	}

	@Override
	public WorkPlaceDAO getWorkplaceDAO() {
		return workPlaceDAOImpl;
	}

}
