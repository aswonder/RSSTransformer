/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstransformer.makers;

import rsstransformer.RSSData;
import com.hp.gagawa.java.*;
import com.hp.gagawa.java.elements.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import rsstransformer.datamodel.RSSItem;
import rsstransformer.interfaces.MakerInterface;

/**
 *
 * @author Andrey S> Divov
 */
public class HTMLMaker implements MakerInterface {

    private RSSData rssData;

    public HTMLMaker(RSSData rssData) {
        this.rssData = rssData;
    }

    @Override
    public void make(String fileName) {

        File file = new File(fileName);

        Document document = new Document(DocumentType.XHTMLTransitional);
        document.head.appendChild(new Meta(
                "text/html;charset=utf-8").setHttpEquiv("Content-Type"));
        document.head.appendChild(new Title().appendChild(new Text(
                "Complex Example Title")));
        
        Link style = new Link();
        style.setRel("stylesheet");
        style.setHref("style.css");
        
        document.head.appendChild(style);
        Body body = document.body;

        Div channelInfoDiv = new Div();
        channelInfoDiv.setCSSClass("channelInfo");
        body.appendChild(channelInfoDiv);
        A linkRSS = new A();
        linkRSS.setHref(rssData.getChannelInfo().getLink());
        linkRSS.appendChild(new Text(rssData.getChannelInfo().getTitle()));
        channelInfoDiv.appendChild(linkRSS);
        channelInfoDiv.appendChild(new P().
                appendChild(new Text(rssData.getChannelInfo().getLanguage())));

        channelInfoDiv.appendChild(new P().
                appendChild(new Text(rssData.getChannelInfo().getDescription())));

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                for (RSSItem item : rssData.getRSSItems()) {
                    A link = new A();
                    link.setHref(item.getLink());
                    link.appendChild(new Text(item.getTitle()));
                    body.appendChild(new Div().appendChild(link));
                    body.appendChild(new Div().appendChild(new P().
                            appendChild(new Text(item.getDescription()))));
                    body.appendChild(new Div().appendChild(new P().
                            appendChild(new Text(item.getPubDate()))));
                    body.appendChild(new Div().appendChild(new P().
                            appendChild(new Text(item.getCategory()))));
                    body.appendChild(new Br());
                    body.appendChild(new Br());
                }
                out.print(document.write());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
