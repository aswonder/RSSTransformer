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
    private String pattern
            = "---------------------------------------\n"
            + "<%s>\n" // link
            + "[%s]\n" // title
            + "%s\n"   // description
            + "%s\n"   // pubDate
            + "%s\n"   // category
            + "\n";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

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
                for (RSSItem item : rssData.getRSSItems()) {
                    out.printf(pattern, item.getLink(),
                            item.getTitle(),
                            item.getDescription(),
                            item.getPubDate(),
                            item.getCategory());
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
