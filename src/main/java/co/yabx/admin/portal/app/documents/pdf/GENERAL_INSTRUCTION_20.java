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

public class GENERAL_INSTRUCTION_20 {

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
			document.add(PDFGenerator.getRightAlignedBoldParagraph("                        BRAC BANK ", false, false));
			document.add(
					PDFGenerator.getRightAlignedParagraph("                         L I M I T E D ", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Date:      ", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Account Number:      ", true, false));
			document.add(PDFGenerator.getTable(true, null, " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
					" ", " ", " ", " "));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Branch Manager", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Ltd. ", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph(
					"Branch: _______________________ Account Title:  ______________________ ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("GENERAL INSTRUCTION", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir/Madam,", true, false));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getTable(true, null, " "));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					" _______________________                    ______________________ ", false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Account holder’s Signature                  2nd Account holder’s Signature (if joint)", true,
					false));
			document.add(PDFGenerator.getCenteredBoldParagraph("FOR BANK USE ONLY ", false, false));
			document.add(PDFGenerator.getCenteredBoldParagraph("----------------------------------", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Customer ID: ", true, false));
			document.add(PDFGenerator.getTable(true, null, " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
					" ", " ", " ", " "));
			document.add(PDFGenerator.getLeftAlignedParagraph("Signature(s) Verified", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					" _______________________                    ______________________ ", false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Received By                                                Action Taken By ", false, false));
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
