package rsstransformer.datamodel;

/**
 * Created by Andrey S. Divov on 04.05.2015.
 */
public class ChannelInfo {

    private String language = "";
    private String title = "";
    private String description = "";
    private String link = "";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
