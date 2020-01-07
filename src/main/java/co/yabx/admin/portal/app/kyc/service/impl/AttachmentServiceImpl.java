package co.yabx.admin.portal.app.kyc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.enums.DocumentSide;
import co.yabx.admin.portal.app.enums.DocumentType;
import co.yabx.admin.portal.app.kyc.dto.AttachmentDetailsDTO;
import co.yabx.admin.portal.app.kyc.dto.AttachmentsDTO;
import co.yabx.admin.portal.app.kyc.entities.AttachmentDetails;
import co.yabx.admin.portal.app.kyc.entities.Attachments;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.repositories.AttachmentDetailsRepository;
import co.yabx.admin.portal.app.kyc.repositories.AttachmentsRepository;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.kyc.service.StorageService;

/**
 * 
 * @author Asad.ali
 *
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentsRepository attachmentsRepository;

	@Autowired
	private AttachmentDetailsRepository attachmentDetailsRepository;

	@Autowired
	private StorageService storageService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Override
	@Transactional
	public AttachmentDetails persistInDb(User user, MultipartFile file, String saveFileName, boolean checkFileName,
			String docType) throws Exception {
		LOGGER.info("File={} saved for retailer={}", file.getOriginalFilename(), user.getId());
		String fileName = file.getOriginalFilename().replaceAll(" ", "_");
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		Set<Attachments> attachmentList = new HashSet<Attachments>();
		DocumentType documentType = null;
		DocumentSide documentSide = null;
		AttachmentDetails attachmentDetails = null;
		Attachments attachments = null;
		AttachmentType attachmentType = null;
		boolean isNew = false;
		String[] fileNames = null;
		if (checkFileName) {
			fileNames = fileName.replaceAll("." + extension, "").split("-");
		} else {
			attachmentType = AttachmentType.DisclaimerDocument;
		}
		if (checkFileName && fileNames != null && fileNames.length > 1) {
			for (String name : fileNames) {
				if (name.equalsIgnoreCase("DRIVING_LICENSE") || name.equalsIgnoreCase("DRIVING LICENSE")) {
					documentType = DocumentType.DRIVING_LICENSE;
				} else if (name.equalsIgnoreCase("PASSPORT")) {
					documentType = DocumentType.PASSPORT;
				} else if (name.equalsIgnoreCase("front")) {
					documentSide = DocumentSide.FRONT;
				} else if (name.equalsIgnoreCase("back")) {
					documentSide = DocumentSide.BACK;
				} else if (name.equalsIgnoreCase("idProof")) {
					attachmentType = AttachmentType.IdentityProof;
				} else if (name.equalsIgnoreCase("addressProof")) {
					attachmentType = AttachmentType.AddressProof;
				}
			}
		} else if (checkFileName) {
			if (fileNames[0].equalsIgnoreCase("tinCertificates")) {
				documentType = DocumentType.TIN_CERTIFICATE;
			} else if (fileNames[0].equalsIgnoreCase("tradeLicense")) {
				documentType = DocumentType.TRADE_LICENSE;
			} else if (fileNames[0].equalsIgnoreCase("nomineePhoto")) {
				documentType = DocumentType.NOMINEE_PHOTO;
			} else if (fileNames[0].equalsIgnoreCase("signature")) {
				documentType = DocumentType.SIGNATURE;
			}
		}
		if (documentType != null) {
			if (attachmentType != null) {
				attachmentDetails = attachmentDetailsRepository.findByUserAndDocumentTypeAndAttachmentType(user,
						documentType, attachmentType);
			} else
				attachmentDetails = attachmentDetailsRepository.findByUserAndDocumentType(user, documentType);
			if (attachmentDetails == null) {
				attachmentDetails = new AttachmentDetails();
				isNew = true;
			}
			if (documentSide != null) {
				if (documentSide == DocumentSide.FRONT) {
					Optional<Attachments> frontDoc = attachmentDetails.getAttachments().stream().filter(f -> f != null
							&& f.getDocumentSide() != null && f.getDocumentSide().equals(DocumentSide.FRONT))
							.findFirst();
					if (frontDoc.isPresent())
						attachments = frontDoc.get();
				} else {
					Optional<Attachments> BackDoc = attachmentDetails.getAttachments().stream().filter(f -> f != null
							&& f.getDocumentSide() != null && f.getDocumentSide().equals(DocumentSide.BACK))
							.findFirst();
					if (BackDoc.isPresent())
						attachments = BackDoc.get();
				}
			} else {
				Optional<Attachments> optional = attachmentDetails.getAttachments().stream().findFirst();
				if (optional.isPresent())
					attachments = optional.get();
			}
			if (attachments == null && isNew) {
				attachments = new Attachments();
				if (documentSide != null) {
					attachments.setDocumentSide(documentSide);
				}
				attachments.setDocumentUrl(saveFileName);
				attachmentList.add(attachments);
				attachmentDetails.setAttachments(attachmentList);
				attachmentDetails.setDocumentType(documentType != null ? documentType.toString() : null);
				attachmentDetails.setAttachmentType(attachmentType);
				attachmentDetails.setUser(user);
				attachmentDetails = attachmentDetailsRepository.save(attachmentDetails);
				return attachmentDetails;
			} else if (!isNew && attachments == null) {
				attachments = new Attachments();
				if (documentSide != null) {
					attachments.setDocumentSide(documentSide);
				}
				attachments.setDocumentUrl(saveFileName);
				// attachments.setAttachmentDetails(attachmentDetails);
				attachmentsRepository.save(attachments);
			} else {
				attachments.setDocumentUrl(saveFileName);
				attachmentsRepository.save(attachments);
			}
			LOGGER.info("File={} saved for retailer={}, detailes={}", file.getOriginalFilename(), user.getId(),
					attachmentDetails);
			return attachmentDetails;
		} else {
			documentType = DocumentType.valueOf(docType);
			attachmentDetails = getDisclaimerAttachement(user, attachmentType, saveFileName);
			if (attachmentDetails != null) {
				Set<Attachments> attachmentsSet = attachmentDetails.getAttachments();
				Optional<Attachments> optional = attachmentsSet.stream()
						.filter(f -> saveFileName.equalsIgnoreCase(f.getDocumentUrl())).findFirst();
				if (optional.isPresent())
					attachments = optional.get();
			} else {
				attachmentDetails = new AttachmentDetails();
				attachments = new Attachments();
			}
			if (attachments == null) {
				attachments = new Attachments();
			}
			attachments.setDocumentUrl(saveFileName);
			attachmentList.add(attachments);
			attachmentDetails.setAttachments(attachmentList);
			attachmentDetails.setDocumentType(documentType != null ? documentType.toString() : docType);
			attachmentDetails.setAttachmentType(attachmentType);
			attachmentDetails.setUser(user);
			attachmentDetails = attachmentDetailsRepository.save(attachmentDetails);
			return attachmentDetails;
		}

	}

	private AttachmentDetails getDisclaimerAttachement(User user, AttachmentType attachmentType, String fileName) {
		List<AttachmentDetails> attachmentDetailsList = attachmentDetailsRepository.findByUserAndAttachmentType(user,
				attachmentType);
		for (AttachmentDetails details : attachmentDetailsList) {
			for (Attachments attachment : details.getAttachments()) {
				String attachmentFilename = attachment.getDocumentUrl();
				if (fileName.equalsIgnoreCase(attachmentFilename)) {
					return details;
				}
			}
		}
		return null;
	}

	@Override
	public AttachmentDetails persistDsrProfilePicInDb(User user, MultipartFile files, String saveFileName) {
		if (user != null) {
			AttachmentDetails attachmentDetails = attachmentDetailsRepository.findByUserAndDocumentType(user,
					DocumentType.PROFILE_PIC);
			Set<Attachments> attachmentsSet = null;
			if (attachmentDetails != null) {
				attachmentsSet = attachmentDetails.getAttachments();
				attachmentsSet.clear();
			} else {
				attachmentDetails = new AttachmentDetails();
				attachmentDetails.setDocumentType(DocumentType.PROFILE_PIC.toString());
				attachmentsSet = new HashSet<Attachments>();
			}
			Attachments attachments = new Attachments();
			attachments.setDocumentUrl(saveFileName);
			attachmentsSet.add(attachments);
			attachmentDetails.setAttachments(attachmentsSet);
			attachmentDetails.setUser(user);
			attachmentDetails = attachmentDetailsRepository.save(attachmentDetails);
			return attachmentDetails;
		}
		return null;
	}

	@Override
	public String fetchDsrProfilePic(User user) {
		AttachmentDetails attachmentDetails = attachmentDetailsRepository.findByUserAndDocumentType(user,
				DocumentType.PROFILE_PIC);
		if (attachmentDetails != null) {
			Set<Attachments> attachments = attachmentDetails.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				Optional<Attachments> optional = attachments.stream().findFirst();
				if (optional.isPresent()) {
					return optional.get().getDocumentUrl();
				}
			}
		}

		return null;
	}

	@Override
	public List<AttachmentDetailsDTO> getRetailerDocuments(User user) {
		if (user != null) {
			List<AttachmentDetails> attachmentDetailsList = attachmentDetailsRepository.findByUser(user);
			List<AttachmentDetailsDTO> attachmentDetailsDTOs = new ArrayList<AttachmentDetailsDTO>();
			if (attachmentDetailsList != null && !attachmentDetailsList.isEmpty()) {
				for (AttachmentDetails attachmentDetails : attachmentDetailsList) {
					AttachmentDetailsDTO attachmentDetailsDTO = new AttachmentDetailsDTO();
					attachmentDetailsDTO.setDocumentType(
							attachmentDetails.getDocumentType() != null ? attachmentDetails.getDocumentType().toString()
									: null);
					attachmentDetailsDTO
							.setAttachments(prepareAttachmentDTO(attachmentDetails.getAttachments(), user.getId()));
					attachmentDetailsDTO.setAttachmentType(attachmentDetails.getAttachmentType() != null
							? attachmentDetails.getAttachmentType().toString()
							: null);
					attachmentDetailsDTOs.add(attachmentDetailsDTO);
				}
			}
			return attachmentDetailsDTOs;
		}
		return null;
	}

	private List<AttachmentsDTO> prepareAttachmentDTO(Set<Attachments> attachmentsSet, Long retailerId) {
		List<AttachmentsDTO> attachmentsDTOs = new ArrayList<AttachmentsDTO>();
		if (attachmentsSet != null && !attachmentsSet.isEmpty()) {
			for (Attachments attachments : attachmentsSet) {
				AttachmentsDTO attachmentsDTO = new AttachmentsDTO();
				attachmentsDTO.setDocumentSide(attachments.getDocumentSide());
				try {
					attachmentsDTO
							.setByteArray(storageService.getImage(attachments.getDocumentUrl(), retailerId, false));
					attachmentsDTOs.add(attachmentsDTO);
				} catch (Exception e) {
					LOGGER.error("Exception while fetching images={},error={}", attachments.getDocumentUrl(),
							e.getMessage());
				}
			}
			return attachmentsDTOs;
		}
		return null;
	}

}
