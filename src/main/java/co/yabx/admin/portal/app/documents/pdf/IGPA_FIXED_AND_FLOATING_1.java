package co.yabx.admin.portal.app.documents.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import co.yabx.admin.portal.app.enums.AddressType;
import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.kyc.entities.AddressDetails;
import co.yabx.admin.portal.app.kyc.entities.BusinessDetails;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.util.DateUtil;
import co.yabx.admin.portal.app.util.PDFGenerator;
import co.yabx.admin.portal.app.util.SpringUtil;

public class IGPA_FIXED_AND_FLOATING_1 {

	public static String getDocuments(User user) {
		String newFileName = System.currentTimeMillis() + "." + "pdf";
		String path = SpringUtil.bean(AppConfigService.class).getProperty("DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/")
				+ user.getId() + "/" + "disclaimer/pdf/";
		File destination = new File(path);
		if (!destination.exists())
			destination.mkdirs();
		Document document = get_IGPA_FIXED_AND_FLOATING_Document(user, path + newFileName,
				"Irrevocable General Power of Attorney (IGPA)- Fixed & Floating");
		if (document != null) {
			SpringUtil.bean(AttachmentService.class).saveAttachments(user, "IGPA_FIXED_AND_FLOATING", newFileName,
					AttachmentType.DisclaimerDocument);
		}
		return newFileName;
	}

