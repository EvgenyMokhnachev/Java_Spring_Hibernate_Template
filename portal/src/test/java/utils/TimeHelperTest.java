package utils;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
public class TimeHelperTest {
    private final long MAX_VALUE =  99 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000;

    @Autowired
    private TimeHelper timeHelper;

    @Test
    public void getTimeFromMillis() {
        String time = timeHelper.getTimeFromMillis(0);
        assertEquals("00:00:00", time);

        time = timeHelper.getTimeFromMillis(15 * 60 * 60 * 1000 + 4 * 60 * 1000 + 8 * 1000);
        assertEquals("15:04:08", time);

        time = timeHelper.getTimeFromMillis(MAX_VALUE);
        assertEquals("99:59:59", time);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNegativeMillis() {
        timeHelper.getTimeFromMillis(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getGreaterThanMax() {
        timeHelper.getTimeFromMillis(MAX_VALUE + 1);
    }

    @Test
    public void ago(){
        DateTime end = new DateTime("2015-07-06T20:00");

        String timeAgo = timeHelper.getTimeAgo(new DateTime("2014-01-05T19:30"), end);
        assertEquals("2014 January, 5", timeAgo);

        timeAgo = timeHelper.getTimeAgo(new DateTime("2015-01-05T19:30"), end);
        assertEquals("January, 5", timeAgo);

        timeAgo = timeHelper.getTimeAgo(new DateTime("2015-07-05T23:30"), end);
        assertEquals("20 hours ago", timeAgo);

        timeAgo = timeHelper.getTimeAgo(new DateTime("2015-07-06T18:30"), end);
        assertEquals("1 hour ago", timeAgo);

        timeAgo = timeHelper.getTimeAgo(new DateTime("2015-07-06T19:15"), end);
        assertEquals("45 minutes ago", timeAgo);

        timeAgo = timeHelper.getTimeAgo(new DateTime("2015-07-06T20:00"), end);
        assertEquals("a moment ago", timeAgo);

    }

}
