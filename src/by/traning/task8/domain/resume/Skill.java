package by.traning.task8.domain.resume;

import java.io.Serializable;

import by.traning.task8.domain.type.SkillType;

public class Skill implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idSkill;
	private String name;
	private SkillType raiting;
	private int IdResume;

	public Skill() {

	}

	public int getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getRaiting() {
		return raiting;
	}

	public void setRaiting(SkillType raiting) {
		this.raiting = raiting;
	}

	public int getIdResume() {
		return IdResume;
	}

	public void setIdResume(int idResume) {
		IdResume = idResume;
	}

}
