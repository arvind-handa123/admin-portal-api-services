package co.yabx.admin.portal.app.enums;

public enum GroupType {
	PERMANENT_ADDRESS, PRESENT_ADDRESS, REGISTERED_ADDRESS;

	public static GroupType getGroupType(String value) {
		if (value.equalsIgnoreCase("Permanent Address")) {
			return GroupType.PERMANENT_ADDRESS;
		} else if (value.equalsIgnoreCase("Present Address")) {
			return GroupType.PRESENT_ADDRESS;
		} else if (value.equalsIgnoreCase("Registered Address")) {
			return GroupType.REGISTERED_ADDRESS;
		} else {
			return GroupType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("PERMANENT_ADDRESS")) {
			return "Permanent Address";
		} else if (super.toString().equals("PRESENT_ADDRESS")) {
			return "Present Address";
		} else if (super.toString().equals("REGISTERED_ADDRESS")) {
			return "Registered Address";
		} else {
			return super.toString();
		}
	}
}
