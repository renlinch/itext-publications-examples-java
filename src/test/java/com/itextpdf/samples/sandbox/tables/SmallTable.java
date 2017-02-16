/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written in answer to the question
 * http://stackoverflow.com/questions/39203479
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class SmallTable extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/tables/small_table.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SmallTable().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, new PageSize(290, 112));
        doc.setMargins(5, 5, 5, 5);

        Table table = new Table(new float[]{ 160, 120 });

        // first row
        Cell cell = new Cell(1, 2).add("Some text here");
        cell.setHeight(30);
        cell.setBorder(Border.NO_BORDER);
        table.addCell(cell);

        // second row
        cell = new Cell().add(new Paragraph("Some more text").setFontSize(10));
        cell.setHeight(30);
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.setBorder(Border.NO_BORDER);
        table.addCell(cell);
        Barcode128 code128 = new Barcode128(pdfDoc);
        code128.setCode("14785236987541");
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(pdfDoc));
        cell = new Cell().add(code128Image.setAutoScale(true));
        cell.setBorder(Border.NO_BORDER);
        cell.setHeight(30);
        table.addCell(cell);

        // third row
        table.addCell(cell.clone(true));
        cell = new Cell().add(new Paragraph("and something else here").setFontSize(10));
        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.RIGHT);
        table.addCell(cell);
        doc.add(table);

        doc.close();
    }
}