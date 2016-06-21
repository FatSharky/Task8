package by.traning.task8.domains;


import java.util.Arrays;
import java.util.Date;

import by.traning.task8.domains.role.Role;
import by.traning.task8.domains.type.ContactPhoneType;
import by.traning.task8.domains.type.MilitaryType;

public class Applicant {
	private String email;
	private String password;
	private String surname;
	private String name;
	private String secondName;
	private byte[] photo;
	private int workPhone;
	private int homePhone;
	private int mobPhone;
	private ContactPhoneType contactPhone;
	private String skype;
	private Date birthDate;
	private MilitaryType military;
	private Role role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(int workPhone) {
		this.workPhone = workPhone;
	}

	public int getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(int homePhone) {
		this.homePhone = homePhone;
	}

	public int getMobPhone() {
		return mobPhone;
	}

	public void setMobPhone(int mobPhone) {
		this.mobPhone = mobPhone;
	}

	public ContactPhoneType getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(ContactPhoneType contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public MilitaryType getMilitary() {
		return military;
	}

	public void setMilitary(MilitaryType military) {
		this.military = military;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + homePhone;
		result = prime * result + ((military == null) ? 0 : military.hashCode());
		result = prime * result + mobPhone;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((skype == null) ? 0 : skype.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + workPhone;
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
		Applicant other = (Applicant) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (contactPhone != other.contactPhone)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (homePhone != other.homePhone)
			return false;
		if (military != other.military)
			return false;
		if (mobPhone != other.mobPhone)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (role != other.role)
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (skype == null) {
			if (other.skype != null)
				return false;
		} else if (!skype.equals(other.skype))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (workPhone != other.workPhone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Applicant [email=" + email + ", password=" + password + ", surname=" + surname + ", name=" + name
				+ ", secondName=" + secondName + ", photo=" + Arrays.toString(photo) + ", workPhone=" + workPhone
				+ ", homePhone=" + homePhone + ", mobPhone=" + mobPhone + ", contactPhone=" + contactPhone + ", skype="
				+ skype + ", birthDate=" + birthDate + ", military=" + military + ", role=" + role + "]";
	}

}
