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

    private static final String ITEMS_SEPARATOR = "---------------------------------------";
    private RSSData rssData;

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
                    out.println(item.getLink());
                    out.println(item.getTitle());
                    out.println(item.getDescription());
                    out.println(item.getPubDate());
                    out.println(item.getCategory());
                    out.println(ITEMS_SEPARATOR);
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
