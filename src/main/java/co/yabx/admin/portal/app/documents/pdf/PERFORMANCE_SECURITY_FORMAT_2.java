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

public class PERFORMANCE_SECURITY_FORMAT_2 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("Performance Security", true, true));
			document.add(PDFGenerator.getCenteredParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getCenteredParagraph("New Eskaton Branch, 23 New Eskaton, Dhaka", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Beneficiary: Bangladesh Rural Electrification Board",
					true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Date: ", true, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Performance Guarantee No.: ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"We have been informed that Bangladesh Rural Electrification Board (hereinafter called ‘the Contactor’) has entered into contract no BREB/URIDS(DMCS)/URIDS(E)-CW-01-08/2017-2018…… dated….. with you, for the execution of Civil Works for the construction of Zonal Office & Residential Building at Fullgazi Zonal Office Complex in Feni PBS (hereinafter called ‘the Contract’).",
					false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"Furthermore, we understand that, according to the conditions of the Contract, a performance guarantee is required. ",
					false, true));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"At the request of the Contractor, we, BRAC Bank Limited, hereby irrevocably undertake to pay you any sum or sums not exceeding in total an amount of BDT 85,61,865.16 (Eighty Five Lac Sixty One Thousand Eight Hundred Sixty Five Taka and Sixteen Paisa) such sum being payable in the types and proportions of currencies in which the Contract Price is payable, upon receipt by us of your first demand in writing accompanied by a written statement stating that the Contractor is in breach of its obligation(s) under the Contract, without your needing to prove or to show ground for your demand or the sum specified therein. ",
					false, true));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"This guarantee shall expire, no later than the …..Day of…. and any demand for payment under it must be received by us at this office on or before that date.",
					false, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"This guarantee is subject to the Uniform Rules for Demand Guarantees, ICC Publication No. 758, except that subparagraph (ii) of Sub-article 20(a) is hereby excluded.",
					false, true));
			document.add(
					PDFGenerator.getCenteredParagraph("............................................... ", false, true));
			document.add(PDFGenerator.getCenteredParagraph("Signature(s) and seal of bank (where appropriate) ", false,
					false));

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
		get_PERFORMANCE_SECURITY_FORMAT_Document("D://test/1.pdf", "abc");
	}
}
