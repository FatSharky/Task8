package by.traning.task8.domain.resume;

import java.io.Serializable;
import java.util.Date;

import by.traning.task8.domain.type.EducationType;
import by.traning.task8.domain.type.PostgraduateType;

public class Education implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idEducation;
	private String institution;
	private String faculty;
	private String departmnet;
	private EducationType education;
	private int course;
	private Date grandYear;
	private PostgraduateType postgraduate;
	private int idResume;

	public Education() {

	}

	public int getIdEducation() {
		return idEducation;
	}

	public void setIdEducation(int idEducation) {
		this.idEducation = idEducation;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartmnet() {
		return departmnet;
	}

	public void setDepartmnet(String departmnet) {
		this.departmnet = departmnet;
	}

	public EducationType getEducation() {
		return education;
	}

	public void setEducation(EducationType education) {
		this.education = education;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Date getGrandYear() {
		return grandYear;
	}

	public void setGrandYear(Date grandYear) {
		this.grandYear = grandYear;
	}

	public PostgraduateType getPostgraduate() {
		return postgraduate;
	}

	public void setPostgraduate(PostgraduateType postgraduate) {
		this.postgraduate = postgraduate;
	}

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + course;
		result = prime * result + ((departmnet == null) ? 0 : departmnet.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((grandYear == null) ? 0 : grandYear.hashCode());
		result = prime * result + idEducation;
		result = prime * result + idResume;
		result = prime * result + ((institution == null) ? 0 : institution.hashCode());
		result = prime * result + ((postgraduate == null) ? 0 : postgraduate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Education other = (Education) obj;
		if (course != other.course)
			return false;
		if (departmnet == null) {
			if (other.departmnet != null)
				return false;
		} else if (!departmnet.equals(other.departmnet))
			return false;
		if (education != other.education)
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (grandYear == null) {
			if (other.grandYear != null)
				return false;
		} else if (!grandYear.equals(other.grandYear))
			return false;
		if (idEducation != other.idEducation)
			return false;
		if (idResume != other.idResume)
			return false;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		if (postgraduate != other.postgraduate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Education [idEducation=" + idEducation + ", institution=" + institution + ", faculty=" + faculty
				+ ", departmnet=" + departmnet + ", education=" + education + ", course=" + course + ", grandYear="
				+ grandYear + ", postgraduate=" + postgraduate + ", idResume=" + idResume + "]";
	}

}
