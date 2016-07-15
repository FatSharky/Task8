package by.traning.task8.domain.type;

public enum EducationType {
	NOT_SPECIFIED{
		{
			educationType = "not specified";
		}
	},
	UNIVERSITY_INCOMPLITE{
		{
			educationType="university incomplete";
		}
	},
	HIGHER{
		{
			educationType="higher";
		}
	},
	MASTER{
		{
			educationType="master";
		}
	},
	PHD{
		{
			educationType="PhD";
		}
	},
	SECONDARY{
		{
			educationType="secondary";
		}
	},
	TECHNICAL_SHOOL{
		{
			educationType="technical school";
		}
	},
	PHD_CANDIDATE{
		{
			educationType="PhD candidate";
		}
	};
	
	String educationType;

	public String getEducationType() {
		return educationType;
	}

}
