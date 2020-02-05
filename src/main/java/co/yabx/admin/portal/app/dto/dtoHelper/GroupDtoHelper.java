package co.yabx.admin.portal.app.dto.dtoHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import co.yabx.admin.portal.app.kyc.dto.FieldsDTO;
import co.yabx.admin.portal.app.kyc.dto.Functionality;
import co.yabx.admin.portal.app.kyc.dto.GroupsDTO;
import co.yabx.admin.portal.app.kyc.dto.SectionsDTO;
import co.yabx.admin.portal.app.kyc.dto.SubFieldsDTO;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BankAccountDetails;
import co.yabx.admin.portal.app.kyc.entities.FieldRemarks;
import co.yabx.admin.portal.app.kyc.entities.Fields;
import co.yabx.admin.portal.app.kyc.entities.Groups;
import co.yabx.admin.portal.app.kyc.entities.SectionGroupRelationship;
import co.yabx.admin.portal.app.kyc.entities.Sections;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.filter.Filters;
import co.yabx.admin.portal.app.kyc.entities.filter.SubGroups;
import co.yabx.admin.portal.app.kyc.repositories.SectionGroupRelationshipRepository;
import co.yabx.admin.portal.app.util.SpringUtil;

public class GroupDtoHelper implements Serializable {
	public static List<GroupsDTO> getGroups(User retailers, Map<String, Integer> filledVsUnfilled,
			Sections appPagesSections, User nominee, Set<AddressDetails> userAddressDetailsSet,
			Set<AddressDetails> nomineeAddressDetailsSet, Set<AddressDetails> businessAddressDetailsSet,
			Set<BankAccountDetails> userBankAccountDetailsSet, Set<BankAccountDetails> nomineeBankAccountDetailsSet,
			Set<BankAccountDetails> businessBankAccountDetailsSet, List<FieldRemarks> fieldRemarksList) {
		List<SectionGroupRelationship> sectionGroupRelationships = SpringUtil
				.bean(SectionGroupRelationshipRepository.class).findBySectionId(appPagesSections.getSectionId());
		List<GroupsDTO> groupsDTOList = new ArrayList<GroupsDTO>();
		for (SectionGroupRelationship sectionGroupRelationship : sectionGroupRelationships) {
			if (sectionGroupRelationship.isActive()) {
				Groups appPagesSectionGroups = sectionGroupRelationship.getGroups();
				Set<SubGroups> subGroups = sectionGroupRelationship.getSubGroups();
				Set<Filters> filters = sectionGroupRelationship.getFilters();
				if (subGroups != null && !subGroups.isEmpty()) {
					for (SubGroups subGropus : subGroups) {
						prepareGroups(subGropus, retailers, appPagesSections, nominee, userAddressDetailsSet,
								nomineeAddressDetailsSet, businessAddressDetailsSet, userBankAccountDetailsSet,
								nomineeBankAccountDetailsSet, businessBankAccountDetailsSet, appPagesSectionGroups,
								groupsDTOList, filledVsUnfilled, filters, sectionGroupRelationship, fieldRemarksList);
					}
				} else {
					prepareGroups(null, retailers, appPagesSections, nominee, userAddressDetailsSet,
							nomineeAddressDetailsSet, businessAddressDetailsSet, userBankAccountDetailsSet,
							nomineeBankAccountDetailsSet, businessBankAccountDetailsSet, appPagesSectionGroups,
							groupsDTOList, filledVsUnfilled, filters, sectionGroupRelationship, fieldRemarksList);
				}
			}
		}
		return groupsDTOList;
	}

