package test.org.tonyx;

import junit.framework.Assert;
import org.junit.Test;

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

}
