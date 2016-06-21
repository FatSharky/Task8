package by.traning.task8.domains.role;

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
