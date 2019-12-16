package co.yabx.admin.portal.app.enums;

public enum ResidentStatus {

	Resident, Non_Resident;

	public static ResidentStatus getResidentStatus(String value) {
		if (value.equalsIgnoreCase("Non-Resident")) {
			return ResidentStatus.Non_Resident;
		} else {
			return ResidentStatus.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Non_Resident")) {
			return "Non-Resident";
		} else {
			return super.toString();
		}
	}
}
