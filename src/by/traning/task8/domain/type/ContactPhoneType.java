package by.traning.task8.domain.type;

public enum ContactPhoneType {
	MOBILE_PHONE {
		{
			contactPhoneType = "mobPhone";
		}
	},
	HOME_PHONE {
		{
			contactPhoneType = "homePhone";
		}
	},
	WORK_PHONE {
		{
			contactPhoneType = "workPhone";
		}
	};

	String contactPhoneType;

	public String getContactPhoneType() {
		return contactPhoneType;
	}
}
