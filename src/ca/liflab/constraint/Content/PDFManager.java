package ca.liflab.constraint.Content;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.List;

/**
 * Created by Guillaume57 on 12/03/2017.
 */

public class PDFManager {

    /**
     * Crée le PDF
     * @return
     * @throws IOException
     */
    public static PDDocument createPDF() throws IOException {
        //Crée un document
        PDDocument doc = new PDDocument();
        PDAcroForm acroForm = new PDAcroForm(doc);

        doc.getDocumentCatalog().setAcroForm(acroForm);

        try {
            //Crée une page
            PDPage page = new PDPage();
            doc.addPage(page);

            //Titre de la page
            PDPageContentStream contents = new PDPageContentStream(doc, page);
            contents.beginText();
            contents.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contents.newLineAtOffset(100, 700);
            contents.showText("Document test n°1");

            //Partie 1
            contents.newLineAtOffset(20, -40);
            contents.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contents.showText("Partie 1");
            contents.endText();

            //TextField de la partie 1
            PDTextField textBox1 = new PDTextField(acroForm);
            textBox1.setMultiline(true);
            textBox1.setPartialName("txt1");
            acroForm.getFields().add(textBox1);
            PDAnnotationWidget widget = textBox1.getWidgets().get(0);
            widget.setAnnotationName("Texte1");
            PDRectangle rect = new PDRectangle(100, 525, 500, 100);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);

            //Partie 2
            contents.beginText();
            contents.newLineAtOffset(120, 450);
            contents.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contents.showText("Partie 2");
            contents.endText();

            //TextField de la partie 2
            PDTextField textBox2 = new PDTextField(acroForm);
            textBox2.setMultiline(true);
            textBox2.setPartialName("txt2");
            acroForm.getFields().add(textBox2);
            PDAnnotationWidget widget2 = textBox2.getWidgets().get(0);
            widget2.setAnnotationName("Texte2");
            PDRectangle rect2 = new PDRectangle(100, 300, 500, 100);
            widget2.setRectangle(rect2);
            widget2.setPage(page);
            page.getAnnotations().add(widget2);

            //Conclusion
            contents.beginText();
            contents.newLineAtOffset(120, 250);
            contents.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contents.showText("Conclusion");
            contents.endText();
            contents.close();

            //TextField de la conclusion
            PDTextField textBox3 = new PDTextField(acroForm);
            textBox3.setMultiline(true);
            textBox3.setPartialName("txt3");
            acroForm.getFields().add(textBox3);
            PDAnnotationWidget widget3 = textBox3.getWidgets().get(0);
            widget3.setAnnotationName("Texte3");
            PDRectangle rect3 = new PDRectangle(100, 100, 500, 100);
            widget3.setHidden(true);
            widget3.setRectangle(rect3);
            widget3.setPage(page);
            page.getAnnotations().add(widget3);

            doc.save("Page.pdf");
        }
        finally {
            doc.close();
        }

        return doc;
    }

    /**
     * Récupérer tout le texte d'un PDF
     * @param document PDF
     * @throws IOException
     */
    public static void extractText(File f) throws IOException {
        //Charge le document
        PDDocument doc = PDDocument.load(f);
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            //Récupère le texte du document
            String text = stripper.getText(doc);
            System.out.println(text);
        }
        catch (Exception e) { }
    }

    /**
     * Avoir toutes les annotations d'une page PDF et afficher leur contenu
     * @param page PDF
     * @throws IOException
     */
    public static void getAnnotations(PDPage page) throws IOException {
        //Récupère la liste des annotations
        List<PDAnnotation> annotations = page.getAnnotations();
        for (PDAnnotation a : annotations) {
            System.out.println(a.getAnnotationName());
            System.out.print(a.getContents());
            System.out.println();
        }
    }

    /**
     * Récupérer le contenu d'une annotation
     * @param page PDF
     * @param annotation
     * @throws IOException
     */
    public static void getContentFromAnnotation(PDPage page, String annotation) throws IOException {
        //Récupère liste des annotations
        List<PDAnnotation> annotations = page.getAnnotations();
        for (PDAnnotation a : annotations) {
            System.out.println(a.getAnnotationName());
            if (a.getAnnotationName().equals(annotation)) {
                System.out.println(a.getContents());
            }
        }
    }

    /**
     * Récupère le document PDF à partir du nom du fichier
     * @param nom du fichier
     * @return
     * @throws IOException
     */
    public static PDDocument getDocument(File f) throws IOException {
        PDDocument doc = PDDocument.load(f);
        return doc;
    }

    /**
     * Récupère la page du document PDF
     * @param document PDF
     * @param numéro de la page
     * @return
     */
    public static PDPage getPage(PDDocument doc, int number) {
        PDPage page = doc.getPage(number);
        return page;
    }
}
