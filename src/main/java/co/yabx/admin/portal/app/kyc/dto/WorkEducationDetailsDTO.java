package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.EducationalQualification;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkEducationDetailsDTO implements Serializable {

	private Long id;

	private String occupation;

	private String designation;

	private String employer;

	private EducationalQualification educationalQualification;

	private Date createdAt;

	private Date updatedAt;

	private String createdBy;

	private String updatedBy;

	private UserDTO user;

	private Integer experience;

	public Long getId() {
		return id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public EducationalQualification getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(EducationalQualification educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "WorkEducationDetailsDTO [id=" + id + ", occupation=" + occupation + ", designation=" + designation
				+ ", employer=" + employer + ", educationalQualification=" + educationalQualification + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", user=" + user + ", experience=" + experience + "]";
	}

}
