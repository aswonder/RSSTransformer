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

        // Description html
        Document document = new Document(DocumentType.XHTMLTransitional);
        Meta charset = new Meta("text/html;charset=utf-8").setHttpEquiv("Content-Type");
        Title htmlTitle = new Title().appendChild(new Text("RSS"));

        Link cssSheet = new Link();
        cssSheet.setRel("stylesheet");
        cssSheet.setHref("style.css");

        document.head.appendChild(charset);
        document.head.appendChild(htmlTitle);
        document.head.appendChild(cssSheet);
        // end of Description html

        A linkRSS = new A();
        linkRSS.setHref(rssData.getChannelInfo().getLink());
        linkRSS.appendChild(new Text(rssData.getChannelInfo().getTitle()));

        Div channelInfoDiv = new Div();
        channelInfoDiv.setCSSClass("channelInfo");
        channelInfoDiv.appendChild(linkRSS);

        channelInfoDiv.appendChild(new P().
                appendChild(new Text(rssData.getChannelInfo().getLanguage())));

        channelInfoDiv.appendChild(new P().
                appendChild(new Text(rssData.getChannelInfo().getDescription())));

        Body body = document.body;
        body.appendChild(channelInfoDiv);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                for (RSSItem item : rssData.getRSSItems()) {

                    A link = new A();
                    link.setHref(item.getLink());
                    link.appendChild(new Text(item.getTitle()));

                    P pDesc = new P();
                    pDesc.appendChild(new Text(item.getDescription()));

                    P pPubDate = new P();
                    pDesc.appendChild(new Text(item.getPubDate()));

                    P pCat = new P();
                    pDesc.appendChild(new Text(item.getCategory()));

                    Div divItem = new Div();
                    divItem.appendChild(link);
                    divItem.appendChild(pDesc);
                    divItem.appendChild(pPubDate);
                    divItem.appendChild(pCat);

                    body.appendChild(divItem);
                    body.appendChild(new Br());
                }
                out.print(document.write());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
