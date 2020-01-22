package co.yabx.admin.portal.app.enums;

public enum FacilityType {
	Term_Loan(0, "Term Loan"), Overdraft(1, "Overdraft"), Short_Term_Loan(2, "Short Term Loan"),
	LC(3, "Letter of Credit"), LATR(4, "Loan Against Trust Receipt"), IBP(5, "Inland Bill Purchase");

	private int id;
	private String name;

	FacilityType(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static FacilityType findById(int id) {
		FacilityType[] facilityTypes = FacilityType.values();
		for (FacilityType facilityType : facilityTypes) {
			if (facilityType.getId() == id) {
				return facilityType;
			}
		}
		return null;
	}

	public static FacilityType findByValue(String value) {
		FacilityType[] facilityTypes = FacilityType.values();
		for (FacilityType facilityType : facilityTypes) {
			if (facilityType.getName().equalsIgnoreCase(value)) {
				return facilityType;
			}
		}
		return null;
	}
}
