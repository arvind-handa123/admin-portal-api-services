package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeDetailsDTO implements Serializable {

	private Long id;

	private double monthlyIncome;

	private double monthlyIncomeFromOtherSource;

	private double monthlyExpenditure;

	private double monthlySurplus;

	private double guarantorNetAssets;

	private double totalAssets;

	private double totalLiabilities;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

	private UserDTO user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	public double getMonthlyIncomeFromOtherSource() {
		return monthlyIncomeFromOtherSource;
	}

	public void setMonthlyIncomeFromOtherSource(double monthlyIncomeFromOtherSource) {
		this.monthlyIncomeFromOtherSource = monthlyIncomeFromOtherSource;
	}

	@Override
	public String toString() {
		return "IncomeDetailsDTO [id=" + id + ", monthlyIncome=" + monthlyIncome + ", monthlyIncomeFromOtherSource="
				+ monthlyIncomeFromOtherSource + ", monthlyExpenditure=" + monthlyExpenditure + ", monthlySurplus="
				+ monthlySurplus + ", guarantorNetAssets=" + guarantorNetAssets + ", totalAssets=" + totalAssets
				+ ", totalLiabilities=" + totalLiabilities + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", user=" + user + "]";
	}

}
