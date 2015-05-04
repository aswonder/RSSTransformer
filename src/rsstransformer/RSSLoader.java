package rsstransformer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrey S. Divov
 */
public class RSSLoader {

    RSSLoader(String link) {
        try {
            loadRSS(link);
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(RSSLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSSLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            return "null";
        }
    }

    private static String getEnclosure(Element el, String tag) {
        if (el.getElementsByTagName(tag).getLength() > 0) {
            String result = "";
            for (int i = 0; i < el.getElementsByTagName(tag).getLength(); i++) {
                result += String.valueOf(el.getElementsByTagName("enclosure").item(0).getChildNodes().item(0).getNodeValue());
            }
            return result.trim();
        } else {
            return "null";
        }
    }

    public void loadRSS(String link) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(link);
        Element el = document.getDocumentElement();

        NodeList channel = document.getElementsByTagName("channel");
        Element channelEl = (Element) channel.item(0);
        System.out.println(getNodeValue(channelEl, "language"));
        System.out.println(getNodeValue(channelEl, "title"));
        System.out.println(getNodeValue(channelEl, "description"));
        System.out.println(getNodeValue(channelEl, "link"));

        System.out.println("----------------------------------------");
        NodeList items = document.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element itemEl = (Element) items.item(i);
            System.out.println("----------------------------------------");
            System.out.println(getNodeValue(itemEl, "link"));
            System.out.println(getNodeValue(itemEl, "title"));
            System.out.println(getNodeValue(itemEl, "description"));
            System.out.println(getNodeValue(itemEl, "pubDate"));
            //System.out.println(getEnclosure(itemEl, "url"));
            System.out.println(getNodeValue(itemEl, "category"));
            System.out.println("----------------------------------------");
            System.out.println();

        }
    }

}
