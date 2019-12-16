package co.yabx.admin.portal.app.enums;

public enum BusinessType {
	Proprietorship, Sole_Proprietorship, Partnership, Limited_Company;

	public static BusinessType getBusinessType(String value) {
		if (value.equalsIgnoreCase("Sole Proprietorship")) {
			return BusinessType.Sole_Proprietorship;
		} else if (value.equalsIgnoreCase("Limited Company")) {
			return BusinessType.Limited_Company;
		} else {
			return BusinessType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Sole_Proprietorship")) {
			return "Sole Proprietorship";
		} else if (super.toString().equals("Limited_Company")) {
			return "Limited Company";
		} else {
			return super.toString();
		}
	}
}
