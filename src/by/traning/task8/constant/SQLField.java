package by.traning.task8.constant;

public final class SQLField {
	private SQLField() {
	}

	// FOR APPLICANT DAO
	public static final String APPLICANT_EMAIL = "email";
	public static final String APPLICANT_PASSWORD = "password";
	public static final String APPLICANT_SURNAME = "surname";
	public static final String APPLICNAT_NAME = "name";
	public static final String APPLICANT_SECONDNAME = "secondName";
	public static final String APPLICANT_PHOTO = "photo";
	public static final String APPLICANT_WORK_PHONE = "work_phone";
	public static final String APPLICANT_HOME_PHONE = "home_phone";
	public static final String APPLICANT_MOB_PHONE = "mob_phone";
	public static final String APPLICANT_CONTACT_PHONE = "contact_phone";
	public static final String APPLICANT_SKYPE = "skype";
	public static final String APPLICANT_BIRTH_DATE = "birth_date";
	public static final String APPLICANT_MILLATRY = "millatry";
	public static final String APPLICANT_ROLE = "role";

	// FOR COMPANY DAO
	public static final String COMPANY_LOGIN = "company_login";
	public static final String COMPANY_PASSWORD = "password";
	public static final String COMPANY_LOGO = "logo";
	public static final String COMPANY_NAME = "name";
	public static final String COMPANY_SITE = "web_site";
	public static final String COMPANY_CITY = "city";
	public static final String COMPANY_ROLE = "role";

	// FOR EDUCATION DAO
	public static final String EDUCATION_ID_EDUC = "id_education";
	public static final String EDUCATION_INSTITUTION = "institution";
	public static final String EDUCATION_FACULTY = "faculty";
	public static final String EDUCATION_DEPARTMENT = "department";
	public static final String EDUCATION_EDUCATION = "education";
	public static final String EDUCATION_COURSE = "course";
	public static final String EDUCATION_GRAND_YEAR = "year";
	public static final String EDUCATION_POSTGRADUATE = "postdraduate";
	public static final String EDUCATION_ID_RESUME = "id_resume";

	// FOR HR
	public static final String HR_EMAIL = "email";
	public static final String HR_PASSWORD = "password";
	public static final String HR_PHOTO = "photo";
	public static final String HR_SURNAME = "surname";
	public static final String HR_NAME = "name";

	// FOR LANGUAGE
	public static final String LANGUAGE_ID_LANGUAGE = "id_language";
	public static final String LANGUAGE_NAME = "name";
	public static final String LANGUAGE_RAITING = "raiting";
	public static final String LANGUAGE_ID_RESUME = "id_resume";

	// FOR SKILL
	public static final String SKILL_ID_SKILL = "id_skill";
	public static final String SKILL_NAME = "name";
	public static final String SKILL_RAITING = "raiting";
	public static final String SKILL_ID_RESUME = "id_resume";

	// FOR INTERVIEW MARK
	public static final String MARK_ID_MARK = "id_mark";
	public static final String MARK_SKILL = "skill";
	public static final String MARK_MARK = "mark";
	public static final String MARK_INTERVIEW_ID = "id_interview";

	// FOR WORKPLACE
	public static final String WORKPLACE_ID = "id_workplace";
	public static final String WORKPLACE_COMPANY_NAME = "company_name";
	public static final String WORKPLACE_POSITION = "position";
	public static final String WORKPLACE_DATE_BEGIN = "date_begin";
	public static final String WORKPLACE_DATE_END = "date_end";
	public static final String WORKPLACE_ID_RESUME = "id_resume";
}
