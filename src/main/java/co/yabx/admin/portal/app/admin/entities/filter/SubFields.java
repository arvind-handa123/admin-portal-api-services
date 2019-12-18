package co.yabx.admin.portal.app.admin.entities.filter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.yabx.admin.portal.app.admin.entities.Fields;


/**
 * 
 * @author Asad.ali
 *
 */
@Entity
@Table(name = "sub_fields")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "field_type", discriminatorType = DiscriminatorType.STRING)
public abstract class SubFields implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "field_type", insertable = false, updatable = false)
	private String fieldType;

	@ManyToOne(targetEntity = Fields.class, fetch = FetchType.LAZY)
	protected Fields parent;

	@JoinColumn(name = "child")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Fields.class)
	Fields child;

	@Column(name = "display_order")
	private Integer displayOrder;

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Fields getParent() {
		return parent;
	}

	public void setParent(Fields parent) {
		this.parent = parent;
	}

	public Fields getChild() {
		return child;
	}

	public void setChild(Fields child) {
		this.child = child;
	}

}
