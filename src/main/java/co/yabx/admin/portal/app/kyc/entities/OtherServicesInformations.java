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

import co.yabx.admin.portal.app.enums.Frequency;
import co.yabx.admin.portal.app.enums.Status;



@Entity
@Table(name = "other_services", indexes = { @Index(name = "sms_banking", columnList = "sms_banking"),
		@Index(name = "e_statement", columnList = "e_statement") })
public class OtherServicesInformations implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name_on_card")
	private String nameToBePrintedOnCard;

	@Column(name = "statement_frequency")
	private Frequency accountStatementFrequency;

	@Column(name = "sms_banking")
	private Status smsBankingfacility;

	@Column(name = "e_statement")
	private Status eStatementFacility;

	@Column(name = "msisdn")
	private String phoneNumber;

	@Column(name = "email_facility")
	private Status emailfacility;

	@Column(name = "alternate_email")
	private String alternateEmail;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_by")
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNameToBePrintedOnCard() {
		return nameToBePrintedOnCard;
	}

	public void setNameToBePrintedOnCard(String nameToBePrintedOnCard) {
		this.nameToBePrintedOnCard = nameToBePrintedOnCard;
	}

	public Frequency getAccountStatementFrequency() {
		return accountStatementFrequency;
	}

	public void setAccountStatementFrequency(Frequency accountStatementFrequency) {
		this.accountStatementFrequency = accountStatementFrequency;
	}

	public Status getSmsBankingfacility() {
		return smsBankingfacility;
	}

	public void setSmsBankingfacility(Status smsBankingfacility) {
		this.smsBankingfacility = smsBankingfacility;
	}

	public Status geteStatementFacility() {
		return eStatementFacility;
	}

	public void seteStatementFacility(Status eStatementFacility) {
		this.eStatementFacility = eStatementFacility;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getEmailfacility() {
		return emailfacility;
	}

	public void setEmailfacility(Status emailfacility) {
		this.emailfacility = emailfacility;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
