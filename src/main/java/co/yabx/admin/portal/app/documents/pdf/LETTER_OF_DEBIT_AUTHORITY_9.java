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

public class LETTER_OF_DEBIT_AUTHORITY_9 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("Letter of Debit Authority ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dated:........................... ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("(hereinafter referred to as the “bank”) ", true, true));

			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir/Madam, ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/We, am/are holding Deposit(s) more particularly described herein below and in consideration of extension of credit facility(ies) of Tk._________________ (Taka ________________) only [hereinafter referred to as the “Loan”] to  me/us/M/s. __________ (Name of the Borrower) ___________ at _______________(Registered Address)___________, [hereinafter referred to as the “Borrower”], I/we  hereby unconditionally and irrevocably authorize the Bank acting through its authorized representatives (which expression shall, where the context so admits, include its successors and assigns)  to debit the aforesaid Deposit(s) held with the Bank, by handing over the Deposit  Receipt(s), duly discharged,  as security  against the  Loan including all charges and interests or in any manner whatsoever including the interests and charges accruing on or in connection with the Loan. ",
					true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/we agree and confirm that the Deposit(s) described in the Schedule below are free from any option, lien, charge or encumbrance of any kind and shall remain with  the Bank as a continuing security until all liabilities and obligations under the Loan have been fully discharged to the satisfaction of the Bank and accordingly the Bank shall be unconditionally authorised without any notice me/us to encash, set-off, transfer,  adjust or apply the  Deposit(s) money(s) from time to time standing to the credit of my/our aforesaid deposits/accounts in or towards partial or full adjustment of the Loan liabilities Interest, Commission, Charges/Fees, Insurance Premium, costs/charges incurred or to be incurred by the Bank for legal opinion, title search, Valuation of Property offered as security or property which is/are already mortgaged/will be mortgage against any credit facility, land rent, taxes, or any other rent, cost of photocopy, related conveyance, legal costs for drafting of documents, opinion, Government fees for charge filling with RJSC, Mortgage fees, stamp costs, and all other costs and charges that the Bank incurred or would incur in realization, maintenance and recovery of the credit facility(ies) sanctioned in my/our favour or in the name of any of my/our company or any company/firm where I/we have interest as director/proprietor etc. whenever so necessary in the event of any default or breach of the terms of the respective Sanction Letter or the Terms and Conditions as  contemplated. . ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/we also agree that the authority to debit my/our account herein created shall be irrevocable and shall continue until discharge of entire Loan liability including interests and all other charges under the terms and conditions of the respective Sanction Letter and this authorization shall be binding in the manner aforesaid on my/our heirs, successors, executors, legal representatives and assignees. ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/we hereby further declare and acknowledge that I/we shall have no claim whatsoever in the accounts in the deposit account including interest accrued thereon held by you having authority to encash and no drawings would be permitted either from principal amount or from the accrued interest until I/we receive from you notice in writing to the effect that you no longer require the deposit as security for the purpose herein specified. ",
					false, false));
			document.add(PDFGenerator.getCenteredUnderLinedParagraph("PARTICULARS  OF THE DEPOSIT(S) ", true, true));
			PdfPTable pdfPTable = PDFGenerator.getTable(true, null, "Deposit Number/Account",
					"Person(s) in whose name(s) the Deposit/ Account stands",
					"Amount of the Deposit/Balance in the Account ", "Remarks ");
			// PDFGenerator.getTable(false, pdfPTable, "a", "b", "c", "d");
			document.add(pdfPTable);
			document.add(PDFGenerator.getRightAlignedParagraph("----------------------------- ", false, false));
			document.add(PDFGenerator.getRightAlignedParagraph("Name: ", false, false));
			document.add(PDFGenerator.getRightAlignedParagraph("Address: ", true, false));

			document.add(PDFGenerator.getRightAlignedParagraph("----------------------------- ", false, false));
			document.add(PDFGenerator.getRightAlignedParagraph("Name: ", false, false));
			document.add(PDFGenerator.getRightAlignedParagraph("Address: ", true, false));

			document.add(PDFGenerator.getRightAlignedParagraph("[signature of the DEPOSITOR(S)] ", false, false));

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
