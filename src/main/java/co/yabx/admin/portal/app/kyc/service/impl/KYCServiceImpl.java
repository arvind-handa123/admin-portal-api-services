package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.kyc.dto.KycDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.KycDocumentsDTO;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.KYCService;
import co.yabx.admin.portal.app.util.EncoderDecoderUtil;

@Service
public class KYCServiceImpl implements KYCService {

	@Autowired
	private AppConfigService appConfigService;

	private static final Logger LOGGER = LoggerFactory.getLogger(KYCServiceImpl.class);

}
