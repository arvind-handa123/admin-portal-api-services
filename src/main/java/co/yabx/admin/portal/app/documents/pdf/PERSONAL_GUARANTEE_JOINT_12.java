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

public class PERSONAL_GUARANTEE_JOINT_12 {

	public static String getDocuments(User user) {
		String newFileName = System.currentTimeMillis() + "." + "pdf";
		String path = SpringUtil.bean(AppConfigService.class).getProperty("DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/")
				+ user.getId() + "/" + "disclaimer/pdf/";
		File destination = new File(path);
		if (!destination.exists())
			destination.mkdirs();
		Document document = get_PERFORMANCE_SECURITY_FORMAT_Document(path + newFileName,
				"PERSONAL_GUARANTEE_JOINT_12");
		if (document != null) {
			SpringUtil.bean(AttachmentService.class).saveAttachments(user, "PERSONAL_GUARANTEE_JOINT", newFileName,
					AttachmentType.DisclaimerDocument);
		}
		return newFileName;
	}

	public static Document get_PERFORMANCE_SECURITY_FORMAT_Document(String path, String title) {
		Document document = new Document(PageSize.A4);
		document.addTitle(title);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.add(PDFGenerator.getLeftAlignedParagraph("Dated:      ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));
			document.add(PDFGenerator.getCenteredBoldParagraph("PERSONAL GUARANTEE ", false, true));
			document.add(PDFGenerator.getCenteredParagraph("(Joint & Several) ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"In consideration of the Bank making or continuing advances or otherwise giving or continuing credit or accommodation or other banking facilities to the entity, listed in Part 1 of the Schedule (hereinafter referred to as the \"Borrower”, we, the undersigned, particulars of whom have been given in Part 2 of the Schedule (hereinafter referred to as collectively  the \"Guarantors\"  and   individually  as   the “Guarantor” ), do hereby jointly and severally guarantee to the Bank the repayment when due or earlier on written demand by the Bank of all monies advanced to or paid for or on account of the Borrower of an aggregate principal amount, as stated in Part 3 of the Schedule, plus all costs, interests, charges and expenses, including all legal costs and fees incurred by the Bank in relation to the recovery of sums due and payable to the Bank by the Borrower whether before or after the date hereof and remaining unpaid or which shall at any time hereafter be owing or payable to the Bank on any such account or any negotiable instrument or any other account whatsoever by the Borrower whether as principal or surety and whether alone or jointly with any other person and in whatever name or style. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"The Guarantors, jointly and severally, further agree as under: ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. Without prejudice to the Bank’s rights against the Borrower as the principal obligor, the Guarantors shall be deemed principal obligors in respect of their obligations hereunder and not merely surety. Accordingly, the Guarantors shall not be discharged nor shall their liability be discharged or impaired by any act, thing, omission or means whatsoever whereby their liability would have been discharged if they had been merely a secondary obligor. This Guarantee shall remain binding on the Guarantors notwithstanding that all or any of the Borrower’s obligations may not for any reason be valid and binding or capable of enforcement. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"2. If any purported obligation or liability of any Guarantor which would have been the subject of this Guarantee had it been valid and enforceable is not or ceases to be valid or enforceable, then all the other Guarantors shall be held responsible of that purported obligation or liability as if the same were fully valid and enforceable. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"3. This Guarantee shall be a continuing Guarantee and shall not be considered as either wholly or partially satisfied by the receipt by the Bank of any sums at any time in payment or discharge wholly or partly of the said moneys or of the debts for the time being owed by the Borrower but shall extend to cover all moneys which shall at any time hereafter be advanced to or paid for or on account of the Borrower or be owing or payable to the Bank by the Borrower notwithstanding the receipt by the Bank of any such sums. The guaranteed obligation under this  guarantee  by  the  Guarantors are  not limited to the extent of their shareholding in the Borrower, instead, they would be deemed liable independently to the fullest  extent of the Borrower’s  liability. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"4. This Guarantee shall be a continuing security binding on the Guarantors until receipt by the Bank of written notice of discontinuance thereof and notwithstanding such discontinuance or any release or granting of time or other indulgence by the Bank to any one or more of the Guarantors or independent dealing by the Bank with any of the Guarantors on any matter whatsoever, including calling  back  this  guarantee  as against a Guarantor  or  discharge of that Guarantor from the liability under these presents to any extent and at any consideration, this Guarantee shall remain a continuing security as regards the remaining Guarantor(s), if any. In case of discontinuance by notice, this Guarantee shall, nevertheless as to the Guarantor(s) giving notice, continue to be binding on them in respect of all liabilities of the Borrower up to the limit above mentioned at the date of receipt of such notice, whether certain or contingent, and also for any credits established for the Borrower and for all instruments, drawn on the Bank or accepted by the Bank for the benefit of the Borrower and purporting to be dated on or before the date of receipt of the notice though actually paid or honored after that date.  ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"5. This Guarantee is and shall be deemed to be additional and without prejudice to any other guarantee(s) or securities given by the Borrower in respect of any indebtedness covered by this Guarantee. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"6. This Guarantee shall not be in any way discharged or impaired by any time or indulgence granted to the Borrower in relation to all or any of the obligations assumed by the Borrower under the terms and conditions of financing or any variation of any provision thereof (whether or not the Guarantors shall be aware of the same) or by any other circumstance which would or might (but for this provision) constitute a legal or equitable discharge or defence of a Guarantor. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"7. The obligations of the Guarantors under this Guarantee will not be affected by any act, omission, matter or thing which, but for this Guarantee, would reduce, release or prejudice any of their obligations under this Guarantee (without limitation and whether or not known to it or the Bank) including: ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(a) ",
					"any time, waiver or consent granted to, or composition with, or renewing, reviewing, rescheduling, restructuring, determining, varying or increasing the banking facility(ies) or transaction or otherwise dealing with the same in any manner whatsoever or concurring in, accepting or varying any compromise, arrangement or settlement with, the Borrower or other person;",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(b) ",
					"the release of the Borrower or any other person under the terms of any composition or arrangement with any creditor; ",
					true, false));

			document.add(PDFGenerator.getUnorderedList("(c) ",
					"the taking, variation, compromise, exchange, renewal or release of, or refusal or neglect to perfect, take up or enforce, any rights against, or security over assets of, the Borrower or other person or any nonpresentation or non-observance of any formality or other requirement in respect of any instrument or any failure to realise the full value of any security; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(d) ",
					"any incapacity or lack of power, authority or legal personality of or dissolution or change in the members or status of the Borrower or any other person; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(e) ",
					"any amendment, novation, supplement, extension (whether of maturity or otherwise) or restatement (in each case however fundamental and of whatsoever nature, and whether or not more onerous) or replacement of the terms and conditions of financing or any other document or security; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(f) ",
					"any unenforceability, illegality or invalidity of any obligation of any person under the finance documents or any other document or security; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(g) ", "any insolvency or similar proceedings;", true, false));
			document.add(PDFGenerator.getUnorderedList("(h) ",
					"any change in law, regulation, decree or order of any jurisdiction or any other event affecting any term of this Guarantee or any obligation of the Guarantors hereunder, including any change in the application of such law, regulation, decree or order, declarations of a banking moratorium or other suspension of payments by banks, any expropriation, confiscation, nationalisation or requisition, any war, insurrection, revolution, hostile act or civil strife.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"8. The Guarantors waive any right they may have of first requiring the Bank to proceed against or enforce any other rights or security or claim payment from the Borrower or any person before claiming of that from the Guarantors under this Guarantee. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"9. The Bank shall not be obliged to make any claim or demand on the Borrower or to resort to any security documents or other means of payment now or hereafter held by or available to it before enforcing this Guarantee and no action taken or omitted by the Bank in connection with any such security documents or other means of payment shall discharge, reduce, prejudice or affect the liability of the Guarantors under this Guarantee, nor shall the Bank be obliged to apply any or other property received or recovered in consequence of any enforcement or realization of any such security documents or other means of payment in reduction of the liabilities of the Guarantors hereunder. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"10. The Guarantors agree that, without the prior written consent of the Bank, they shall not: ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(a) ",
					"exercise their rights of subrogation, reimbursement and indemnity against the Borrower or any other person liable; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(b) ",
					"demand or accept repayment in whole or in part of any indebtedness now or hereafter due to the Guarantors from the Borrower; ",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(c) ",
					"take any step to enforce any right against the Borrower or any other person liable in respect of the liabilities of the Guarantors hereunder; or",
					true, false));
			document.add(PDFGenerator.getUnorderedList("(d) ",
					"claim any set-off or counterclaim against the Borrower or any other person liable in competition with the Bank in the liquidation of the Borrower or any other person liable or have the benefit of, or share in, any payment from the Borrower or any other person liable or any other security documents now or hereafter held by the Bank as security for the loan/advance.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"11. A certificate delivered by the Bank to the Guarantors certifying the amount due from the Borrower at the date of such certificate shall be prima facie evidence of the amount due from the Borrower. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"12. All payments made by the Guarantors hereunder shall be made within three (3) business days of written demand by the Bank. All payments made by the Guarantors hereunder shall be made free and clear of and without any deduction for or on account of (i) any set-off or counterclaim or (ii) any tax or other matter. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"13. If (i) the Guarantors are required by law to make any deduction or withholding from any sum payable by the Guarantors to any person hereunder or (ii) the Bank is required by law to make any payment, on account of tax (other than tax on its overall net income) or otherwise, on or in relation to any amount received or receivable by such person hereunder, then the sum payable by the Guarantors in respect of which such deduction, withholding or payment is required to be made shall be increased to the extent necessary to ensure that, after the making of such deduction, withholding or payment, such person receives and retains (free from any liability in respect of any such deduction. withholding or payment) a net sum equal to the sum which it would have received and so retained had no such deduction, withholding or payment been required. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"14. The Guarantors authorise the Bank to apply any credit balance to which the Guarantors are then entitled on any account of the Guarantors with the Bank at any of their branches in or towards satisfaction of any sum then due and payable from the Guarantors to the Bank under this Guarantee.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"15. This Guarantee shall be binding upon and inure to the benefit of each party hereto and their respective heirs, successors, legal representatives, administrators and assigns.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"16. The Guarantors shall not assign or transfer all or any of their rights, benefits and obligations hereunder.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"17. Neither failure by the Bank to exercise nor any delay by the Bank in exercising, any right or remedy under this Guarantee shall operate as a waiver thereof nor shall any single or partial exercise of any such right or remedy prevent any further or other exercise thereof or the exercise of any other such right or remedy. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"18. No provision of this Guarantee may be amended, changed, waived, discharged or terminated except by an instrument in writing and agreed and consented to by the Guarantors and the Bank. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"19. Any notice, demand, request or other communications to be sent or furnished under or with reference to this Guarantee shall be in writing and shall be served by post at the address of the Bank & the Guarantors mentioned in this Guarantee or such other addresses as the Parties may notify each other in writing and shall be deemed delivered  to the Guarantors if sent by the ordinary course of post and to the Bank on the day of actual receipt.. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"20. This Guarantee shall be governed by and construed in accordance with the laws of Bangladesh and shall be subject to the jurisdiction of the Courts of Bangladesh.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"21. The Schedule attached with this Guarantee constitutes an integral part of this Guarantee.",
					true, false));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("IN WITNESS WHEREOF ", true, false,
					"the Guarantors have caused this Guarantee to be duly executed on the date first above written. "));

			document.add(PDFGenerator.getLeftAlignedBoldParagraph("In witness of:", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"1. ___________________________  2. ___________________________ ", false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Name  :                                                     Name  : ", true, true));
			document.newPage();
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("SCHEDULE ", true, false));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("Part 1 ", false, true));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("Particulars of the Borrower ", true, false));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("Part 2 ", false, true));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("vParticulars of the Guarantors ", true, false));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("Part 3 ", false, true));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("Secured Amount ", true, false));

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
