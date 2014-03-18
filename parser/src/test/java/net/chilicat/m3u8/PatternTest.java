package net.chilicat.m3u8;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dkuffner
 */
public class PatternTest extends TestCase {

    private final Logger log = Logger.getLogger(getClass().getName());

    public void testDateTime() throws Exception {
        String line = "#EXT-X-PROGRAM-DATE-TIME:2009-12-30T12:10:01+0100";
        long date = M3uConstants.Patterns.toDate(line, 0);
        Date expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse("2009-12-30T12:10:01+0100");
        assertEquals(expected.getTime(), date);

    }

    public void testEXT_KEY() throws Exception {
        assertNotNull(M3uConstants.Patterns.EXT_X_KEY.pattern());
    }

    public void testEXTINF_NO_MATCH() throws Exception {
        // not matching:
        Pattern patter = M3uConstants.Patterns.EXTINF;

        String[] list = new String[]{
                "#EXTINF:A200,Title",
                "#EXTINF:200A,Title",
                "#  EXTINF:200,Title",
                "#EXTI NF:200,Title",
        };

        int index = 0;
        for (String param : list) {
            log.info("Process: '" + param + "'");
            Matcher matcher = patter.matcher(param);

            printGroups(index, matcher);

            // assertEquals("Index: " + index, true, matcher.find());
            assertEquals("Index: " + index, false, matcher.matches());
            index++;
        }
    }


    public void testEXTINF() throws Exception {
        Pattern patter = M3uConstants.Patterns.EXTINF;

        String[] list = {
                "#EXTINF:200,Title",
                "\t  #EXTINF \t : \t 200 \t , \t Title\t",
                //"#EXTINF:200,",
        };

        int index = 0;
        for (String param : list) {
            log.info("Process: '" + param + "'");
            Matcher matcher = patter.matcher(param);
            assertEquals("Index: " + index, true, matcher.find());
            assertEquals("Index: " + index, true, matcher.matches());
            assertEquals("Index: " + index, 200, Integer.valueOf(matcher.group(1).trim()).intValue());
            assertEquals("Index: " + index, "Title", matcher.group(2).trim());

            printGroups(index, matcher);

            index++;
        }
    }

    private void printGroups(int index, Matcher matcher) {
        if (matcher.find()) {
            log.info("Pattern: " + matcher.pattern());
            log.info("Group count: " + matcher.groupCount());
            for (int i = 0; i < matcher.groupCount(); i++) {
                log.info("\t" + index + ":" + i + " '" + matcher.group(i) + "'");
            }
        } else {
            log.info("No matches");
        }
    }

}
