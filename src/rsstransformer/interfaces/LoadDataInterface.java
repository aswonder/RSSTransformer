package rsstransformer.interfaces;

import org.xml.sax.SAXException;
import rsstransformer.RSSData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Andrey S. Divov on 04.05.2015.
 */
public interface LoadDataInterface {
    public RSSData LoadData() throws ParserConfigurationException, SAXException, IOException;
}
