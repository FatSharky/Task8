package by.traning.task8.domain.resume;

import java.io.Serializable;
import java.util.Date;

public class WorkPlace implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idWorkPlace;
	private String companyName;
	private String position;
	private Date dateBegin;
	private Date dateEnd;
	private int idResume;

	public int getIdWorkPlace() {
		return idWorkPlace;
	}

	public void setIdWorkPlace(int idWorkPlace) {
		this.idWorkPlace = idWorkPlace;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
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
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((dateBegin == null) ? 0 : dateBegin.hashCode());
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + idResume;
		result = prime * result + idWorkPlace;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		WorkPlace other = (WorkPlace) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (dateBegin == null) {
			if (other.dateBegin != null)
				return false;
		} else if (!dateBegin.equals(other.dateBegin))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (idResume != other.idResume)
			return false;
		if (idWorkPlace != other.idWorkPlace)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkPlace [idWorkPlace=" + idWorkPlace + ", companyName=" + companyName + ", position=" + position
				+ ", dateBegin=" + dateBegin + ", dateEnd=" + dateEnd + ", idResume=" + idResume + "]";
	}

}
