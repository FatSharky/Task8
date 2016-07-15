package by.traning.task8.domain.resume;

import java.io.Serializable;

import by.traning.task8.domain.type.LanguageLevelType;

public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idLanguage;
	private String name;
	private LanguageLevelType raiting;
	private int idResume;

	public Language() {
		
	}

	public int getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LanguageLevelType getRaiting() {
		return raiting;
	}

	public void setRaiting(LanguageLevelType raiting) {
		this.raiting = raiting;
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
		result = prime * result + idLanguage;
		result = prime * result + idResume;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((raiting == null) ? 0 : raiting.hashCode());
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
		Language other = (Language) obj;
		if (idLanguage != other.idLanguage)
			return false;
		if (idResume != other.idResume)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (raiting != other.raiting)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Language [idLanguage=" + idLanguage + ", name=" + name + ", raiting=" + raiting + ", idResume="
				+ idResume + "]";
	}

}
