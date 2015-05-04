package rsstransformer;

/**
 *
 * @author Andrey S. Divov
 */
public class RSSTransformer {

    public static final String LINK_1  = "http://www.cnews.ru/inc/rss/spo_rss.xml";
    public static final String LINK_2  = "http://lenta.ru/rss/articles/";
    public static final String LINK_3  = "http://www.vesti.ru/vesti.rss";

    public static void main(String[] args) throws Exception {
        RSSLoader rss = new RSSLoader(LINK_2);

    }
}
