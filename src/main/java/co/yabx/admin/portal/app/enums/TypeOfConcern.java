package co.yabx.admin.portal.app.enums;

public enum TypeOfConcern {
	Proprietorship, Partnership, Joint_Venture, Private_Ltd, Public_Ltd, Govt_Organization, Semi_Govt_Organization,
	Trust, NGO, CLUB_SOCIETY, EDUCATIONAL_INSTITUTION, RELIGIOUS_ESTABLISHMENT, Others;

	public static TypeOfConcern getTypeOfConcern(String value) {
		if (value.equalsIgnoreCase("Joint Venture")) {
			return TypeOfConcern.Joint_Venture;
		} else if (value.equalsIgnoreCase("Private Ltd.")) {
			return TypeOfConcern.Private_Ltd;
		} else if (value.equalsIgnoreCase("Public Ltd.")) {
			return TypeOfConcern.Public_Ltd;
		} else if (value.equalsIgnoreCase("Govt Organization")) {
			return TypeOfConcern.Govt_Organization;
		} else if (value.equalsIgnoreCase("Semi Govt Organization")) {
			return TypeOfConcern.Semi_Govt_Organization;
		} else if (value.equalsIgnoreCase("Club Society")) {
			return TypeOfConcern.CLUB_SOCIETY;
		} else if (value.equalsIgnoreCase("Educational Institution")) {
			return TypeOfConcern.EDUCATIONAL_INSTITUTION;
		} else if (value.equalsIgnoreCase("Religious Establishment")) {
			return TypeOfConcern.RELIGIOUS_ESTABLISHMENT;
		} else {
			return TypeOfConcern.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Joint_Venture")) {
			return "Joint Venture";
		} else if (super.toString().equals("Private_Ltd")) {
			return "Private Ltd.";
		} else if (super.toString().equals("RELIGIOUS_ESTABLISHMENT")) {
			return "Religious Establishment";
		} else if (super.toString().equals("EDUCATIONAL_INSTITUTION")) {
			return "Educational Institution";
		} else if (super.toString().equals("CLUB_SOCIETY")) {
			return "Club Society";
		} else if (super.toString().equals("Semi_Govt_Organization")) {
			return "Semi Govt Organization";
		} else if (super.toString().equals("Public_Ltd")) {
			return "Public Ltd.";
		} else if (super.toString().equals("Govt_Organization")) {
			return "Govt Organization";
		} else {
			return super.toString();
		}
	}
}
