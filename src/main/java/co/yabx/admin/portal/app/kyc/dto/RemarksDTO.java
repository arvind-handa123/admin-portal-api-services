package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemarksDTO implements Serializable {
	private static final long serialVersionUID = -663185L;
	private String remark;
	private String fieldId;
	private String side;
	private String fieldValue;
	private Integer groupId;
	private Integer sectionId;
	private Integer pageId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "RemarksDTO [remark=" + remark + ", fieldId=" + fieldId + ", side=" + side + ", fieldValue=" + fieldValue
				+ ", groupId=" + groupId + ", sectionId=" + sectionId + ", pageId=" + pageId + "]";
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
