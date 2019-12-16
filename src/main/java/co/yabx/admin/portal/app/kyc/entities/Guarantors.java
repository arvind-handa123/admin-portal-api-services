package co.yabx.admin.portal.app.kyc.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import co.yabx.admin.portal.app.enums.GuarantorType;


@Entity
@DiscriminatorValue("guarantors")
public class Guarantors extends User {

	@Column(name = "guarantor_type")
	private GuarantorType guarantorType;

	@OneToMany(mappedBy = "guarantors", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<IncomeDetails> incomeDetails;

	public GuarantorType getGuarantorType() {
		return guarantorType;
	}

	public void setGuarantorType(GuarantorType guarantorType) {
		this.guarantorType = guarantorType;
	}

	public Set<IncomeDetails> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(Set<IncomeDetails> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

}
