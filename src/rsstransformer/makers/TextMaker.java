package rsstransformer.makers;

import rsstransformer.RSSData;
import rsstransformer.datamodel.RSSItem;
import rsstransformer.interfaces.MakerInterface;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrey S. Divov on 04.05.2015.
 */
public class TextMaker implements MakerInterface {

    private RSSData rssData;
    private String headerPattern = "";
    private String dataPattern
            = "<%s>\n" // link
            + "[%s]\n" // title
            + "%s\n" // description
            + "%s\n" // pubDate
            + "%s\n" // category
            + "---------------------------\n\n";
    private String footerPattern = "";

    public TextMaker(RSSData rssData) {
        this.rssData = rssData;
    }

    @Override
    public void make(String fileName) {

        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(headerPattern);
                for (RSSItem item : rssData.getRSSItems()) {
                    out.printf(dataPattern, item.getLink(),
                            item.getTitle(),
                            item.getDescription(),
                            item.getPubDate(),
                            item.getCategory());
                }
                out.print(footerPattern);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDataPattern() {
        return dataPattern;
    }

    public void setDataPattern(String dataPattern) {
        this.dataPattern = dataPattern;
    }

    public String getHeaderPattern() {
        return headerPattern;
    }

    public void setHeaderPattern(String headerPattern) {
        this.headerPattern = headerPattern;
    }

    public String getFooterPattern() {
        return footerPattern;
    }

    public void setFooterPattern(String footerPattern) {
        this.footerPattern = footerPattern;
    }

}
