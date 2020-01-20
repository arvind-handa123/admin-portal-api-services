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

public class LETTER_OF_CONTINUITY_AND_REVIVAL_5 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("LETTER OF CONTINUITY & REVIVAL ", true, true));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("Date: ", true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph("BRAC Bank Limited", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Anik Tower ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("220/B Tejgaon Gulshan Link Road", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Tejgaon, Dhaka-1208 ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir, ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/We, the undersigned beg to enclose a Demand Promissory Note dated ………………………. for BDT. _________________(Taka___________________)  Only signed by me that is given to you as security for the repayment of any credit facility which is at present outstanding in my name and also for the repayment of any credit facility to the extent of BDT. _________________(Taka___________________)  Only, which I may avail of hereafter and the said Promissory Note is to be a security to you for the repayment of the ultimate balance or sum remaining unpaid on the credit account and I remain liable on the said Promissory Note, notwithstanding the fact that by payments made into my account from time to time the credit account may from time to time be reduced or extinguished or even that the balance of the said account may be at credit. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/We further acknowledge that you may, at your absolute discretion, postpone the right to sue for my/our non-payment against the said Demand Promissory Note till the payment against the said Demand Promissory Note till the occurrence of last event of default in time under the loan/advance/overdraft/cash credit facility extended to us. In the event that you exercise such discretion, the said Demand Promissory Note shall be deemed to have become payable on the date on which the said event of default occurs. ",
					true, true));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I/We further acknowledge that you are at liberty to take such steps as you consider expedient in order to enforce payment of the Promissory Note at any time after your notice demanding payment has been posted and default made in payments for 3 (Three) days after posting such notice and that this letter of continuity shall apply to any other Promissory Note that may be given in renewal or substitution of the original.     ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"I hereby further acknowledge and confirm that I am liable to you for payment of the amount mentioned in the said Promissory Note together with interest thereon; and that the limitation of the said Promissory Note shall be suspended in accordance with the Limitation Act, 1908 (and any amendment thereto) until I default in repayment of credit facility, the limitation shall start from the date of default. ",
					true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Yours faithfully, ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("............................................... ", true,
					true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Name: ", false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph("Address: ", false, false));

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
