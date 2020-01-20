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

public class DP_NOTE_4 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("DEMAND PROMISSORY NOTE", true, true));

			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BDT: __________________ ", false, false));
			document.add(PDFGenerator.getRightAlignedBoldParagraph("             Date:   ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/We, the undersigned, promise to pay on demand to BRAC Bank Limited the sum of BDT __________________ (Taka ________________________) only, for value received with interest thereon from this date, at the rate of ________% per annum with quarterly rests or at such rate as the Bank shall determine from time to time. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"All payments under this note shall be made free and clear of and without any deductions and withholdings of any kind whatsoever. ",
					true, true));
			document.add(PDFGenerator.getRightAlignedParagraph("________________________", true, true));
			document.add(PDFGenerator.getRightAlignedParagraph("Signature of the Promisor ", true, true));
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
