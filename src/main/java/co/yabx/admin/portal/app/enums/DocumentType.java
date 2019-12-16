package co.yabx.admin.portal.app.enums;

public enum DocumentType {
	SIGNATURE, SELFIE, DRIVING_LICENSE, NATIONAL_IDENTIFICATION_NUMBER, PASSPORT, TIN_CERTIFICATE,
	VAT_REGISTRATION_NUMBER, BIRTH_CERTIFICATE, INCOME_TAX_RETURN, TRADE_LICENSE, PHOTO, NOMINEE_PHOTO, PROFILE_PIC;

	public static DocumentType getAddressType(String value) {
		if (value.equalsIgnoreCase("DRIVING LICENSE")) {
			return DocumentType.DRIVING_LICENSE;
		} else if (value.equalsIgnoreCase("NATIONAL IDENTIFICATION NUMBER")) {
			return DocumentType.NATIONAL_IDENTIFICATION_NUMBER;
		} else if (value.equalsIgnoreCase("TIN CERTIFICATE")) {
			return DocumentType.TIN_CERTIFICATE;
		} else if (value.equalsIgnoreCase("VAT REGISTRATION NUMBER")) {
			return DocumentType.VAT_REGISTRATION_NUMBER;
		} else if (value.equalsIgnoreCase("BIRTH CERTIFICATE")) {
			return DocumentType.BIRTH_CERTIFICATE;
		} else if (value.equalsIgnoreCase("INCOME TAX RETURN")) {
			return DocumentType.INCOME_TAX_RETURN;
		} else if (value.equalsIgnoreCase("TRADE LICENSE")) {
			return DocumentType.TRADE_LICENSE;
		} else if (value.equalsIgnoreCase("NOMINEE PHOTOE")) {
			return DocumentType.NOMINEE_PHOTO;
		} else if (value.equalsIgnoreCase("PROFILE PIC")) {
			return DocumentType.PROFILE_PIC;
		} else {
			return DocumentType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("TRADE_LICENSE")) {
			return "Trade License";
		} else if (super.toString().equals("INCOME_TAX_RETURN")) {
			return "Income Tax Return";
		} else if (super.toString().equals("BIRTH_CERTIFICATE")) {
			return "Birth Certificate";
		} else if (super.toString().equals("VAT_REGISTRATION_NUMBER")) {
			return "VAT Registration Number";
		} else if (super.toString().equals("TAX_IDENTIFICATION_NUMBER")) {
			return "TAX Identification Number";
		} else if (super.toString().equals("NATIONAL_IDENTIFICATION_NUMBER")) {
			return "National Identification Number";
		} else if (super.toString().equals("DRIVING_LICENSE")) {
			return "Driving License";
		} else if (super.toString().equals("PROFILE_PIC")) {
			return "PROFILE PIC";
		} else {
			return super.toString();
		}
	}

}
