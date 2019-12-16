package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.FunctionalityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Functionality implements Serializable {
	private Long id;
	private FunctionalityType type;
	private Long fromGroup;
	private Long toGroup;
	private Integer fieldToCompare;
	private Integer minThreshold;
	private Integer maxThreshold;

	public FunctionalityType getType() {
		return type;
	}

	public void setType(FunctionalityType type) {
		this.type = type;
	}

	public Long getFromGroup() {
		return fromGroup;
	}

	public void setFromGroup(Long fromGroup) {
		this.fromGroup = fromGroup;
	}

	public Long getToGroup() {
		return toGroup;
	}

	public void setToGroup(Long toGroup) {
		this.toGroup = toGroup;
	}

	public Integer getFieldToCompare() {
		return fieldToCompare;
	}

	public void setFieldToCompare(Integer fieldToCompare) {
		this.fieldToCompare = fieldToCompare;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinThreshold() {
		return minThreshold;
	}

	public void setMinThreshold(Integer minThreshold) {
		this.minThreshold = minThreshold;
	}

	public Integer getMaxThreshold() {
		return maxThreshold;
	}

	public void setMaxThreshold(Integer maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

}
