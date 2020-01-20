package co.yabx.admin.portal.app.documents.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.util.PDFGenerator;
import co.yabx.admin.portal.app.util.SpringUtil;

public class SIGNATURE_CARD_18 {

	public static String getDocuments(User user) {
		String newFileName = System.currentTimeMillis() + "." + "pdf";
		String path = SpringUtil.bean(AppConfigService.class).getProperty("DOCUMENT_STORAGE_BASE_PATH", "/var/lib/kyc/")
				+ user.getId() + "/" + "disclaimer/pdf/";
		File destination = new File(path);
		if (!destination.exists())
			destination.mkdirs();
		Document document = get_PERFORMANCE_SECURITY_FORMAT_Document(path + newFileName, "SIGNATURE_CARD_18");
		if (document != null) {
			SpringUtil.bean(AttachmentService.class).saveAttachments(user, "SIGNATURE_CARD", newFileName,
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

			try {
				/*
				 * Image img =
				 * Image.getInstance(SpringUtil.bean(AppConfigService.class).getProperty("",
				 * "C:\\Users\\asad.ali\\Documents\\admin-portal\\brac_bank.png"));
				 */
				Image img = Image.getInstance(
						SpringUtil.bean(AppConfigService.class).getProperty("BANK_ICONS_PATH", "/var/lib/kyc/icons"));
				img.setAlignment(Element.ALIGN_LEFT);
				img.setIndentationLeft(40);
				document.add(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.add(PDFGenerator.getLeftAlignedParagraph("BRANCH:      ", false, false));
			document.add(
					PDFGenerator.getRightAlignedBoldParagraph("                        SIGNATURE CARD ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Account Title:------------------------------------------------------------------------------------      ",
					false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("(In BLOCK Letters)", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Date:      ", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Account No:      ", true, false));
			document.add(PDFGenerator.getTable(true, null, " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
					" ", " ", " ", " "));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("CIF No:      ", true, false));
			document.add(PDFGenerator.getTable(true, null, " ", " ", " ", " ", " ", " ", " ", " "));
			document.add(PDFGenerator.getLeftAlignedParagraph(" ", false, false));
			document.add(PDFGenerator.getTable(true, null, "Name", "Signature"));
			document.add(PDFGenerator.getTable(true, null, " ", " "));
			document.add(PDFGenerator.getTable(true, null, " ", " "));
			document.add(PDFGenerator.getTable(true, null, "Signing Instruction;",
					"Authorised Signature/Verified & Approved"));
			document.add(PDFGenerator.getTable(true, null, 50.0f, " ", " "));

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