	public static Document get_IGPA_FIXED_AND_FLOATING_Document(User user, String path, String title) {
		Document document = new Document(PageSize.A4);
		document.addTitle(title);
		try {
			LocalDate currentdate = LocalDate.now();
			BusinessDetails businessDetails = getBusinessDetails(user.getBusinessDetails());
			String businessRegisterredAddress = getRegisterredAddress(user.getBusinessDetails());
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			String businessName = businessDetails != null ? businessDetails.getBusinessName() : "--------";
			String partnerName = businessDetails != null ? businessDetails.getDirectorOrPartnerName() : "--------";
			document.open();
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("BEFORE A NOTARY", true, true));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph(
					"IRREVOCABLE GENERAL POWER OF ATTORNEY FOR MOVABLE PROPERTIES", true, false));
			document.add(PDFGenerator
					.getLeftAlignedParagraph("This Irrevocable General Power of Attorney is made at Dhaka on this the "
							+ currentdate.getDayOfMonth() + " th day of the month of " + currentdate.getMonth() + ", "
							+ currentdate.getYear() + " eof the Christian Era.", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("We, " + businessName
					+ ", a proprietorship/ Private Limited/ Partnership concern having its office at "
					+ businessRegisterredAddress
					+ " (hereinafter referred to as the “Principal”), represented by its Proprietor/Partner/ Director (s), "
					+ partnerName
					+ " , who is duly authorized to execute this Irrevocable General Power of Attorney, do hereby state as follows: ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("WHEREAS:", true, false));

			List unorderedList = PDFGenerator.getUnorderedList("(A)  ",
					"By a (i) Letter of Hypothecation of Fixed & Floating Assets by way of fixed charge dated "
							+ DateUtil.getDate()
							+ " , (hereinafter collectively referred to as the “Letter of Hypothecation”) the properties described in the Schedule herein below (hereinafter referred to as the “Properties”) were hypothecated as a continuing security for repayment of the loan facility (hereinafter referred to as the “Facility”) granted to the Principal by:",
					false, false);
			document.add(unorderedList);
			document.add(PDFGenerator.getLeftAlignedSubListParagraph("BRAC BANK LIMITED", true, true, false));
			document.add(PDFGenerator.getLeftAlignedSubListParagraph("Anik Tower,", false, false, false));
			document.add(PDFGenerator.getLeftAlignedSubListParagraph("220/B Tejgaon Gulshan Link Road, ", false, false,
					false));
			document.add(PDFGenerator.getLeftAlignedSubListParagraph(
					"Tejgaon, Dhaka-1208 (hereinafter referred to as the “Lender”). ", false, false, false));
			document.add(PDFGenerator.getLeftAlignedSubListParagraph(
					"on the terms and conditions contained in its Sanction Advice executed by and between the Borrower and the Lender. ",
					false, true, true));
			unorderedList = PDFGenerator.getUnorderedList("(B) ",
					"In order to exercise the powers of sale in relation to the Hypothecated Properties it is necessary for us to execute this Irrevocable General Power of Attorney in favour of the Lender in the manner stipulated hereunder. ",
					false, false);
			document.add(unorderedList);
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Wherever any expressed term is used in this Power of Attorney and not defined hereunder, such term shall have the meaning given to it in the Letters of Hypothecation. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("NOW THEREFORE BY THESE PRESENTS, ",
					false, true,
					"we, _____________________________ ____________________________ in consideration of the Lender granting us the Loan do hereby make, nominate, constitute, ordain and appoint the Lender (hereinafter referred to as the “Attorney”), acting through any authorized signatory or signatories, to be the true and lawful attorney for and on behalf of and in the name of the Principal, with full powers of substitution and delegation, to do, execute and perform, or cause to be done, executed and performed, at any time or from time to time, all or any of the acts, deeds, matters, things and authorities hereinafter mentioned:"));
			unorderedList = PDFGenerator.getUnorderedList("(i) ",
					"Upon the occurrence of an event of default or breach of the Letters of Hypothecation or the Letter of Offer of Facilities; or ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("(ii) ", "At any other time if specified herein; or ", true,
					true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("(iii) ",
					"If the Lender and the Principal otherwise agree that this Power of Attorney should become exercisable;",
					true, true);
			document.add(unorderedList);
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"The rights, powers, acts or discretion conferred by the Principal upon the Attorney are: ", true,
					true));
			unorderedList = PDFGenerator.getUnorderedList("1. ",
					"To take possession of the Properties or any part thereof and to take their entire administration, management, and control. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("2. ",
					"To sell or dispose of the Properties or any part thereof together or in parcel on account and at the risk of the Principal, either privately or by public auction or by private contract on such terms and conditions as the Attorney shall think fit and proper, without any reference to the Principal; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("3. ",
					"To realize and receive the sale proceeds and any other monies receivable in respect of the Properties or any part thereof and apply the same towards the liabilities of the Principal with Lender; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("4. ",
					"To seal, sign and execute the necessary deeds, present those for registration and get the same registered or assigned (as the case may be) and to vest the Properties or any part thereof in any transferee, together with all rights of the owner in, or to the Properties or any part thereof as if the same had been sold to the transferee by the Principal as the owner. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("5. ",
					"To incur any expenditure on behalf of the Principal that may be necessary for taking over, management and control of the Properties or any part thereof and for sale, charge or disposal thereof and to incur any liabilities on behalf of the Principal for the said purpose. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("6. ",
					"To advertise through newspapers or otherwise for auction of the Properties or any part thereof, to arrange and effect the auction or sale to receive the bid money, and apply the same in meeting expenses and in liquidating the indebtedness of the Principal to the Lender. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("7. ",
					"To negotiate for the sale of the Properties or any part thereof, and settle the terms of sale, to sign, seal, execute and deliver all such contracts, agreements, sale deeds or deeds of conveyance and/or other documents, and/or instruments of transfer with all necessary and reasonable covenants therein on behalf of the Principal, and generally ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("8. ",
					"To do and perform all other acts, matters, and things that may be necessary or proper for completing the sale or disposal of the Properties or any part thereto; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("9. ",
					"To realize, receive and take payment of any consideration or purchase money or other moneys that may become payable to the Principal in connection with such sale or disposal as aforesaid, and upon receipt thereof to give and grant sufficient and effectual receipts or discharges for the same; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("10. ",
					"To collect, receive and take in respect of the Properties payment of any receivables or book debts of the Principal or other moneys that may become payable to the Principal from any person, firm, company or authority and upon receipt thereof to give and grant sufficient and effectual receipts or discharges for the same;To represent the Principal before any authorities, the Bangladesh Bank or any other person in connection with the transactions referred to herein above, and sign and execute whatever instruments that may be necessary for obtaining the approval, if required, of such transactions by any authority or any other organization or institution for the purpose of completion of the sale of the Properties. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("11. ",
					"To appear, represent and act in all civil or criminal Courts or arbitration and before judicial, quasi-judicial, statutory and revenue authorities, either in the original or appellate side as well as in any government department, local authority, autonomous, semi-autonomous authority or other body corporate, and to prosecute or defend or to take part in all or any action, application, suit, appeal, proceeding, and for such purposes to subscribe, sign and verify all plaints, written statements and any memorandum of appeal and to do all acts, deeds and things which may be necessary in relation thereto, and to execute any power or vokalatnamas by signing on behalf of and in the name of the Principal.",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("12. ",
					"To appoint and retain lawyers and advocates and to remove such advocates and retainers from time to time and again to appoint as occasion shall require for the aforesaid purposes.",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("13. ",
					"To apply for withdrawal, withdraw and receive all moneys that may be deposited in any Court or office concerning the Properties or any part thereof in case of acquisition or requisition of the Properties or any part thereof by any Government or other lawful authority. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("14. ",
					"To appear before any registering authority having jurisdiction in that behalf in relation to the Properties for registration and acknowledge and register pursuant to the provisions and regulations in that respect for the time being in force, all instruments and writings including sale deeds, deeds of conveyance, mortgage deeds executed and signed either by the Principal directly or under the authority of these presents and to present for registration and  to admit execution thereof and do all such acts, and deeds in that behalf as the Attorney may believe is proper and expedient.",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("15. ",
					"Without notice to the Principal set off, transfer or apply all or any of the monies from time to time standing to the credit of any account in the name of the Principal or towards the discharge and satisfaction of all sums of money which are, at the time, due or owing to Lender by the Principal. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("16. ",
					"At any time (including, for the avoidance of doubt, prior to the occurrence of an event of default) to sign, execute, seal, deliver, perfect and do all deeds, documents, assurances, instruments, acts and things which the Attorney may consider to be required or desirable in connection with expediting the powers granted herein;",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("17. ",
					"To appoint by deed or in writing under hand any one or more qualified persons from an accounting firm of international standing (or its equivalent) (or such other firm as we, acting reasonably and without undue delay may agree) to be a receiver or manager (the “Receiver”) of all or any part of the Properties on such terms and conditions as the Attorney deems fit and appropriate and to remove any Receiver appointed by it and appoint a new Receiver in its place. This power shall be in addition to all statutory and other powers of appointment under the Code of Civil Procedure, 1908 (Act V of 1908). The Receiver is deemed to be the agent of the Principal for all purposes and Lender shall not incur any liability (either to the Principal or to any other person) by reason of Lender making its appointment of a Receiver or for any other reason. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("18. ",
					"At any time (including, for the avoidance of doubt, prior to the occurrence of an event of default) and from time to time to appoint any substitute or substitutes and to delegate to him or them all or any of the powers, authorities or discretion vested in the Attorney under or by virtue of these presents (other than this power of substitution) and to remove any such substitute or substitutes at pleasure and appoint another or others in his or their place, to do all or any acts, deeds,",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("    ",
					"Matters and things hereunder, as may be necessary, usual, proper or expedient for the purposes hereof. ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("19. ",
					"To do, perform, sign and execute generally each and all other acts, deeds, matters and things, legally and effectively, which the Lender may deem necessary and expedient for any of the purposes aforesaid or otherwise as the Principal could have done if personally present.",
					true, true);
			document.add(unorderedList);
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"AND the Principal do hereby ratify and confirm whatever the said Attorney or any substitute(s) acting under them shall lawfully do or purport to do or cause to be done by virtue of these presents. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"AND the powers conferred on the Attorney hereunder are solely to protect the interests of Lender in the Properties and shall not impose any duty upon the Attorney to exercise any such power. The Attorney shall be accountable only for amounts that are actually received by it as a result of the exercise of such powers, and neither it, nor any of its officers, directors, employees, or agents, shall be responsible to the Principal for any act or failure to act hereunder. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"THE PRINCIPAL HEREBY DECLARES AND ACKNOWLEDGES that the Attorney has interest in the Properties which form the subject matter of this Power of Attorney, and hence this Power of Attorney shall be irrevocable, and shall not be terminated, revoked or rescinded by the Principal and its successors and assigns or any other person for any reason whatsoever until the Principal’s indebtedness to all the Lender is fully adjusted and settled and all the Lender release the Principal absolutely from all liabilities whatsoever, and until that time we shall not execute any other power of attorney in favour of any other person in connection with the Properties. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"THE PRINCIPAL FURTHER DECLARES that this Irrevocable Power of Attorney is valid and binding on the Principal, its successors-in interest and assignees.",
					true, true));
			document.newPage();
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("SCHEDULE OF THE PROPERTIES ", true, true));
			unorderedList = PDFGenerator.getUnorderedList("(A)   ",
					"       All plant, machinery, spare parts, accessories, electrical equipments, all other movable properties and assets acquired or to be acquired by availing the Term Loan as described in the annexed List of Plant and Machinery and the documents constituting or evidencing title to or control over or otherwise relating to such property, in which the Borrower may from time to time have an interest, and all policies of insurance relating thereto and all sale proceeds thereof, of the Principal; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("(B)   ",
					"       All present and future plant, machinery, spare parts, accessories, electrical equipments, all other movable properties and assets and like assets and other documents constituting or evidencing title to or control over or otherwise relating to such property, in which the Borrower may from time to time have an interest, and all policies of insurance relating thereto and all sale proceeds thereof, of the Principal; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("(C)   ",
					"       All present and future movable property, including furniture and fixtures, office equipment, stocks of raw materials, work-in-process, finished goods, lease assets, vehicles and all other assets, wherever situated (including goods in transit), and all negotiable instruments, bills of lading, railway receipts, warrants, delivery orders, wharfingers’ or other warehousekeepers’ certificates and other documents constituting or evidencing title to or control over or otherwise relating to such property, and all policies of insurance relating thereto and all sale proceeds thereof, of the Principal; ",
					true, true);
			document.add(unorderedList);
			unorderedList = PDFGenerator.getUnorderedList("(D)   ",
					"       All present and future book debts, outstandings, monies, receivables, claims, bills, contracts, engagements, securities, rights and like assets and other documents constituting or evidencing title to or control over or otherwise relating to such property, and all proceeds thereof of the Principal and in which the Principal may have an interest; and ",
					true, true);
			document.add(unorderedList);
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("IN WITNESS WHEREOF ", false, false,
					"the Principal has executed this Power of Attorney on the day, month and year first written above. "));
			document.add(PDFGenerator.getRightAlignedBoldContentWithNonBoldContent(
					"Signed, sealed and delivered by,                         ", false, false, "In witness whereof:"));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"______________________________                 1. _______________________ ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"through its authorized signatory                           Name: ", false, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"By ________________________                        Address:", false, false));
			document.add(PDFGenerator.getRightAlignedParagraph("2.________________________  ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Name:                                                                   Name:", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Designation:                                                          Address: ", false, false));

			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return document;
	}

	private static String getRegisterredAddress(Set<BusinessDetails> businessDetails) {
		AddressDetails addressDetails = getBusinessRegisterredAddress(businessDetails);
		if (addressDetails != null) {
			return addressDetails.getAddress() + ", " + addressDetails.getUpazilaThana() + ", "
					+ addressDetails.getCityDsitrict() + ", " + addressDetails.getDivision() + ", "
					+ addressDetails.getZipCode() + ", " + addressDetails.getLandmark() + ", "
					+ addressDetails.getCountry();
		}
		return "-------";
	}

	private static BusinessDetails getBusinessDetails(Set<BusinessDetails> businessDetails) {
		Optional<BusinessDetails> optional = businessDetails.stream().findFirst();
		return optional.isPresent() ? optional.get() : null;
	}

	private static AddressDetails getBusinessRegisterredAddress(Set<BusinessDetails> businessDetails) {
		BusinessDetails details = getBusinessDetails(businessDetails);
		if (details != null) {
			Set<AddressDetails> set = details.getAddressDetails();
			Optional<AddressDetails> addressDetailsOptional = set != null
					? set.stream().filter(f -> AddressType.BUSINESS_REGISTERED_ADDRESS.equals(f.getAddressType()))
							.findFirst()
					: Optional.empty();
			return addressDetailsOptional.isPresent() ? addressDetailsOptional.get() : null;
		}
		return null;
	}
}
