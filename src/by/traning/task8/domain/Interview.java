package by.traning.task8.domain;

import java.io.Serializable;
import java.util.Date;

import by.traning.task8.domain.type.InterviewType;
import by.traning.task8.domain.type.PassType;

public class Interview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idInterview;
	private InterviewType type;
	private Date dateBegin;
	private PassType pass;
	private int idVerify;

	public Interview() {

	}

	public int getIdInterview() {
		return idInterview;
	}

	public void setIdInterview(int idInterview) {
		this.idInterview = idInterview;
	}

	public InterviewType getType() {
		return type;
	}

	public void setType(InterviewType type) {
		this.type = type;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public PassType getPass() {
		return pass;
	}

	public void setPass(PassType pass) {
		this.pass = pass;
	}

	public int getIdVerify() {
		return idVerify;
	}

	public void setIdVerify(int idVerify) {
		this.idVerify = idVerify;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateBegin == null) ? 0 : dateBegin.hashCode());
		result = prime * result + idInterview;
		result = prime * result + idVerify;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Interview other = (Interview) obj;
		if (dateBegin == null) {
			if (other.dateBegin != null)
				return false;
		} else if (!dateBegin.equals(other.dateBegin))
			return false;
		if (idInterview != other.idInterview)
			return false;
		if (idVerify != other.idVerify)
			return false;
		if (pass != other.pass)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interview [idInterview=" + idInterview + ", type=" + type + ", dateBegin=" + dateBegin + ", pass="
				+ pass + ", idVerify=" + idVerify + "]";
	}

}
