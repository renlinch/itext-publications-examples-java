package com.itextpdf.samples.htmlsamples.chapter05;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.licensing.base.LicenseKey;

/**
 * Converts a simple HTML file to a PDF document.
 */
public class C05E02_Invitation {

	/**
	 * The path to the resulting PDF file.
	 */
	public static final String DEST = "./target/htmlsamples/ch05/invitation.pdf";

	/**
	 * The path to the source HTML file.
	 */
	public static final String SRC = "./src/main/resources/htmlsamples/html/invitation.html";

	/**
	 * The main method of this example.
	 *
	 * @param args no arguments are needed to run this example.
	 * @throws IOException signals that an I/O exception has occurred.
	 */
    public static void main(String[] args) throws IOException {
        try (FileInputStream license = new FileInputStream(System.getenv("ITEXT7_LICENSEKEY")
				+ "/itextkey-html2pdf_typography.json")) {
			LicenseKey.loadLicenseFile(license);
		}
    	File file = new File(DEST);
    	file.getParentFile().mkdirs();

        C05E02_Invitation app = new C05E02_Invitation();
        app.createPdf(SRC, DEST);
    }

    /**
     * Creates the PDF file.
     *
     * @param src the path to the source HTML file
     * @param dest the path to the resulting PDF
     * @throws IOException signals that an I/O exception has occurred.
     */
    public void createPdf(String src, String dest) throws IOException {
        HtmlConverter.convertToPdf(new File(src), new File(dest));
    }
}
