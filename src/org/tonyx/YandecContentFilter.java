package org.tonyx;

import org.tonyxzt.language.core.ContentFilter;
import test.org.tonyx.ContentFotografare;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 03/08/12
 * Time: 15.01
 * To change this template use File | Settings | File Templates.
 */
public class YandecContentFilter implements ContentFilter {

    @Override
    public String filter(String s) {
        String[] splitted;
        String flattedToReturn="";
        if (s.contains("acronym")) {
            splitted=s.split("acronym");
            for(int i=1;i<splitted.length;i++) {
                flattedToReturn+=splitted[i];
            }
        }
        //return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\".\">","\n");
        //return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("\\<.*?>"," ");
        //return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ");
        //return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ").replaceAll(".*?\\)\">(.*)","$1");

        //return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ").replaceAll("[0-9]+\\)\">(.*)","$1");

        //String matchingWord = matchingWord(s);
        //return matchingWord+" : "+flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ").replaceAll("[0-9]+\\)\">(.*)","$1").replace("\n",";\t");

        return flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ").replaceAll("[0-9]+\\)\">(.*)","$1").replace("\n",";\t");

    }

    private String matchingWord(String theContent) {
        String result ="";
        try  {
             result = theContent.split("<h1 class=\"b-translation__title i-bem\">")[1].split("<div")[0].trim();
        } catch(Exception e)
        { // index array out of bound error or whatever}
        }
        return result;
    }
}
