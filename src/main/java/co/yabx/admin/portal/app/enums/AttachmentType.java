package co.yabx.admin.portal.app.enums;

public enum AttachmentType {
	IdentityProof, AddressProof;

	public static AttachmentType getAttachmentType(String value) {
		if (value.equalsIgnoreCase("Identity Proof")) {
			return AttachmentType.IdentityProof;
		} else if (value.equalsIgnoreCase("Address Proof")) {
			return AttachmentType.AddressProof;
		} else {
			return AttachmentType.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("AddressProof")) {
			return "Address Proof";
		} else if (super.toString().equals("IdentityProof")) {
			return "Identity Proof";
		} else {
			return super.toString();
		}
	}
}
