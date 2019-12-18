package co.yabx.admin.portal.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.admin.entities.Pages;
import co.yabx.admin.portal.app.admin.entities.ProductConfigurations;
import co.yabx.admin.portal.app.admin.repositories.ProductConfigurationRepository;
import co.yabx.admin.portal.app.dto.dtoHelper.PagesDTOHeper;
import co.yabx.admin.portal.app.kyc.dto.PagesDTO;
import co.yabx.admin.portal.app.service.AdminPortalService;

@Service
public class AdminPortalServiceImpl implements AdminPortalService {

	@Autowired
	private ProductConfigurationRepository productConfigurationRepository;

	@Override
	public List<PagesDTO> fetchProductDetails(Long productId) {
		Optional<ProductConfigurations> productConfigurations = productConfigurationRepository.findById(productId);
		List<PagesDTO> appPagesDTOList = new ArrayList<PagesDTO>();
		if (productConfigurations.isPresent()) {
			Set<Pages> appPages = productConfigurations.get().getPages();
			for (Pages pages : appPages) {
				appPagesDTOList.add(PagesDTOHeper.prepareAppPagesDto(pages));
			}
			return appPagesDTOList;
		}
		return null;
	}

}
