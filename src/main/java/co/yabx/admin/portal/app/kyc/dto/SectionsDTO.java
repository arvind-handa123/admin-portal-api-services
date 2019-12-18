package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionsDTO implements Serializable {
	private static final long serialVersionUID = 1547547L;

	private Long sectionId;

	private String sectionName;

	private String sectionTitle;

	private Date createdAt;

	private Date updatedAt;

	private boolean enable;

	private String sectionType;

	private String icon;

	private Integer filledFields;

	private Integer totalFields;

	private Long nomineeId;

	private Integer displayOrder;

	private List<PagesDTO> pagesDTOs;

	public String getSectionType() {
		return sectionType;
	}

	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getFilledFields() {
		return filledFields;
	}

	public void setFilledFields(Integer filledFields) {
		this.filledFields = filledFields;
	}

	public Integer getTotalFields() {
		return totalFields;
	}

	public void setTotalFields(Integer totalFields) {
		this.totalFields = totalFields;
	}

	private List<GroupsDTO> groups;

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

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public List<GroupsDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupsDTO> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "AppPagesSectionsDTO [sectionId=" + sectionId + ", sectionName=" + sectionName + ", sectionTitle="
				+ sectionTitle + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", enable=" + enable
				+ ", groups=" + groups + "]";
	}

	public Long getNomineeId() {
		return nomineeId;
	}

	public void setNomineeId(Long nomineeId) {
		this.nomineeId = nomineeId;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<PagesDTO> getPagesDTOs() {
		return pagesDTOs;
	}

	public void setPagesDTOs(List<PagesDTO> pagesDTOs) {
		this.pagesDTOs = pagesDTOs;
	}

}
