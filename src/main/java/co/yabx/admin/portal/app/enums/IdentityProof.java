package co.yabx.admin.portal.app.enums;

public enum IdentityProof {
	DRIVING_LICENSE, PASSPORT;

	public static IdentityProof getIdentityProof(String value) {
		if (value.equalsIgnoreCase("DRIVING LICENSE")) {
			return IdentityProof.DRIVING_LICENSE;
		} else {
			return IdentityProof.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("DRIVING_LICENSE")) {
			return "Driving License";
		} else {
			return super.toString();
		}
	}
}
