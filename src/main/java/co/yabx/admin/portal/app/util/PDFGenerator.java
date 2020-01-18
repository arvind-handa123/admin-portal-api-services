package co.yabx.admin.portal.app.util;

import java.io.Serializable;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFGenerator implements Serializable {
	private static Font black_bold_underlined = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.UNDERLINE,
			new CMYKColor(0, 0, 0, 255));
	private static Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
			new CMYKColor(0, 0, 0, 255));
	private static Font regular = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL,
			new CMYKColor(0, 0, 0, 255));

	public static List getUnorderedList(String type, String content, boolean isSpacingBefore, boolean isSpacingAfter) {
		List unorderedList = new List(List.UNORDERED);
		ListItem item = new ListItem(content, regular);
		unorderedList.setListSymbol(type);
		unorderedList.setIndentationLeft(50);
		unorderedList.setIndentationRight(40);
		item.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			item.setSpacingBefore(10);
		if (isSpacingAfter)
			item.setSpacingAfter(10);
		unorderedList.add(item);
		return unorderedList;
	}

	public static Element getLeftAlignedSubListParagraph(String content, boolean isBold, boolean isSpacingBefore,
			boolean isSpacingAfter) {
		Paragraph paragraph = null;
		if (isBold) {
			paragraph = new Paragraph(content, black_bold);
		} else
			paragraph = new Paragraph(content, regular);
		paragraph.setIndentationLeft(70);
		paragraph.setIndentationRight(40);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);

		return paragraph;
	}

	public static Element getLeftAlignedParagraph(String content, boolean isSpacingAfter, boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, regular);
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		paragraph.setAlignment(Element.ALIGN_LEFT);

		if (isSpacingBefore)
			paragraph.setSpacingBefore(10);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(10);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		return paragraph;
	}

	public static Element getRightAlignedParagraph(String content, boolean isSpacingAfter, boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, regular);
		paragraph.setIndentationLeft(300);
		paragraph.setAlignment(Element.ALIGN_RIGHT);

		if (isSpacingBefore)
			paragraph.setSpacingBefore(10);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(10);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		return paragraph;
	}

	public static Paragraph getCenteredUnderLinedParagraph(String content, boolean isSpacingAfter,
			boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, black_bold_underlined);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getCenteredBoldParagraph(String content, boolean isSpacingAfter, boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, black_bold);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getCenteredParagraph(String content, boolean isSpacingAfter, boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, regular);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getLeftAlignedBoldParagraph(String content, boolean isSpacingAfter,
			boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, black_bold);
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getRightAlignedBoldParagraph(String content, boolean isSpacingAfter,
			boolean isSpacingBefore) {
		Paragraph paragraph = new Paragraph(content, black_bold);
		paragraph.setIndentationLeft(300);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getLeftAlignedBoldContentWithNonBoldContent(String boldContent, boolean isSpacingAfter,
			boolean isSpacingBefore, String nonBoldContent) {
		Paragraph paragraph = new Paragraph(boldContent, black_bold);
		paragraph.add(new Chunk(nonBoldContent, regular));
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static Paragraph getRightAlignedBoldContentWithNonBoldContent(String nonBoldContent, boolean isSpacingAfter,
			boolean isSpacingBefore, String boldContent) {
		Paragraph paragraph = new Paragraph(nonBoldContent, regular);
		paragraph.add(new Chunk(boldContent, black_bold));
		paragraph.setIndentationLeft(40);
		paragraph.setIndentationRight(40);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		if (isSpacingBefore)
			paragraph.setSpacingBefore(25);
		if (isSpacingAfter)
			paragraph.setSpacingAfter(25);
		return paragraph;
	}

	public static PdfPTable getTable(boolean isNew, PdfPTable table, String... header) {
		if (isNew) {
			table = new PdfPTable(header.length);
		}
		for (int i = 0; i < header.length; i++) {
			table.addCell(new PdfPCell(new Phrase(header[i])));
		}
		return table;

	}

	public static PdfPTable getTable(boolean isNew, PdfPTable table, Float paddingSize, String... header) {
		if (isNew) {
			table = new PdfPTable(header.length);
		}
		for (int i = 0; i < header.length; i++) {
			PdfPCell pdfPCell = new PdfPCell(new Phrase(header[i]));
			if (paddingSize != null)
				pdfPCell.setPadding(paddingSize);
			table.addCell(pdfPCell);
		}
		return table;

	}
}
