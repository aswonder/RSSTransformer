package rsstransformer.datamodel;

/**
 * Created by Andrey S. Divov on 04.05.2015.
 */
public class RSSItem {

    private String link = "";
    private String title = "";
    private String description = "";
    private String pubDate = "";
    private String category = "";

    public RSSItem(String link, String title, String description, String pubDate, String category){
        this.link = link;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.category = category;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
