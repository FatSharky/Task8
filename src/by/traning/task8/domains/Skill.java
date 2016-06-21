package by.traning.task8.domains;

import by.traning.task8.domains.type.SkillType;

public class Skill {
	private int idSkill;
	private String name;
	private SkillType raiting;
	private int IdResume;

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
