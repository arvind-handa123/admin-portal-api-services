package co.yabx.admin.portal.app.documents.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import co.yabx.admin.portal.app.enums.AttachmentType;
import co.yabx.admin.portal.app.kyc.entities.User;
import co.yabx.admin.portal.app.kyc.service.AppConfigService;
import co.yabx.admin.portal.app.kyc.service.AttachmentService;
import co.yabx.admin.portal.app.util.PDFGenerator;
import co.yabx.admin.portal.app.util.SpringUtil;

public class GENERAL_LOAN_AGREEMENT_8 {

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
			document.add(PDFGenerator.getCenteredBoldParagraph("General Loan Agreement ", true, true));
			document.add(PDFGenerator.getLeftAlignedParagraph("Dear Sir, ", true, false));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"In consideration of BRAC Bank Limited, a banking company hereinafter called the “Bank” (which expression shall, where the context so admits, include its successors and assigns) making or continuing advances or otherwise giving credit or providing finance to me/us under one or more modes of finance or otherwise affording any other banking facilities or other accommodation of any kind (hereinafter collectively referred to as “Loans”) up to a limit of BDT _____________________ (Taka _______________________________) only, I/we, ___________ (Name of the Borrower)________________ having its office at  ______________ (Registered Address)_______________ (hereinafter called the “Borrower”). ",
					true, false));
			document.add(PDFGenerator.getLeftAlignedBoldParagraph(
					"HEREBY AGREE, UNDERTAKE, WARRANT, ASSURE AND COVENANT AS FOLLOWS:", false, false));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("1. Loans Defined: ", false, false,
					" The term “Loans” as used herein shall include any and all indebtedness, obligations and liabilities of any kind of the Borrower (or of any partnership, syndicate, association, joint venture or other group of which the Borrower is a member) in which the Bank shall have interest, now or hereafter existing, whether or not represented by notes, bonds, debentures, drafts or other evidence of indebtedness, whether arising out of loans, advances on open account, letters of credit, overdraft, contract or by operation of law or otherwise whether absolute or contingent, joint or several, secured or unsecured, due or not due, direct or indirect, liquidated or un-liquidated and whether incurred by the Borrower as principal, surety, endorser, guarantor or otherwise."));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("2. Security Defined: ", false, false,
					" The term “Security” as used herein shall include all present and future credit balances of the Borrower in any currency with the Bank, all Monies, negotiable instruments, commercial papers, notes, bonds, shares, debentures or other securities, bills of lading, airway bills, railway and truck receipts, warehouse receipts, insurance policies, claims, demands and any interest thereon, and any property represented by any of the foregoing and any other property, rights and interests of the Borrower, and any evidence thereof, which have been or at any time shall be delivered to or otherwise come into the possession of the Bank, its custody or control or be in transit to or from or allocated to the Bank for any purpose, whether or not accepted. "));

			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("3. Pledge: ", false, false,
					" As Security for the Loans, the Borrower hereby pledges to the Bank all of the Security and gives to it a general lien upon, and a right of set-off, of all rights, title and interest of the Borrower in and to any of the Security. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("4. General Rights in Security: ",
					false, false,
					" On the occurrence of any event of default, the Bank may at its option, without notice and without incurring any liability and without discharging or otherwise affecting any liability of the Borrower thereon: "));
			document.add(PDFGenerator.getUnorderedList("(a)   ",
					" Exercise any or all powers with respect to any Security with the same force and effect and in the manner as an absolute owner thereof; ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(b)   ", " Transfer any Security in the name of the Bank; ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(c)   ", " Exercise all voting powers; ", false, false));
			document.add(PDFGenerator.getUnorderedList("(d)   ",
					" Remove or have removed any Security from any state or country to any other state or country; ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(e)   ",
					" pledge any obligations issued or guaranteed by the Government of Bangladesh or any local authority which comprises the Security either alone or mingled with other collateral, to the Bangladesh Bank or any other authority in Bangladesh to secure deposits or other obligations of the Bank whether or not in excess of the Loans to the Borrower by the Bank;",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(f)   ",
					" Demand and receive all payments and distributions of any Security (including principal, premium, interest, dividend or other income, share dividends and rights to subscribe); ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(g)   ",
					" the Bank may act on behalf of the Borrower in its name or in the name of any one for whom it has acted or shall act as agent, demand, sue for, collect and receive any Monies, securities or other property at any time due, payable or receivable on account of or in exchange for any Security, or make any compromise or settlement deemed desirable with respect thereto; ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(h)   ",
					" sell any Security which constitutes an obligation for the payment of money, in any manner hereinafter provided, or extend the time of payment of any such Security, arrange for payment of any such Security in installments, or otherwise modify the terms thereof as to any other Party thereon; ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(i)   ",
					" Apply the net cash proceeds of any Security, whether principal or interest, to the principal and interest if any payable on any loan, or to continue to hold such proceeds as Security;",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(j)   ",
					" Surrender or release any Security to the Borrower, or exchange any Security for another Security provided by the Borrower ",
					false, false));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent(
					"5. Bank’s obligations as to Security: ", false, false,
					" The Bank shall be under no obligation to send notices, perform services or take any action of any kind in connection with the management of the Security. The Bank shall have no responsibility or liability for the form, sufficiency, accuracy, genuineness or legal effect of any Security or any instrument in any way relating thereto or any signature thereon, or any instrument representing or purporting to represent property or goods, or for the performance of any obligation of carriage, storage, insurance or otherwise, or for the consequences of any error, interruption, delay, mutilation or loss in transit of cables, telegrams, letters or other documents, or errors in translation or interpretation, or for obligations imposed by laws, customs or regulations of any state or country, or for the acts or decisions of Public Authorities, strikes, lockouts, riots, wars, acts of God, or other causes beyond the control of the Bank, or for the act or failure to act by any of the Bank’s correspondents."));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("6. Insurance: ", false, false,
					" The Borrower will at its own expense at all times keep fully insured with reputable companies acceptable to the Bank all tangible property constituting a part of the Security, against loss by fire or all or any other risks to which said property may be subject, and will deposit with the Bank copies of the policies or certificate thereof in such form as the Bank shall approve. If the Borrower shall fail to do so, the Bank may maintain such insurance and the expense thereof shall be an additional liability of the Borrower. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("7. Additional Security: ", false,
					false,
					" The Borrower will, upon demand of the Bank at any time or from time to time, furnish such further Security or make such payment on account as will be satisfactory to the Bank."));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("8. Acceleration of Maturity: ",
					false, false,
					" The Bank, at its own discretion, may decide to accelerate the date of maturity of the Loans, in the event that"));

			document.add(PDFGenerator.getUnorderedList("(a)   ",
					" the Borrower fails to furnish further Security or make payment as required in the previous paragraph; or",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(b)   ",
					" Any sum becoming due and unpaid by the Borrower within 7 days of becoming due; or ", false,
					false));
			document.add(PDFGenerator.getUnorderedList("(c)   ",
					" The Borrower shall fail to perform any terms and conditions herein contained; or ", false,
					false));
			document.add(PDFGenerator.getUnorderedList("(d)   ",
					" The premium relative to any policy of insurance constituting a part of the Security is not paid when due, or such premium if paid by the operation of a provision in any such policy for automatic premium loans by the insurer with or without the consent of the Bank; or  ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(e)   ",
					" the Borrower  (1) die (2) becomes insolvent (however evident), (3) commits any act of insolvency, (4) defaults in any payment on any indebtedness or in the performance of any instrument relating to any indebtedness, (5) makes a general assignment for the benefit of creditors, (6) suspends the operation of the Borrower’s usual business (7) is expelled or suspended from any  exchange or trade association, (8) admits  in writing the inability of the Borrower to pay the Borrower’s debts generally as they become due; or",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(f)   ",
					" any petition in bankruptcy or insolvency or for a reorganization, composition, extension or the appointment of a receiver or other relief under any law relating to bankruptcy, insolvency, the relief of debtors or the liquidation or adjustment of indebtedness is filed by or against  the Borrower or the property of the Borrower;  or ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(g)   ",
					" The Borrower, being a corporation, a resolution, for its winding up is passed or an order is made for its winding up; or ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(h)   ",
					" any warrant, order of attachment, tax lien or levy, restraint or garnishment or sequestration be issued against any of the property of the Borrower by any receiver, court or governmental authority to take possession or control of any substantial part of such property or control over the affairs and obligations of the Borrower; or ",
					false, false));
			document.add(PDFGenerator.getUnorderedList("(i)   ",
					" any of the events described in subdivisions (e) through (h) above shall occur (1) with respect to any maker, obligor, endorser, guarantor, surety issuer or other person liable, upon or for any loan or Security or any partnership of which the Borrower or any such person may now or then be a member,  (2) with respect to the property of any such person or partnership, then and in any such event, all Loans shall be due and payable forthwith without presentation or demand for payment which are hereby expressly waived and thereafter all Loans shall bear interest at the legal rate (if higher than the rate then applicable thereto), provided however, that the Bank by notice in writing may waive, suspend or modify the effect of any such event upon any loan either before or after the same shall have occurred.",
					false, false));

			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("9. Realization on Security: ", false,
					false,
					" Upon non-payment of interest or non-payment of principal on any loan when due or becoming due as  above provided, the Bank may without demand of payment or notice of intention, enforce, collect and realize the dues by sale of the Security, assignment, set off, application and otherwise. Any such sale, assignment, or other realization may be at any time and place public or private with or without advertisement or notice of the time or place or otherwise (all of which are waived), in one or more sale(s) or purchase(s) at such price or prices as the Bank may deem best, for cash or on credit or future delivery, without assumption of any credit risk. The Bank may be a purchaser at any such sale, and each purchaser of any Security so sold (including the Bank) shall hold the same absolutely free from any claim or right of any kind or equity or redemption of the Borrower, which are hereby waived and released. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("10. Expenses: ", false, false,
					" The Borrower will pay all expenses (including legal fees of every kind) of or incidental to the enforcement of any of the provisions hereof or of any of the Loans or of any actual or attempted sale or of any exchange, enforcement, collection or settlement of the Security and of the receipt of the proceeds thereof, and of the care of the Security (including insurance), and any such expense incurred by the Bank shall be added to the amounts due on all Loans and the Bank shall be entitled to all of the benefits thereof. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent(
					"11. Liability for Unpaid Balance, Return of Security: ", false, false,
					" Not with standing the return of Security or realization by the Bank upon the entire Security or the retention by the Bank of any Security, regardless of its value, the Borrower shall remain liable for the unpaid balance of all Loans, together with interest thereon till the date of payment. After all Loans (including expenses, charges, interest, premium,  principal and other sums included therein) have been paid in full, any Security remaining and the remaining proceeds of any Security shall be returned to the Borrower. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("12. Rights Cumulative, No waiver: ",
					false, false,
					" The rights and remedies herein expressly specified are cumulative and non-exclusive of any which the Bank would otherwise have, but it is not intended that any right or remedy be exercised in any jurisdiction in which such exercise would be specifically prohibited by law. No delay by the Bank in exercising any right or remedy hereunder shall operate as a waiver thereof, nor shall any single or partial exercise thereof preclude any further exercise of any other right. The Bank shall not be liable for exercising or failing to exercise any power or right"));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("13. Waiver of Presentment: ", false,
					false,
					" The Borrower hereby waives presentment (except for acceptance when necessary), protest, notice of protest and notice of dishonor of any and all instruments included in the Loans or the Security, whether upon inception, maturity, acceleration of maturity or otherwise and any or all other notice and demand whatsoever, whether or not relating to such instruments. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("14. Amendment: ", false, false,
					" This Agreement shall not be amended, modified or limited except by a written agreement expressly setting out the amendment, modification or limitation and signed by the party against which such amendment; modification or limitation is to be effective. This Agreement shall supersede any inconsistent provisions of any custody agreement hereinbefore executed. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("15. Payment: ", false, false,
					" Unless otherwise agreed, all Loans hereto before or hereafter obtained from or through the Bank by the Borrower shall be repayable upon demand at the branch of the Bank at which the Loan was made available to the Borrower. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("16. Notices: ", false, false,
					" Service of Process Demands for additional Security and any other demands or notices to the undersigned made by telephone or in writing left at or telegraphed or mailed to the address as the Borrower may furnish in writing, shall be as effective as if delivered in person. The Borrower consents to the commencement of any action and the service of any process at any place where the Borrower resides or conducts business or has any property. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("17. Participations: ", false, false,
					" The Bank may act hereunder or with respect to any Loan or Security, on behalf of or as agent for any principal or participant, and may grant participation, in or assign any Loan, and may grant participation in or assign this agreement and transfer any Security to any such principal, participant in or assignee of any Loan. In any such case, the term “Bank” as used herein shall include all such principals, participants and assignees, each of whom shall have all the benefits of this agreement as if named herein. The Bank shall continue to have the benefits hereof if it retains any interest in any Loan or Security but shall be fully discharged from all claims and responsibility from any Security so transferred. The Bank may in its discretion exercise all of the rights herein granted without the consent of or notice to any principal, participant, or assignee, and shall not be liable to any principal, participant or assignee for any such action. The term “Bank” as used herein shall also include any agent or nominee of the Bank and each agent or nominee shall also have the benefits of this agreement as if named herein."));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("18. Parties: ", false, false,
					" If more than one person signs this Agreement, they will be jointly and severally liable hereunder, the term “Borrower” will refer to all such person collectively, and the provisions hereof regarding the Loans or Security will apply to any Loan or any Security of any or all of such persons, but the Bank will be authorized to deal hereunder with any one or more such persons. This Agreement will be binding upon the heirs, executors, administrators, successors, or assigns of each Borrower."));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("19. Continuing Agreement", false,
					false,
					" This agreement shall apply to all existing and future transactions, whether or not of the character contemplated at the date hereof, and if all transactions between the Bank and the Borrower shall at any time or times be closed, this agreement shall be applicable to any new transaction thereafter. The acceptance of this agreement shall not be deemed a commitment by the Bank to make any new Loan or extend any fresh credits in the future. "));
			document.add(PDFGenerator.getLeftAlignedBoldContentWithNonBoldContent("20. Miscellaneous: ", false, false,
					" The Borrower will furnish financial statements yearly and will give prompt notice (30 days in advance when possible) of any bulk sale of assets or change in management, control or business policies or any meeting of creditors or any judgment against the Borrower or any event that may be detrimental to the rights and interests of the Bank or any event mentioned hereinbefore and will provide additional information and will permit inspection of books and records on request. "));

			document.add(PDFGenerator.getLeftAlignedParagraph(
					"If this agreement is signed or otherwise executed by or on behalf of more than one party, the obligations and the liabilities of such parties shall be deemed to be joint and several unless expressly stated to the contrary. ",
					false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"It is also understood that any word appearing in the singular will also apply for the plural and vice versa. ",
					false, false));
			document.add(PDFGenerator.getLeftAlignedParagraph(
					"This agreement is to be construed according to the laws of Bangladesh. ", false, false));
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
