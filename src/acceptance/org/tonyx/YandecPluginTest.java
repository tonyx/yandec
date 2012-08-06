package acceptance.org.tonyx;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.tonyx.YandecContentFilter;
import org.tonyx.YandecContentProvider;
import org.tonyxzt.language.core.GenericDictionary;
import org.tonyxzt.language.core.Translator;
import org.tonyxzt.language.io.OutStream;
import org.tonyxzt.language.util.BrowserActivator;
import org.tonyxzt.language.util.CommandLineToStatusClassWrapper;
import org.tonyxzt.language.util.FakeBrowserActivator;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 03/08/12
 * Time: 15.44
 * To change this template use File | Settings | File Templates.
 */
public class YandecPluginTest {
    Map<String,GenericDictionary> mapDictionaries;
    OutStream ios;
    BrowserActivator browserActivator;

    @Before
    public void SetUp() {
        browserActivator = mock(FakeBrowserActivator.class);
        ios = mock(OutStream.class);

        mapDictionaries = new HashMap<String,GenericDictionary>(){
            {
                put("yandec",new GenericDictionary("yandec",new YandecContentProvider(),new YandecContentFilter()));
            }
        };
    }

    @Test
    public void forUnsupportedLanguageShouldGetAWarningMessage() throws Exception {
        ArgumentMatcher<String> shouliovere = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("unresolved dictionary");
            }
        };
        // given
        String[] command = new String[] {"--dic=unresolved"};
        CommandLineToStatusClassWrapper commandLineToStatusClassWrapper  = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineToStatusClassWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(shouliovere));
        //Assert.assertTrue(getContent().contains("unresolved dictionary"));
    }


    @Test
    public void aWordContainedInTheDataBaseWillBeReturned() throws Exception {
        // given
        ArgumentMatcher<String> privet = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("дом");
            }
        };
        String[] command = new String[]{"--dic=yandec","--oriLang=it","--targetLang=ru","casa"};
        CommandLineToStatusClassWrapper commandLineWrapper = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(privet));
    }


}
