package co.yabx.admin.portal.app.documents.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.util.PDFGenerator;
import co.yabx.admin.portal.app.util.SpringUtil;

public class COUNTER_GUARANTEE_15 {

	public static String getDocuments(User user) {
		String newFileName = System.currentTimeMillis() + "." + "pdf";
		String path = SpringUtil.bean(AppConfigService.class).getProperty("DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/")
				+ user.getId() + "/" + "disclaimer/";
		File destination = new File(path);
		if (!destination.exists())
			destination.mkdirs();
		Document document = get_PERFORMANCE_SECURITY_FORMAT_Document(path + newFileName,
				"Irrevocable General Power of Attorney (IGPA)- Fixed & Floating");
		if (document != null) {
			SpringUtil.bean(AttachmentService.class).saveAttachments(user, "IGPA_FIXED_AND_FLOATING", newFileName,
					AttachmentType.valueOf("Disclaimer Document"));
		}
		return newFileName;
	}

	public static Document get_PERFORMANCE_SECURITY_FORMAT_Document(String path, String title) {
		Document document = new Document(PageSize.A4);
		document.addTitle(title);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.add(PDFGenerator.getLeftAlignedParagraph("Date:      ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));
			document.add(PDFGenerator.getCenteredBoldParagraph(
					"REQUEST FOR ISSUANCE OF GUARANTEE AND COUNTER GUARANTEE FOR THE GUARANTEE ISSUED BY BRAC BANK LIMITED ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir, ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"In consideration of BRAC Bank Limited, at our request, issuing/agreeing to issue a Bank Guarantee bearing No. ……………..……. expiring on …………………………, hereinafter referred to as \"the Guarantee\" for BDT ………………………………… (Taka ……………………………………………………………………………………………………….……) only in favor of  __________________(Name of the Borrower)_____________________ having its office at __________________(Registered Address of the Borrower)_____________________ hereinafter called \"the Beneficiary\", on our behalf, as per the format attached herewith duly certified by us, we jointly and severally hereby agree and undertake and guarantee to you, your successors and assigns, as follows : ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. To pay to you forthwith on your demand in writing by you all and every sum or sums of money which may at any time or times  paid by you under or by virtue of or in connection with the Guarantee together with service charges and all costs, expenses and charges or any renewal or extension or modification thereof. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"2. To keep you fully indemnified and to save and hold you free and harmless in respect of each and all payment(s) made and any obligation(s), liability (ies), losses or damages undertaken or incurred or suffered by you (whether directly or indirectly) and against all actions, proceedings, under or in connection with or by virtue of the Guarantee or any renewal or extension or modification thereof, which you shall be entitled to do at your absolute discretion. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"3. You shall be entitled at your sole discretion and without any reference to us or obtaining our consent to make payment of all or any part of the sum or sums guaranteed by you under or by virtue of or in connection with the Guarantee or any renewal, extension or modification thereof on a request or demand being made on you for the purpose by the beneficiary thereof.  Any request or demand made to or upon you by the beneficiary for payment of any sum of money as aforesaid shall be sufficient authority for you to make such payment. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"4. Notwithstanding the possibility, existence, tendency or continuance of any dispute(s) or difference(s) or of any arbitration proceeding(s) or of any suit(s) or other legal proceeding(s) whatsoever between you and us and the beneficiaries of the said Guarantees and which may, directly or indirectly, arise out of or under or in connection with or pursuant to the Guarantee or which may affect the legality or the validity of the same, the reasonableness or propriety or validity of any such payment(s) made by you to the beneficiary shall not be disputed or questioned by us on any ground whatsoever or howsoever and the same shall be final, conclusive and binding on us in so far as it concerns our liability to you hereunder. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"5. To pay to you forthwith on demand in writing by you all costs, charges and expenses paid or incurred by you in any manner concerning the Guarantee or any renewal or extension or modification thereof and your obligation and liability there under and this Counter Guarantee and your rights hereunder. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"6. You shall be entitled without any further consent from us to debit any of our account or accounts at any of your branches (whether Loan or cash credit or overdraft or savings or current or fixed or short term deposit or any other accounts whatsoever) with the amount(s) of any payment(s) you may make under or by virtue of or in connection with the Guarantee or any renewal or extension or modification thereof.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Once payment of all or any part of the sum or sums guaranteed by the Bank on behalf of us vide Letter of Guarantee/Bank Guarantee No.................. dated ...................... is made to the beneficiary, the said amount paid by the Bank shall be treated as a loan facility availed by us and as a loan liability incurred on us and in case of unavailability of funds in our account(s) or default or non repayment of said guaranteed amount and/or loan amount, the Bank shall be entitled to recover the same from us as per the laws of the land. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"7. This Counter Guarantee shall be absolute, unconditional and irrevocable and shall be binding on us and each of us jointly and severally and also on our respective heirs, legal representatives, successors and assigns jointly and/or severally. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"8. We and each of us shall be liable hereunder to you jointly and/or each severally, and on the death of any one or more of us, his heirs, executors, administrators or legal representatives (as the case may be) shall be likewise liable jointly and/or severally with the survivor or survivors. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"9.  We hereby undertake to have returned to you the original letter of guarantee (said guarantee) duly cancelled by us after the expiry of the guarantee and we further undertake to pay commission at the agreed rate till such time that the said guarantee duly cancelled is returned to you. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"10. The liability incurred by us and each of us hereunder shall not in any manner be prejudiced or affected by any change in our firm/company whether by the death or retirement of any partner or director, or by admission of any new partner or partners or director or directors or shareholder or shareholders or otherwise howsoever even though the firm/company may become a sole proprietary firm, and all the partner(s), director(s) or shareholder(s) for the time being of the firm/company or the sole proprietor and their or his heirs, executors, administrators, or legal representatives, as the case may be, shall be liable to you hereunder, jointly and/or severally. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"11. We agree that if you call upon us demanding payment of any amount hereunder, we shall make the payment to you within 3 (three) business days after receiving your demand and that we shall make the payment without raising any question, objection or dispute. In case we fail to make the payment to you within 3 (three) business working days after receiving your demand notice, we shall become liable to pay to you by way of liquidated damages an amount  of  110%  including the amount of your demand together with all costs and expenses incurred by you in effecting the recovery from us. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"12. This Counter Guarantee shall be governed and construed in all respects in accordance with the laws of Bangladesh and the courts in Bangladesh shall be competent to hear all disputes arising there from and will have the jurisdiction in the matter.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"13. This Counter Guarantee shall remain valid and in force until such time as it is returned to us duly canceled, released and discharged by you in writing, and until then, we shall continue to be bound and liable to you under and in terms of this Counter Guarantee for making payment within 3 (three) business days of receipt of your demand in writing. Our liability under this Counter Guarantee shall continue during its validity period notwithstanding that no claim is lodged with you under the Guarantee. In the event that no claim is lodged with you under the Guarantee, you shall be at liberty to cancel the liability under the guarantee in your books and records, but notwithstanding such action of canceling the liability in your books and records, the same shall not constitute release and discharge of this Counter Guarantee and our liability to you hereunder shall continue until you release and discharge this Counter Guarantee in writing.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"14. We further agree to create such securities in your favor by way of mortgage, charge, hypothecation or otherwise of our properties, as securities for due payment of our liabilities under this Counter Guarantee as you may require us to furnish, and such securities shall not in any manner affect or diminish our liability under this Counter Guarantee, but shall be in addition to this Counter Guarantee. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"If this agreement is signed or otherwise executed by or on behalf of more than one party, the obligation and the liability of such parties shall be deemed to be joint and several unless expressly stated to the contrary. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"It is also understood that any word appearing in the singular will also apply for the plural and vice versa. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"IN WITNESS whereof we executed these presents on the day, month and year first hereinabove written. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph("___________________ ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Authorized Signature ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Witness: ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. ___________________________  2. ___________________________ ", false, true));

			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void main(String[] args) {
		get_PERFORMANCE_SECURITY_FORMAT_Document("D://test/2.pdf", "abc");
	}
}