	private static void prepareGroups(SubGroups subGroups, User retailers, Sections appPagesSections, User nominee,
			Set<AddressDetails> userAddressDetailsSet, Set<AddressDetails> nomineeAddressDetailsSet,
			Set<AddressDetails> businessAddressDetailsSet, Set<BankAccountDetails> userBankAccountDetailsSet,
			Set<BankAccountDetails> nomineeBankAccountDetailsSet, Set<BankAccountDetails> businessBankAccountDetailsSet,
			Groups appPagesSectionGroups, List<GroupsDTO> groupsDTOList, Map<String, Integer> filledVsUnfilled,
			Set<Filters> filters, SectionGroupRelationship sectionGroupRelationship,
			List<FieldRemarks> fieldRemarksList) {

		if (appPagesSectionGroups != null) {
			Map<String, Integer> groups = new HashMap<String, Integer>();
			groups.put("filledFields", 0);
			groups.put("totalFields", 0);
			GroupsDTO appPagesSectionGroupsDTO = new GroupsDTO();
			Set<Fields> appDynamicFieldsSet = appPagesSectionGroups.getAppDynamicFields();
			Filters filter = fieldsTobeFiltered(filters, appPagesSectionGroups.getGroupName());
			if (appDynamicFieldsSet != null && !appDynamicFieldsSet.isEmpty()) {
				List<FieldsDTO> fields = FieldsDtoHelper.getFields(appDynamicFieldsSet, retailers, groups,
						appPagesSections, nominee, userAddressDetailsSet, nomineeAddressDetailsSet,
						businessAddressDetailsSet, userBankAccountDetailsSet, nomineeBankAccountDetailsSet,
						businessBankAccountDetailsSet, subGroups, filter, sectionGroupRelationship, groupsDTOList,
						fieldRemarksList);
				if (subGroups != null) {
					addFunctionality(subGroups, groupsDTOList, fields, appPagesSectionGroupsDTO);
				} else {
					appPagesSectionGroupsDTO.setVisible(true);
				}
				Set<FieldsDTO> appDynamicFieldsDTOs = fields.stream().sorted(Comparator.comparing(FieldsDTO::getId))
						.collect(Collectors.toSet());
				appPagesSectionGroupsDTO.setFields(appDynamicFieldsDTOs.stream()
						.sorted(Comparator.comparing(FieldsDTO::getDisplayOrder)).collect(Collectors.toList()));
				appPagesSectionGroupsDTO.setEnable(appPagesSectionGroups.isEnable());
				if (subGroups != null) {
					appPagesSectionGroupsDTO.setGroupId(subGroups.getId());
				} else {
					appPagesSectionGroupsDTO.setGroupId(appPagesSectionGroups.getGroupId());
				}
				appPagesSectionGroupsDTO.setGroupName(appPagesSectionGroups.getGroupName());
				appPagesSectionGroupsDTO
						.setGroupTitle(subGroups != null && subGroups.getGroupType() != null ? subGroups.getGroupType()
								: appPagesSectionGroups.getGroupTitle());
				if (subGroups != null)
					appPagesSectionGroupsDTO.setDisplayOrder(subGroups.getDisplayOrder());
				else
					appPagesSectionGroupsDTO.setDisplayOrder(appPagesSectionGroups.getDisplayOrder());
				appPagesSectionGroupsDTO.setTotalFields(groups.get("totalFields"));
				appPagesSectionGroupsDTO.setFilledFields(groups.get("filledFields"));
				appPagesSectionGroupsDTO.setMandatoryFieldReceived(isMandatoryFieldReceived(fields));
			}
			groupsDTOList.add(appPagesSectionGroupsDTO);
			filledVsUnfilled.put("filledFields", filledVsUnfilled.get("filledFields") + groups.get("filledFields"));
			filledVsUnfilled.put("totalFields", filledVsUnfilled.get("totalFields") + groups.get("totalFields"));
		}
	}

	private static boolean isMandatoryFieldReceived(List<FieldsDTO> fieldsDtoList) {
		if (fieldsDtoList != null && !fieldsDtoList.isEmpty()) {
			for (FieldsDTO fieldsDTO : fieldsDtoList) {
				if (fieldsDTO.getSubFields() != null && !fieldsDTO.getSubFields().isEmpty()) {
					for (SubFieldsDTO subFieldsDTO : fieldsDTO.getSubFields()) {
						FieldsDTO fields = subFieldsDTO.getFields();
						if (fields.isMandatory()) {
							if (fields.getSavedData() == null)
								return false;
						}
					}
				} else {
					if (fieldsDTO.isMandatory()) {
						if (fieldsDTO.getSavedData() == null)
							return false;
					}
				}
			}
		}
		return true;
	}

