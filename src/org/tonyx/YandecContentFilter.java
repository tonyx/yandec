package org.tonyx;

import org.tonyxzt.language.core.ContentFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 03/08/12
 * Time: 15.01
 * To change this template use File | Settings | File Templates.
 */
public class YandecContentFilter implements ContentFilter {
    static Map<String,String> langAssoc;

    static {
        langAssoc = new HashMap<String,String>();
        langAssoc.put("ru","Ru");
        langAssoc.put("it","It");
        langAssoc.put("kk","Kk");
        langAssoc.put("uk","Uk");
        langAssoc.put("fr","Fr");
        langAssoc.put("en","En");
        langAssoc.put("de","De");
    }

    public String filter(String s,String langIn, String langOut) {
        String[] splitted;
        String flattedToReturn="";
        if (s.contains("acronym")) {
            splitted=s.split("acronym");
            for(int i=1;i<splitted.length;i++) {
                flattedToReturn+=splitted[i];
            }
        }

        String plainMatch="";

        String essential = "\'Essential \\("+langAssoc.get(langIn.toLowerCase())+"-"+langAssoc.get(langOut.toLowerCase())+"\\)\', \'translation\':";

        if (s.contains(essential.replaceAll("\\\\",""))) {
            plainMatch = removeApostrophes(s.split(essential)[1].split("}")[0]);
        }

        String universal = "\'Universal \\("+langAssoc.get(langIn.toLowerCase())+"-"+langAssoc.get(langOut.toLowerCase())+"\\)\', \'translation\':";
        if (s.contains(universal.replaceAll("\\\\",""))) {
            plainMatch = removeApostrophes(s.split(universal)[1].split("}")[0]);
        }

        return plainMatch+";   "+flattedToReturn.split("<td class=\"l-page__gap-right\">")[0].replaceAll("<li id=\"","\n").replaceAll("</p><p>","\n").replaceAll("\\<.*?>"," ").replaceAll("[0-9]+\\)\">(.*)","$1").replace("\n",";\t");
    }

    private String removeApostrophes(String str) {
        try {
            return str.substring(str.indexOf("'")+1,str.lastIndexOf("'"));
        } catch (Exception e) {
            return str;
        }
    }
}
