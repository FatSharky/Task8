package by.traning.task8.domain.role;

public enum Role {
	APPLICNAT {
		{
			role = "applicant";
		}
	},
	COMPANY {
		{
			role = "company";
		}
	},
	HR {
		{
			role = "hr";
		}
	};

	String role;

	public String getRole() {
		return role;
	}

}
