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

public class PERSONAL_GUARANTEE_INDIVIDUAL_11 {

	public static String getDocuments(User user) {
		String newFileName = System.currentTimeMillis() + "." + "pdf";
		String path = SpringUtil.bean(AppConfigService.class).getProperty("DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/")
				+ user.getId() + "/" + "disclaimer/pdf/";
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
			document.add(PDFGenerator.getCenteredBoldParagraph("PERSONAL LETTER OF GUARANTEE ", true, true));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"In consideration of your making or continuing advances or otherwise giving or continuing credit or accommodation or other banking facilities to __________________(Name of the Borrower)_____________________ having address at ______________(Registered Address of Borrower)_______________ hereinafter called “the Principal”, I ______________________, S/o __________________, the undersigned, Guarantee to you due repayment within 3 (three) business days after demand of all Monies  which shall at any time be due to you from the Principal, in any shape or form together with interest, charges, costs etc. provided that the total amount recoverable from me under this Guarantee shall be the principal sum of BDT ……………………………..  (Taka ……………………………………) only and I further agree as under : ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. My liability under this Guarantee will be as that of a principal debtor and you may at your option hold me primarily responsible for all the liabilities of the Principal towards you. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"2. In case of delay of or default by the Principal debtor to repay the debt, I undertake to pay the principal amount together with interest accrued thereon and all costs, charges and expenses which the Bank shall incur till the date of final settlement. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"3. This Guarantee shall be a continuing Security binding on me and my personal representative (s) until a written notice of discontinuance is received by me from you and notwithstanding such discontinuance or any release or granting of time or other indulgence by you to the Principal, this Guarantee shall remain a continuing Security as regards my personal representative(s). In case of discontinuance by notice, this Guarantee shall, nevertheless as to the parties giving notice, continue to be binding on them and their personal representative(s) in respect of all the liabilities of the Principal up to the limit above mentioned at the date of receipt of such notice, whether certain or contingent, and also for any credits established for the Principal and for all instruments, drawn on you or accepted by you for the benefit of the Principal and purporting to be dated on or before the date of receipt of the notice though actually paid or honored after that date.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"4. This Guarantee is and shall  deemed to be additional and without prejudice to any other guarantee(s) or securities given by the Principal in respect of any indebtedness covered by this Guarantee.",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"5. This Guarantee shall not be discharged or prejudiced by any partial payment or settlement of amounts or the existence of a credit balance of the Principal at any time or by discharge of the Principal by operation of law or for any other reason.  6. This Guarantee shall not be considered as satisfied by any intermediate payment or satisfaction of the whole or any part of any sum or sums of money remaining unpaid as aforesaid but shall be a continuing security and shall extend to cover any sum or sums of money which shall for the time being constitute the balance due from the Principal to the Bank upon any such account as herein mentioned.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"6. You may as you think fit and without reference to me grant the Principal time or other indulgence or make or accept any arrangement or composition with the Principal in respect of any indebtedness hereby guaranteed, and also vary, renew, release, realize or in any way deal with any securities or rights now or hereafter held by you in respect of the indebtedness of the Principal towards you. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"7. You may deal with any dividends, partial payments etc. received in respect of the indebtedness hereby guaranteed, and also with any securities held or proceeds thereof, as you may deem fit so as to confer on you maximum benefit. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"8. In respect of any securities held by you with regard to the indebtedness hereby guaranteed, I shall not do or cause to be done anything that will impair their value or in case of bankruptcy or insolvency, neither the Principal nor I shall have any right of proof until all the Monies due to you are repaid. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"9. Any accounts settled between you and the Principal as well as any statement of the Bank regarding the amount due to you at any time will be accepted by me as conclusive evidence to the extent of my liability under this Guarantee. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"10. A certificate in writing signed by an officer of the Bank as to the money and liabilities for the time being due and remaining or incurred to the Bank from or by the Principal shall be final and conclusive evidence in any legal proceedings against me, my executor(s), administrator(s) and legal representative(s) in all courts of law and elsewhere. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"11. My liability hereunder shall not be discharged, released, impaired, altered or otherwise affected by (a) time, forbearance, or other indulgence given or agreed to be given by the Bank to the Principal, (b) by reason of any compromise or arrangement between the Bank and the Principal, (c) any other act, event or omission which but for this provision might operate to impair, release, discharge, alter or affect my liability hereunder. (d) any change in the constitution, structure or powers of the Principal, (e) any disputes or differences of whatsoever nature between the Bank and the Principal or any claims of whatsoever nature of the Principal against the Bank, or (f) any circumstances or considerations which might otherwise constitute a legal or equitable discharge of a surety or guarantor. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"12. The Bank shall have a lien on all securities belonging to me whether furnished in connection with the indebtedness guaranteed hereby or otherwise now or hereafter held by the Bank so long as any Monies remain unpaid hereunder.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"13. In the case of the Principal being a company/firm, any change in its constitution shall not affect my liability hereunder. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"14. Where the Principal purports to act on behalf of another person or corporation or company or firm you shall not be bound to enquire into powers of such Principal, and all Monies borrowed by him/them will be covered by this Guarantee notwithstanding any absence or insufficiency of or irregularity in the exercise of the powers.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"15. If the name of the Principal hereinabove inserted is that of either a firm or of a limited company or any other corporation or of any committee or association or other un-incorporated body, any of the provisions herein contained which are primarily and liberally applicable to the case of a single and individual Principal only shall be construed and take effect so as to give the Bank hereunder a guarantee for the money owing from that firm and every member thereof or from that limited company or corporation or committee or association or other unincorporated body as identical or analogous as may be with or to that which would have been given for the money owing from a single individual if the Principal had been a single individual and any money shall be deemed to be so owing notwithstanding any defect, informality or insufficiency in the borrowing powers of the Principal or in the exercise thereof which might be a defense as between the Principal and the Bank. In the case of a firm, this Guarantee shall be deemed to be a continuing guarantee for all Monies owing on any such account as herein mentioned from the person or persons carrying on business in the name of or in succession to the firm or from any one or more of such persons through death, retirement or admission of partners or other causes the constitutions of the firm may have been in part or wholly varied. In case of a limited company or other corporation any reference to bankruptcy shall be deemed to be a reference to liquidation or other analogous proceeding and the money owing as aforesaid and hereby guaranteed shall be deemed to include any Monies owing in respect of debentures or debenture stocks of the limited company or other corporation held by or on behalf of the Bank.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"16. This Guarantee shall be in addition to and not in substitution for any other guarantee given by me to the Bank on behalf of the Principal. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"17. Any notice, by way of demand, request or otherwise hereunder may be given to me or left at the last known address/place of business or residence or may be sent to me by post addressed as aforesaid and if sent by post, it shall be deemed to have been duly given and served when it would reach me in due course of post. If, for want of address or otherwise, the notice cannot be given by post, an advertisement in a newspaper shall be deemed sufficient notice given on the day the advertisement appears. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"It is also understood that any word appearing in the singular will also apply for the plural and vice versa. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Signature             : ________________________ ", true,
					false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Name                        : ………………………………………………. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Father’s/Husband’s Name    : ………………………………………….. ", true,
					false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Mother’s Name     : …………………………..", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Permanent Address                    :………………………………………………….. ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"NID No.                                     :……………………………………….. ", true, true));

			document.add(PDFGenerator.getLeftAlignedParagraph("Witness: ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. ___________________________  2. ___________________________ ", false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Name  :                                                     Name  : ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Address :                                                   Address ", true, true));
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
