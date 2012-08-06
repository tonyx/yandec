package org.tonyx;

import org.tonyxzt.language.core.InputWordRemapper;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 06/08/12
 * Time: 19.25
 * To change this template use File | Settings | File Templates.
 */
public class YandecInputWordRemapper implements InputWordRemapper {
    @Override
    public String remappedInputWord(String inWord, String htmlContent) {
        String result ="";
        try  {
            result = htmlContent.split("<h1 class=\"b-translation__title i-bem\">")[1].split("<div")[0].trim();
        } catch(Exception e)
        {
            //System.err.print(e.toString());
            // index array out of bound error or whatever
        }
        if (!result.equals(inWord)&&!"".equals(result.trim()))
            return result;
        else
            return inWord;
    }
}
