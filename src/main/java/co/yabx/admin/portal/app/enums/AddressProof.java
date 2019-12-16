package co.yabx.admin.portal.app.enums;

public enum AddressProof {
	DRIVING_LICENSE, PASSPORT;

	public static AddressProof getAddressProof(String value) {
		if (value.equalsIgnoreCase("Driving License")) {
			return AddressProof.DRIVING_LICENSE;
		} else {
			return AddressProof.valueOf(value);
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
