/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstransformer.makers;

import rsstransformer.RSSData;

/**
 *
 * @author filin
 */
public class HTMLMaker extends TextMaker {

    public HTMLMaker(RSSData rssData) {
        super(rssData);
        setHeaderPattern("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 "
                + "Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-"
                + "strict.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\""
                + " xml:lang=\"en\" lang=\"en\">\n<head>\n"
                + "<meta http-equiv=\"content-type\" content=\"text/html; "
                + "charset=utf-8\" /></head><body>");
        setDataPattern("<a href=\"%s\">%s</a>\n<p>%s</p>\n<p>%s</p>\n<p>%s</p>\n<hr>");
        setFooterPattern("</body></html>");
    }
}
