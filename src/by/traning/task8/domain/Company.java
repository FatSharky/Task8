package by.traning.task8.domain;

import java.io.Serializable;
import java.util.Arrays;

import by.traning.task8.domain.role.Role;

public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	private String companyLogin;
	private String password;
	private byte[] logo;
	private String name;
	private String webSite;
	private String city;
	private Role role;

	public Company() {

	}

	public String getCompanyLogin() {
		return companyLogin;
	}

	public void setCompanyLogin(String companyLogin) {
		this.companyLogin = companyLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((companyLogin == null) ? 0 : companyLogin.hashCode());
		result = prime * result + Arrays.hashCode(logo);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((webSite == null) ? 0 : webSite.hashCode());
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
		Company other = (Company) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (companyLogin == null) {
			if (other.companyLogin != null)
				return false;
		} else if (!companyLogin.equals(other.companyLogin))
			return false;
		if (!Arrays.equals(logo, other.logo))
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
		if (role != other.role)
			return false;
		if (webSite == null) {
			if (other.webSite != null)
				return false;
		} else if (!webSite.equals(other.webSite))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [companyLogin=" + companyLogin + ", password=" + password + ", logo=" + Arrays.toString(logo)
				+ ", name=" + name + ", webSite=" + webSite + ", city=" + city + ", role=" + role + "]";
	}

}
