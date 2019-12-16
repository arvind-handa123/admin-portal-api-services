package co.yabx.admin.portal.app.kyc.entities.filter;

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

import co.yabx.admin.portal.app.kyc.entities.Fields;

/**
 * 
 * @author Asad.ali
 *
 */
@Entity
@Table(name = "operations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "operation_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Operations implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "operation_type", insertable = false, updatable = false)
	private String operationType;

	@JoinColumn(name = "fields")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Fields.class)
	Fields fields;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "compare_with_field")
	private Integer compareWith;

	@Column(name = "min_threshold")
	private Integer minThreshold;

	@Column(name = "max_threshold")
	private Integer maxThreshold;

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

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public Integer getCompareWith() {
		return compareWith;
	}

	public void setCompareWith(Integer compareWith) {
		this.compareWith = compareWith;
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

	@Override
	public String toString() {
		return "Operations [id=" + id + ", operationType=" + operationType + ", fields=" + fields + ", displayOrder="
				+ displayOrder + ", compareWith=" + compareWith + "]";
	}

}
