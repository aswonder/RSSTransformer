package rsstransformer.makers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font.FontFamily;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import rsstransformer.RSSData;
import rsstransformer.datamodel.RSSItem;
import rsstransformer.interfaces.MakerInterface;

/*
 * @author Andrey S. Divov
 */
public class PDFMaker implements MakerInterface {

    private Paragraph paragraph;
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
    private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.GREEN);

    private RSSData rssData;

    public PDFMaker(RSSData rssData) {
        this.rssData = rssData;
    }

    @Override
    public void make(String fileName) {
        try {
            Document document = new Document();

            //Путь к файлу
            //Поток (стрим) для записи файла
            FileOutputStream fos = new FileOutputStream(fileName);

            //PDF Writer
            PdfWriter.getInstance(document, fos);

            //Открываем PDF документ для записи
            document.open();

            BaseFont baseFont = BaseFont.createFont("NordTypefamily/Nord Medium.otf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font linkFont = new Font(baseFont, 11);
            linkFont.setColor(0, 0, 255); //Set Blue color
            linkFont.setStyle(6); //Set underline italic

            Font pFont = new Font(baseFont, 10);

            Font dateFont = new Font(baseFont, 10);
            
            Font catFont = new Font(baseFont, 11);
            catFont.setColor(100, 100, 100); //Set grey color
            catFont.setStyle(1); //set bold style


            //Заголовок с выравниванием по центру
            paragraph = new Paragraph("RSS", titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            for (RSSItem item : rssData.getRSSItems()) {
                // Add text with a link to an external URL
                Chunk link = new Chunk(item.getTitle());
                link.setAction(new PdfAction(new URL(item.getLink())));
                link.setFont(linkFont);
                document.add(link);

                paragraph = new Paragraph(item.getDescription(), pFont);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(25);
                document.add(paragraph);

                paragraph = new Paragraph(item.getPubDate(), dateFont);
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                paragraph.setFirstLineIndent(25);
                document.add(paragraph);

                paragraph = new Paragraph(item.getCategory(), catFont);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(25);
                document.add(paragraph);

                paragraph = new Paragraph(" ", textFont);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(25);
                document.add(paragraph);
            }

            document.close();

        } catch (FileNotFoundException e) {
            //Blah Blah Blah
        } catch (DocumentException e) {
            //Blah Blah Blah
        } catch (IOException ex) {
            Logger.getLogger(PDFMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
