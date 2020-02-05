package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupsDTO implements Serializable, Comparable<GroupsDTO> {
	private static final long serialVersionUID = 214321L;

	private Long groupId;

	private String groupName;

	private String groupTitle;

	private Date createdAt;

	private Date updatedAt;

	private Boolean enable;

	private Integer totalFields;

	private Integer filledFields;

	private List<FieldsDTO> fields;

	private Integer displayOrder;

	private Boolean visible;

	private Boolean mandatoryFieldReceived;

	private String filename;

	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isHasSuperset() {
		return hasSuperset;
	}

	public void setHasSuperset(boolean hasSuperset) {
		this.hasSuperset = hasSuperset;
	}

	public Boolean getEnable() {
		return enable;
	}

	public Boolean getVisible() {
		return visible;
	}

	public Boolean getMandatoryFieldReceived() {
		return mandatoryFieldReceived;
	}

	private boolean hasSuperset;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public List<FieldsDTO> getFields() {
		return fields;
	}

	public void setFields(List<FieldsDTO> fields) {
		this.fields = fields;
	}

	public Integer getTotalFields() {
		return totalFields;
	}

	public void setTotalFields(Integer totalFields) {
		this.totalFields = totalFields;
	}

	public Integer getFilledFields() {
		return filledFields;
	}

	public void setFilledFields(Integer filledFields) {
		this.filledFields = filledFields;
	}

	@Override
	public String toString() {
		return "AppPagesSectionGroupsDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupTitle="
				+ groupTitle + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", enable=" + enable
				+ ", fields=" + fields + "]";
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean isMandatoryFieldReceived() {
		return mandatoryFieldReceived;
	}

	public void setMandatoryFieldReceived(Boolean mandatoryFieldReceived) {
		this.mandatoryFieldReceived = mandatoryFieldReceived;
	}

	@Override
	public int compareTo(GroupsDTO groupsDTO) {
		return groupId.compareTo(groupsDTO.getGroupId());

	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
