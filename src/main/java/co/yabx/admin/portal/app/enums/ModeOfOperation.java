package co.yabx.admin.portal.app.enums;

public enum ModeOfOperation {
	Single, Joint, Either_OR_Survivor, Other;

	public static ModeOfOperation getModeOfOperation(String value) {
		if (value.equalsIgnoreCase("Either or Survivor")) {
			return ModeOfOperation.Either_OR_Survivor;
		} else {
			return ModeOfOperation.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Either_OR_Survivor")) {
			return "Either or Survivor";
		} else {
			return super.toString();
		}
	}
}
