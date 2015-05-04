package rsstransformer;

import rsstransformer.datamodel.ChannelInfo;
import rsstransformer.datamodel.RSSItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey S. Divov on 04.05.2015.
 */
public class RSSData {

    private ChannelInfo channelInfo = new ChannelInfo();
    private List<RSSItem> rssItems = new LinkedList<RSSItem>();

    public void setChannelInfo(ChannelInfo channelInfo) {
        this.channelInfo = channelInfo;
    }

    public ChannelInfo getChannelInfo() {
        return channelInfo;
    }

    public void setRSSItems(List<RSSItem> rssItems) {
        this.rssItems = rssItems;
    }

    public List<RSSItem> getRSSItems() {
        return rssItems;
    }

}
