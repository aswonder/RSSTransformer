package rsstransformer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import rsstransformer.datamodel.ChannelInfo;
import rsstransformer.datamodel.RSSItem;
import rsstransformer.interfaces.LoadDataInterface;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrey S. Divov
 */
public class RSSLoader implements LoadDataInterface {

    private static final String NULL_STRING = "";
    private Document document;

    RSSLoader(String link) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        document = documentBuilder.parse(link);
    }

    RSSLoader(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        document = documentBuilder.parse(file);
    }

    private static String getNodeValue(Element el, String tag) {
        if (el.getElementsByTagName(tag).getLength() > 0) {
            String result = "";
            for (int i = 0; i < el.getElementsByTagName(tag).
                    item(0).getChildNodes().getLength(); i++) {
                result += el.getElementsByTagName(tag).item(0).
                        getChildNodes().item(i).getNodeValue();
            }
            return result.trim();
        } else {
            return NULL_STRING;
        }
    }

    @Override
    public RSSData LoadData() {
        ChannelInfo channelInfo = new ChannelInfo();
        List<RSSItem> rssItems = new LinkedList<>();
        RSSData rssData = new RSSData();

        NodeList channel = document.getElementsByTagName("channel");
        Element channelEl = (Element) channel.item(0);

        channelInfo.setLanguage(getNodeValue(channelEl, "language"));
        channelInfo.setTitle(getNodeValue(channelEl, "title"));
        channelInfo.setDescription(getNodeValue(channelEl, "description"));
        channelInfo.setLink(getNodeValue(channelEl, "link"));

        NodeList items = document.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element itemEl = (Element) items.item(i);

            rssItems.add(new RSSItem(
                    getNodeValue(itemEl, "link"),
                    getNodeValue(itemEl, "title"),
                    getNodeValue(itemEl, "description"),
                    getNodeValue(itemEl, "pubDate"),
                    getNodeValue(itemEl, "category")));
        }
        rssData.setChannelInfo(channelInfo);
        rssData.setRSSItems(rssItems);
        return rssData;
    }
}
