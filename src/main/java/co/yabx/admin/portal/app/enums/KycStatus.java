package co.yabx.admin.portal.app.enums;

public enum KycStatus {
	YES, NO, REJECTED, NEW, SUBMITTED, APPROVED, IN_PROGRESS;
	public static KycStatus getAddressType(String value) {
		if (value.equalsIgnoreCase("IN PROGRESS")) {
			return KycStatus.IN_PROGRESS;
		} else {
			return KycStatus.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("IN_PROGRESS")) {
			return "In Progress";
		} else {
			return super.toString();
		}
	}

}
