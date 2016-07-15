package by.traning.task8.domain.type;

public enum MilitaryType {

	NOT_SPECIFIED {
		{
			millatryType = "not specified";
		}
	},
	FIT {
		{
			millatryType = "fit";
		}
	},
	NOT_FIT {
		{
			millatryType = "not fit";
		}
	},
	MILITARY_DEPARTMENT {
		{
			millatryType = "military department";
		}
	},
	NOT_BOUND {
		{
			millatryType = "not bound";
		}
	};

	String millatryType;

	private MilitaryType() {
	}

	public String getMillatryType() {
		return millatryType;
	}

}
