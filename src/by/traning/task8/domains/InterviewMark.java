package by.traning.task8.domains;

import by.traning.task8.domains.type.SkillType;

public class InterviewMark {
	private int idMark;
	private String skill;
	private SkillType mark;
	private int idInterview;

	public int getIdMark() {
		return idMark;
	}

	public void setIdMark(int idMark) {
		this.idMark = idMark;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public SkillType getMark() {
		return mark;
	}

	public void setMark(SkillType mark) {
		this.mark = mark;
	}

	public int getIdInterview() {
		return idInterview;
	}

	public void setIdInterview(int idInterview) {
		this.idInterview = idInterview;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idInterview;
		result = prime * result + idMark;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
		InterviewMark other = (InterviewMark) obj;
		if (idInterview != other.idInterview)
			return false;
		if (idMark != other.idMark)
			return false;
		if (mark != other.mark)
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviewMark [idMark=" + idMark + ", skill=" + skill + ", mark=" + mark + ", idInterview="
				+ idInterview + "]";
	}

}
