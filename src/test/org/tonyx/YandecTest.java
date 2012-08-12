package test.org.tonyx;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.tonyx.YandecContentFilter;
import org.tonyxzt.language.core.ContentFilter;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 06/08/12
 * Time: 16.55
 * To change this template use File | Settings | File Templates.
 */
public class YandecTest {
    @Test
    public void RegeXpToTakeFotografareWord() {
        String result = ContentFotografare.content.split("<h1 class=\"b-translation__title i-bem\">")[1].split("<div")[0];
        System.out.println(result);
        assertTrue(result.contains("fotografare"));
        Assert.assertEquals("fotografare",result.trim());
    }

    @Test
    @Ignore
    public void ShouldContainFratellino() {
        // given
        ContentFilter yandecFilter = new YandecContentFilter();

        // when
        String theContent = yandecFilter.filter(ContentBratichka.content,"","");

        // then
        Assert.assertTrue(theContent.contains("fratellino"));

    }

    @Deprecated
    @Test
    public void sdafsf() {
        String reg = ".*<span class=\"b-translation-review__translation\">(.*)</span>.*";
        String content = "адфасдфsdfasfasdfa\nlskdfaskdf\nnlksjfas<span class=\"b-translation-review__translation\">fratellino</span>dfasfd\nfdasfda\n";
        System.out.println(Pattern.compile(reg, Pattern.DOTALL).matcher(content).replaceAll("$1"));
        Assert.assertEquals("fratellino",Pattern.compile(reg, Pattern.DOTALL).matcher(content).replaceAll("$1"));
    }

    @Deprecated
    @Test
    public void sdafsfdfasd() {
        String reg = ".*по-итальянски</a></h3><span class=\"b-translation-review__translation\">(.*)</span>.*";
        //String reg = ".*<span class=\"b-translation-review__translation\">(.*)</span>.*";

        String content = ContentBratichka.content;
        System.out.println(Pattern.compile(reg, Pattern.DOTALL).matcher(content).replaceAll("$1").split("<\\/span>")[0]);
        Assert.assertEquals("fratellino",(Pattern.compile(reg, Pattern.DOTALL).matcher(content).replaceAll("$1").split("<\\/span>")[0]));
    }

}
