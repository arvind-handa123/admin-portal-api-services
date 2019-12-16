package co.yabx.admin.portal.app.kyc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the Question database table.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long pageId;

	private String pageName;

	private String pageTitle;

	private Date createdAt;

	private Date updatedAt;

	private boolean enable;

	private String pageType;

	private String icon;

	private Integer filledFields;

	private Integer totalFields;

	private List<SectionsDTO> sections;

	private String pageCompletion;

	private Long retailerId;

	private Long dsrId;

	private ActionDTO action;

	private Integer displayOrder;

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public List<SectionsDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionsDTO> sections) {
		this.sections = sections;
	}

	public String getPageCompletion() {
		return pageCompletion;
	}

	public void setPageCompletion(String pageCompletion) {
		this.pageCompletion = pageCompletion;
	}

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	public Long getDsrId() {
		return dsrId;
	}

	public void setDsrId(Long dsrId) {
		this.dsrId = dsrId;
	}

	public ActionDTO getAction() {
		return action;
	}

	public void setAction(ActionDTO action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "AppPagesDTO [pageId=" + pageId + ", pageName=" + pageName + ", pageTitle=" + pageTitle + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", enable=" + enable + ", pageType=" + pageType + ", icon="
				+ icon + ", filledFields=" + filledFields + ", totalFields=" + totalFields + ", sections=" + sections
				+ ", pageCompletion=" + pageCompletion + ", retailerId=" + retailerId + ", dsrId=" + dsrId + ", action="
				+ action + "]";
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}
