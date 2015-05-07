package rsstransformer.makers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private RSSData rssData;

    public PDFMaker(RSSData rssData) {
        this.rssData = rssData;
    }

    @Override
    public void make(String fileName) {
        try {
            Document document = new Document();

            //Import custom font
            BaseFont baseFont = BaseFont.
                    createFont("fonts/NordTypefamily/Nord Medium.otf",
                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            //Declare fonts
            Font titleFont = new Font(baseFont, 13, Font.BOLD);
            Font descFont = new Font(baseFont, 11, Font.ITALIC, BaseColor.DARK_GRAY);
            Font linkFont = new Font(baseFont, 11, Font.ITALIC, BaseColor.BLUE);
            Font pFont = new Font(baseFont, 10);
            Font dateFont = new Font(baseFont, 10);
            Font catFont = new Font(baseFont, 11, Font.BOLD, new BaseColor(140, 0, 0));
            Font sepFont = new Font(baseFont, 16);

            //Path to file
            FileOutputStream fos = new FileOutputStream(fileName);

            //PDF Writer
            PdfWriter.getInstance(document, fos);

            //Open PDF document for write
            document.open();

            //Add Title of channel
            paragraph = new Paragraph(rssData.getChannelInfo().getTitle(), titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            //Add Desciption of channel
            paragraph = new Paragraph(rssData.getChannelInfo().getDescription(), descFont);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);

            //Add empty line
            paragraph = new Paragraph(" ", pFont);
            document.add(paragraph);

            for (RSSItem item : rssData.getRSSItems()) {
                // Add text with a link to an external URL
                Chunk link = new Chunk(item.getTitle());
                link.setAction(new PdfAction(new URL(item.getLink())));
                link.setFont(linkFont);
                document.add(link);

                //Add Desciption of item
                paragraph = new Paragraph(item.getDescription(), pFont);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(25);
                document.add(paragraph);

                //Add date of publication
                paragraph = new Paragraph(item.getPubDate(), dateFont);
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                document.add(paragraph);

                //Add category
                paragraph = new Paragraph(item.getCategory(), catFont);
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                document.add(paragraph);

                //Add empty line
                paragraph = new Paragraph(" ", sepFont);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
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
