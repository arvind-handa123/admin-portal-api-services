package co.yabx.admin.portal.app.enums;

public enum KycStatus {
	YES, NO, REJECTED, NEW, SUBMITTED, APPROVED, IN_PROGRESS, LOC_ISSUED, LOC_GENERATED, UNDER_REVIEW, RE_SUBMITTED;
	public static KycStatus getAddressType(String value) {
		if (value.equalsIgnoreCase("IN PROGRESS")) {
			return KycStatus.IN_PROGRESS;
		} else if (value.equalsIgnoreCase("IN PROGRESS")) {
			return KycStatus.LOC_ISSUED;
		} else if (value.equalsIgnoreCase("LOC GENERATED")) {
			return KycStatus.LOC_GENERATED;
		} else if (value.equalsIgnoreCase("UNDER REVIEW")) {
			return KycStatus.UNDER_REVIEW;
		} else if (value.equalsIgnoreCase("RE SUBMITTED")) {
			return KycStatus.RE_SUBMITTED;
		}
		{
			return KycStatus.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("IN_PROGRESS")) {
			return "In Progress";
		} else if (super.toString().equals("LOC_ISSUED")) {
			return "LO ISSUED";
		} else if (super.toString().equals("LOC_GENERATED")) {
			return "LOC GENERATED";
		} else if (super.toString().equals("UNDER_REVIEW")) {
			return "UNDER REVIEW";
		} else if (super.toString().equals("RE_SUBMITTED")) {
			return "RE SUBMITTED";
		}
		{
			return super.toString();
		}
	}

}
