package co.yabx.admin.portal.app.admin.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Question database table.
 * 
 */
@Entity
@Table(name = "groups", indexes = { @Index(name = "group_name", columnList = "group_name") })
public class Groups implements Serializable {
	private static final long serialVersionUID = 214321L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long groupId;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "group_title")
	private String groupTitle;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "enable", columnDefinition = "boolean default true")
	private boolean enable;

	@Column(name = "total_fields")
	private Integer totalFields;

	@OneToMany(mappedBy = "groups", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Fields> appDynamicFields;

	@Column(name = "display_order")
	private Integer displayOrder;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Sections.class)
	private Sections sections;

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Set<Fields> getAppDynamicFields() {
		return appDynamicFields;
	}

	public void setAppDynamicFields(Set<Fields> appDynamicFields) {
		this.appDynamicFields = appDynamicFields;
	}

	public Integer getTotalFields() {
		return totalFields;
	}

	public void setTotalFields(Integer totalFields) {
		this.totalFields = totalFields;
	}

	public Sections getSections() {
		return sections;
	}

	public void setSections(Sections sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return "AppPagesSectionGroups [groupId=" + groupId + ", groupName=" + groupName + ", groupTitle=" + groupTitle
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", enable=" + enable + ", appDynamicFields="
				+ appDynamicFields + "]";
	}

}
