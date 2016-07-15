package by.traning.task8.domain;

import java.io.Serializable;

import by.traning.task8.domain.type.PassType;

public class Verify implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idVerify;
	private int idVacancy;
	private int idResume;
	private PassType pass;

	public Verify() {

	}

	public int getIdVerify() {
		return idVerify;
	}

	public void setIdVerify(int idVerify) {
		this.idVerify = idVerify;
	}

	public int getIdVacancy() {
		return idVacancy;
	}

	public void setIdVacancy(int idVacancy) {
		this.idVacancy = idVacancy;
	}

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public PassType getPass() {
		return pass;
	}

	public void setPass(PassType pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idResume;
		result = prime * result + idVacancy;
		result = prime * result + idVerify;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
		Verify other = (Verify) obj;
		if (idResume != other.idResume)
			return false;
		if (idVacancy != other.idVacancy)
			return false;
		if (idVerify != other.idVerify)
			return false;
		if (pass != other.pass)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Verify [idVerify=" + idVerify + ", idVacancy=" + idVacancy + ", idResume=" + idResume + ", pass=" + pass
				+ "]";
	}

}
