package co.yabx.admin.portal.app.enums;

public enum FunctionalityType {
	Copy, Compare, Validation, Replicate;

	public static FunctionalityType getFunctionalityType(String value) {
		if (value.equalsIgnoreCase("COPY")) {
			return FunctionalityType.Copy;
		} else if (value.equalsIgnoreCase("COMPARE")) {
			return FunctionalityType.Compare;
		} else if (value.equalsIgnoreCase("VALIDATION")) {
			return FunctionalityType.Validation;
		} else if (value.equalsIgnoreCase("REPLICATE")) {
			return FunctionalityType.Replicate;
		} else {
			return FunctionalityType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Compare")) {
			return "COMPARE";
		} else if (super.toString().equals("Copy")) {
			return "COPY";
		} else if (super.toString().equals("Validation")) {
			return "VALIDATION";
		} else if (super.toString().equals("Replicate")) {
			return "REPLICATE";
		} else {
			return super.toString();
		}
	}
}