	private static void addFunctionality(SubGroups subGroups, List<GroupsDTO> appPagesSectionGroupSet,
			List<FieldsDTO> fields, GroupsDTO appPagesSectionGroupsDTO) {
		Fields field = subGroups.getFields();
		appPagesSectionGroupsDTO.setVisible(subGroups.isVisible());
		if (field != null) {
			FieldsDTO fieldsDTO = getAppDynamicFieldDTO(field);
			Functionality functionality = new Functionality();
			functionality.setType(subGroups.getFunctionalityType());
			// Link group from permanent to present and so on
			functionality.setFromGroup(subGroups.getLinked_group());
			functionality.setToGroup(subGroups.getId());
			fieldsDTO.setFunctionality(functionality);
			fields.add(fieldsDTO);
		}
	}

	private static Filters fieldsTobeFiltered(Set<Filters> filters, String groupName) {
		if (filters != null && !filters.isEmpty() && groupName != null && !groupName.isEmpty()) {
			Optional<Filters> filter = filters.stream().filter(f -> groupName.equalsIgnoreCase(f.getGroupName()))
					.findFirst();
			return filter.isPresent() ? filter.get() : null;
		}
		return null;
	}

	private static FieldsDTO getAppDynamicFieldDTO(Fields dynamicFields) {
		FieldsDTO appDynamicFieldsDTO = new FieldsDTO();
		appDynamicFieldsDTO.setCamera(dynamicFields.isCamera());
		appDynamicFieldsDTO.setDataType(dynamicFields.getDataType());
		appDynamicFieldsDTO.setEditable(dynamicFields.isEditable());
		appDynamicFieldsDTO.setFieldId(dynamicFields.getFieldId());
		appDynamicFieldsDTO.setFieldName(dynamicFields.getFieldName());
		appDynamicFieldsDTO.setId(dynamicFields.getId());
		appDynamicFieldsDTO.setMandatory(dynamicFields.isMandatory());
		appDynamicFieldsDTO.setOptions(dynamicFields.getOptions());
		appDynamicFieldsDTO.setPlaceHolderText(dynamicFields.getPlaceHolderText());
		appDynamicFieldsDTO.setSavedData(dynamicFields.getSavedData());
		appDynamicFieldsDTO.setType(dynamicFields.getType());
		appDynamicFieldsDTO.setValidation(dynamicFields.getValidation());
		appDynamicFieldsDTO.setDisplayOrder(dynamicFields.getDisplayOrder());
		return appDynamicFieldsDTO;
	}

	public static List<GroupsDTO> getGroups(Set<co.yabx.admin.portal.app.admin.entities.Groups> groupSet) {
		List<GroupsDTO> groupsDTOs = new ArrayList<GroupsDTO>();
		for (co.yabx.admin.portal.app.admin.entities.Groups groups : groupSet) {
			GroupsDTO groupsDTO = new GroupsDTO();
			groupsDTO.setGroupId(groups.getGroupId());
			groupsDTO.setGroupName(groups.getGroupName());
			groupsDTO.setGroupTitle(groups.getGroupTitle());
			groupsDTO.setDisplayOrder(groups.getDisplayOrder());
			groupsDTO.setEnable(groups.isEnable());
			groupsDTO.setFilename(groups.getFilename());
			groupsDTO.setLink(groups.getLink());
			groupsDTO.setHasSuperset(groups.isSuperset());
			groupsDTOs.add(groupsDTO);
		}
		return groupsDTOs.stream().sorted(Comparator.comparing(GroupsDTO::getDisplayOrder))
				.collect(Collectors.toList());

	}

}
