package rsstransformer;

import rsstransformer.interfaces.LoadDataInterface;
import rsstransformer.interfaces.MakerInterface;
import rsstransformer.makers.TextMaker;

/**
 *
 * @author Andrey S. Divov
 */
public class RSSTransformer {

    private static final String LINK_1  = "http://www.cnews.ru/inc/rss/spo_rss.xml";
    private static final String LINK_2  = "http://lenta.ru/rss/articles/";
    private static final String LINK_3  = "http://www.vesti.ru/vesti.rss";
    private static final String FILE_NAME = "out.txt";

    public static void main(String[] args) throws Exception {
        LoadDataInterface rss = new RSSLoader(LINK_1);
        MakerInterface maker = new TextMaker(rss.LoadData());
        maker.make(FILE_NAME);
    }
}
