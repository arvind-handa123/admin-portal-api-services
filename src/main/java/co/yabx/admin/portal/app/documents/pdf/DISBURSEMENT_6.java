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

public class DISBURSEMENT_6 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("LETTER OF DISBURSEMENT ", true, true));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Date: ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));

			document.add(PDFGenerator.getLeftAlignedBoldParagraph(
					"Ref: Credit Facilities for BDT ……………………….. (Taka …………………………) only. ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir, ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"With reference to the above Credit Facilities availed by me/us today in respect of which I/we have this day signed a Demand Promissory Note for  BDT _____________________________  (Taka ________________________) only. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Please arrange to disburse the amount of the loan to me/us. ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Thanking you, ", true, true));
			document.add(PDFGenerator.getCenteredParagraph(
					"-----------------------------        ------------------------------- ", true, true));
			document.add(PDFGenerator
					.getCenteredParagraph("Authorized Signature                  Authorized Signature ", true, true));

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
