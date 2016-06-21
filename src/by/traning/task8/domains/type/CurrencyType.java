package by.traning.task8.domains.type;

public enum CurrencyType {
	RUB {
		{
			currencyType = "rub";
		}
	},
	DOLAR {
		{
			currencyType = "dolar";
		}
	};

	String currencyType;

	public String getCurrencyType() {
		return currencyType;
	}

}
