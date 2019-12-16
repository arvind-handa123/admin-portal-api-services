package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "income_details", indexes = { @Index(name = "total_assets", columnList = "total_assets"),
		@Index(name = "monthly_income", columnList = "monthly_income") })
public class IncomeDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "monthly_income")
	private double monthlyIncome;

	@Column(name = "monthly_income_from_other_source")
	private double monthlyIncomeFromOtherSource;

	@Column(name = "monthly_expenditure")
	private double monthlyExpenditure;

	@Column(name = "monthly_surplus")
	private double monthlySurplus;

	@Column(name = "guarantor_net_assets")
	private double guarantorNetAssets;

	@Column(name = "total_assets")
	private double totalAssets;

	@Column(name = "total_liabilities")
	private double totalLiabilities;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Guarantors.class)
	Guarantors guarantors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double getMonthlyExpenditure() {
		return monthlyExpenditure;
	}

	public void setMonthlyExpenditure(double monthlyExpenditure) {
		this.monthlyExpenditure = monthlyExpenditure;
	}

	public double getMonthlySurplus() {
		return monthlySurplus;
	}

	public void setMonthlySurplus(double monthlySurplus) {
		this.monthlySurplus = monthlySurplus;
	}

	public double getGuarantorNetAssets() {
		return guarantorNetAssets;
	}

	public void setGuarantorNetAssets(double guarantorNetAssets) {
		this.guarantorNetAssets = guarantorNetAssets;
	}

	public double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public double getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(double totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@PrePersist
	private void prePersist() {
		if (createdAt == null) {
			createdAt = new Date();
			updatedAt = new Date();
		}
	}

	@PreUpdate
	private void preUpdate() {
		updatedAt = new Date();

	}

	public double getMonthlyIncomeFromOtherSource() {
		return monthlyIncomeFromOtherSource;
	}

	public void setMonthlyIncomeFromOtherSource(double monthlyIncomeFromOtherSource) {
		this.monthlyIncomeFromOtherSource = monthlyIncomeFromOtherSource;
	}

}
