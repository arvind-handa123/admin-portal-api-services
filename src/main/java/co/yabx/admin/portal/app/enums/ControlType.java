package co.yabx.admin.portal.app.enums;

public enum ControlType {

	RADIO("RADIO"), CHECKBOX("CHECKBOX"), DROPDOWNS("DROPDOWNS"), TEXTAREA("TEXTAREA"), BUTTON("BUTTON"), LINK("LINK"),
	ATTACHMENT("ATTACHMENT"), TEXT("TEXT"), CALENDER("CALENDER"), DDMMYYYY("dd/mm/yyyy"), PLUS("+");

	private String value;

	private ControlType(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

}
