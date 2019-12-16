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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.yabx.admin.portal.app.kyc.entities.SectionGroupRelationship;

/**
 * Created by Asad Ali on 21/10/2019. Filter table contains configuration for
 * any defined entities. Whether to show the or not is decided by this entity
 * and by different subclass of this entity which are discriminator based column
 * in same Filter table. Example - if we have to show some fields of Distributor
 * and but not Retailers or Vice Versa, then that could be controlled from here-
 * 
 *
 * Now this card is when if all three filter pass
 */
@Entity
@Table(name = "filters")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "group_name", discriminatorType = DiscriminatorType.STRING)
public abstract class Filters implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "group_name", insertable = false, updatable = false)
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@ManyToOne(targetEntity = SectionGroupRelationship.class, fetch = FetchType.LAZY)
	protected SectionGroupRelationship sectionGroupRelationship;

	public abstract boolean filter(String field);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SectionGroupRelationship getSectionGroupRelationship() {
		return sectionGroupRelationship;
	}

	public void setSectionGroupRelationship(SectionGroupRelationship sectionGroupRelationship) {
		this.sectionGroupRelationship = sectionGroupRelationship;
	}

}
