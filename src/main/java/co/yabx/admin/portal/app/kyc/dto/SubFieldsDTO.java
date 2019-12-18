package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * 
 * @author Asad.ali
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubFieldsDTO implements Serializable {

	private Long id;

	private String fieldType;

	private FieldsDTO parent;

	private FieldsDTO fields;

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FieldsDTO getParent() {
		return parent;
	}

	public void setParent(FieldsDTO parent) {
		this.parent = parent;
	}

	public String getFieldType() {
		return fieldType;
	}

	public FieldsDTO getFields() {
		return fields;
	}

	public void setFields(FieldsDTO fields) {
		this.fields = fields;
	}

}
