package rsstransformer;

import rsstransformer.interfaces.LoadDataInterface;
import rsstransformer.interfaces.MakerInterface;
import rsstransformer.makers.HTMLMaker;
import rsstransformer.makers.PDFMaker;
import rsstransformer.makers.TextMaker;

/**
 * @author Andrey S. Divov
 */
public class RSSTransformer {

    private static final String LINK_1 = "http://www.cnews.ru/inc/rss/spo_rss.xml";
    private static final String LINK_2 = "http://lenta.ru/rss/articles/";
    private static final String LINK_3 = "http://www.vesti.ru/vesti.rss";
    private static final String LINK_4 = "http://english.pravda.ru/world/export.xml";

    private static final String TEXT_FILE_NAME = "output.txt";
    private static final String HTML_FILE_NAME = "output.html";
    private static final String PDF_FILE_NAME = "output.pdf";

    public static void main(String[] args) throws Exception {
        LoadDataInterface rss = new RSSLoader(LINK_3);

        MakerInterface rssText = new TextMaker(rss.LoadData());
        rssText.make(TEXT_FILE_NAME);

        MakerInterface rssHTML = new HTMLMaker(rss.LoadData());
        rssHTML.make(HTML_FILE_NAME);

        MakerInterface rssPDF = new PDFMaker(rss.LoadData());
        rssPDF.make(PDF_FILE_NAME);
    }
}
