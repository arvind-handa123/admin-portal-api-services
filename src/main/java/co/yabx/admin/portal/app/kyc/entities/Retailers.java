package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RETAILERS")
public class Retailers extends User implements Serializable {

	@Column(name = "retailer_id", unique = true)
	private String retailerId;

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

}
