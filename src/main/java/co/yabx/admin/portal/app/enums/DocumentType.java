package co.yabx.admin.portal.app.enums;

public enum DocumentType {
	SIGNATURE, SELFIE, DRIVING_LICENSE, NATIONAL_IDENTIFICATION_NUMBER, PASSPORT, TIN_CERTIFICATE,
	VAT_REGISTRATION_NUMBER, BIRTH_CERTIFICATE, INCOME_TAX_RETURN, TRADE_LICENSE, PHOTO, NOMINEE_PHOTO, PROFILE_PIC,
	RETAILER_PHOTO, IGPA_FIXED_AND_FLOATING, DP_NOTE, LETTER_OF_CONTINUITY_AND_REVIVAL, DISBURSEMENT, ARRANGEMENT,
	GENERAL_LOAN_AGREEMENT, DECLARATION_FORM, PERSONAL_GUARANTEE_INDIVIDUAL, PERSONAL_GUARANTEE_JOINT, KYC_SME,
	LETTER_OF_DEBIT_AUTHORITY, SIGNATURE_CARD, SME_BUSINESS_SEGMENTATION_FORM_REVISED, PG_MEMO_ENGINEERS,
	PERFORMANCE_SECURITY_FORMAT, GENERAL_INSTRUCTION, FATCA_FORM_INDIVIDUAL_AND_PROPRIETOR, SBS_FORMS,
	COUNTER_GUARANTEE, EFT_AUTHORIZATION_FORM, LETTER_OF_LIEN_AND_SET_OFF_DEPOSIT_ACCOUNT_OR_MARGIN;

	public static DocumentType getDocumentType(String value) {
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
		} else if (value.equalsIgnoreCase("RETAILER PHOTO")) {
			return DocumentType.RETAILER_PHOTO;
		} else if (value.equalsIgnoreCase("PROFILE PIC")) {
			return DocumentType.PROFILE_PIC;
		} else {
			return DocumentType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("TRADE_LICENSE")) {
			return "TRADE LICENSE";
		} else if (super.toString().equals("INCOME_TAX_RETURN")) {
			return "Income Tax Return";
		} else if (super.toString().equals("BIRTH_CERTIFICATE")) {
			return "BIRTH CERTIFICATE";
		} else if (super.toString().equals("VAT_REGISTRATION_NUMBER")) {
			return "VAT Registration Number";
		} else if (super.toString().equals("TAX_IDENTIFICATION_NUMBER")) {
			return "TAX Identification Number";
		} else if (super.toString().equals("NATIONAL_IDENTIFICATION_NUMBER")) {
			return "NATIONAL IDENTIFICATION NUMBER";
		} else if (super.toString().equals("DRIVING_LICENSE")) {
			return "DRIVING LICENSE";
		} else if (super.toString().equals("PROFILE_PIC")) {
			return "PROFILE PIC";
		} else if (super.toString().equals("RETAILER_PHOTO")) {
			return "RETAILER PHOTO";
		} else if (super.toString().equals("NOMINEE_PHOTO")) {
			return "NOMINEE PHOTO";
		} else if (super.toString().equals("TIN_CERTIFICATE")) {
			return "TIN CERTIFICATE";
		} else {
			return super.toString();
		}
	}

}
