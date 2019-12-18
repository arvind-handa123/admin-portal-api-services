package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.enums.KycStatus;
import co.yabx.admin.portal.app.enums.PageType;
import co.yabx.admin.portal.app.enums.Relationship;
import co.yabx.admin.portal.app.enums.UserType;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.kyc.entities.AccountStatuses;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.entities.UserRelationships;
import co.yabx.admin.portal.app.kyc.repositories.AccountStatusesRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRelationshipsRepository;
import co.yabx.admin.portal.app.kyc.repositories.UserRepository;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.kyc.service.UserService;

@Service
public class KYCServiceImpl implements KYCService {

	@Autowired
	private AccountStatusesRepository accountStatusesRepository;

	@Autowired
	private UserRelationshipsRepository userRelationshipsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(KYCServiceImpl.class);

	public List<PagesDTO> findAllRetailers() {
		List<UserRelationships> dsrRetailersRelationships = userRelationshipsRepository
				.findByRelationship(Relationship.RETAILER);
		List<PagesDTO> pagesDTOs = new ArrayList<PagesDTO>();
		for (UserRelationships userRelationships : dsrRetailersRelationships) {
			pagesDTOs.addAll(userService.getUserDetails(userRelationships.getRelative(), PageType.RETAILERS));
		}
		return pagesDTOs;
	}

	@Override
	public List<User> fetchRetailersByKycStatus(KycStatus kycStatus) {
		List<User> usersList = userRepository.findByUserType(UserType.RETAILERS.name());
		List<User> userList = new ArrayList<User>();
		for (User user : usersList) {
			if (user.getMsisdn() != null && !user.getMsisdn().isEmpty()) {
				AccountStatuses accountStatuses = accountStatusesRepository.findByMsisdn(user.getMsisdn());
				if (accountStatuses != null && accountStatuses.getKycVerified() != null
						&& accountStatuses.getKycVerified() == kycStatus) {
					userList.add(user);
				}
			}
		}
		return userList;
	}

}
