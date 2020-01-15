package co.yabx.admin.portal.app.documents.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.util.PDFGenerator;
import co.yabx.admin.portal.app.util.SpringUtil;

public class ARRANGEMENT_7 {

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
			document.add(PDFGenerator.getLeftAlignedParagraph("Date: ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));
			document.add(PDFGenerator.getCenteredBoldParagraph("LETTER OF ARRANGEMENT ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir, ", true, false));

			document.add(PDFGenerator.getLeftAlignedBoldParagraph(
					"Ref: Credit Facilities for BDT ___________________ (Taka _________________________) only granted in favor of  ______(Name of the borrower)__________, address at ________________(Registered Address)___________________ ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"With reference to the above limit granted in my/our favor of ______(Name of the borrower)__________S/o______________________ address at ________________(Registered Address)___________________ for which I/we have this day executed a Demand Promissory Note and other necessary documents, I/we hereby acknowledge your right to cancel the facility at any time with or without intimation to me/us. In the event of the facility being canceled by you, I/we undertake to pay to you all dues together with all other charges due by me/us immediately on demand. If this agreement is signed or otherwise executed by or on behalf of more than one party, the obligation and the liability of such parties shall be deemed to be joint and several unless expressly stated to the contrary.",
					false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"It is also understood that, any word appearing in the singular will also apply for the plural and vice versa. ",
					false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"IN WITNESS whereof we executed these presents on the day, month and year first hereinabove written. ",
					false, false));
			document.add(PDFGenerator.getCenteredParagraph(
					"-----------------------------        ------------------------------- ", false, true));
			document.add(PDFGenerator
					.getCenteredParagraph("Authorized Signature                  Authorized Signature ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Witness: ", true, false));
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
