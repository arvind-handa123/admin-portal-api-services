package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.yabx.admin.portal.app.enums.ControlType;
import co.yabx.admin.portal.app.enums.DataType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldsDTO implements Serializable {
	private static final long serialVersionUID = 1588962L;

	private Long id;

	private Long userId;

	private ControlType type;

	private String fieldId;

	private DataType dataType;

	private String fieldName;

	private String placeHolderText;

	private Object savedData;

	private String validation;

	private Date createdAt;

	private Date updatedAt;

	private Boolean camera;

	private Boolean editable;

	private Boolean mandatory;

	private String response;

	private String defaultValue;

	private List<String> options;

	private List<SubFieldsDTO> subFields;

	private Functionality functionality;

	private Integer displayOrder;

	private String help;

	private String remark;

	private Boolean enableFutureDates;

	private Boolean enablePastDates;

	private Boolean internationalRepresentation;

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

	public ControlType getType() {
		return type;
	}

	public void setType(ControlType type) {
		this.type = type;
	}

	public String getPlaceHolderText() {
		return placeHolderText;
	}

	public void setPlaceHolderText(String placeHolderText) {
		this.placeHolderText = placeHolderText;
	}

	public Object getSavedData() {
		return savedData;
	}

	public void setSavedData(Object savedData) {
		this.savedData = savedData;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public Boolean isCamera() {
		return camera;
	}

	public void setCamera(Boolean camera) {
		this.camera = camera;
	}

	public Boolean isEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<SubFieldsDTO> getSubFields() {
		return subFields;
	}

	public void setSubFields(List<SubFieldsDTO> subFields) {
		this.subFields = subFields;
	}

	public Functionality getFunctionality() {
		return functionality;
	}

	public void setFunctionality(Functionality functionality) {
		this.functionality = functionality;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public String toString() {
		return "FieldsDTO [id=" + id + ", response=" + response + "]";
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public Boolean getEnableFutureDates() {
		return enableFutureDates;
	}

	public void setEnableFutureDates(Boolean enableFutureDates) {
		this.enableFutureDates = enableFutureDates;
	}

	public Boolean getEnablePastDates() {
		return enablePastDates;
	}

	public void setEnablePastDates(Boolean enablePastDates) {
		this.enablePastDates = enablePastDates;
	}

	public Boolean getInternationalRepresentation() {
		return internationalRepresentation;
	}

	public void setInternationalRepresentation(Boolean internationalRepresentation) {
		this.internationalRepresentation = internationalRepresentation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
