package org.tonyx;

import org.tonyxzt.language.core.ContentProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 03/08/12
 * Time: 14.19
 * To change this template use File | Settings | File Templates.
 */
public class YandecContentProvider implements ContentProvider{
    private static String[] supportedLangPairs={"it-ru","en-ru","ge-ru","fr-ru","it-ru","la-ru","kk-ru","ru-uk","ru-kk","uk-ru"};
    static Set<String> supported = new HashSet<String>();
    static {
        for (int i=0;i<supportedLangPairs.length;i++) {
            supported.add(supportedLangPairs[i]);
        }
    }

    @Override
    public String retrieve(String word, String langIn, String langOut) throws Exception {

        String toReturn="";

        //URL url = new URL("http://slovari.yandex.ru"+"/"+word+"/"+langIn+"/#lingvo/");
        URL url = new URL("http://slovari.yandex.ru"+"/"+ URLEncoder.encode(word,"UTF-8")+"/"+langIn+"/#lingvo/");

        InputStream response = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response,"UTF-8"));
        for (String line; (line = reader.readLine()) != null;) {
            toReturn+="\n";
            toReturn+=line;
        }

        reader.close();

        return toReturn;
    }

    @Override
    public String supportedLanguges() {
        String supp=
                "kazac\t kk\n"+
                "german\t de\n"+
                "italian\t it\n"+
                "french\t fr\n"+
                "ukrainan\t uk\n"+
                "spanish\t es\n"+
                "latin\t la\n"+
                "russian\t ru\n";
        String available="available combinations ori-target:\n";
        for(String comb :supported) {
            available+=comb+"\n ";
        }
        return supp+available;
    }

    @Override
    public String getInfoUrl() {
        return "http://slovari.yandex.ru/";
    }
}
