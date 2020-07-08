package andrew.projects.reacon.entities;

import andrew.projects.reacon.Util.DateFormater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)

public class DateFormatterTest {
    @Test
    public void DateConverting() {
        LocalDateTime currentDate = LocalDateTime.now();

        DateFormater df = new DateFormater();
        df.setDate(currentDate);

        String date = df.GetDate();
        String testDate = "20\\d{2}(-|\\/)((0[1-9])|(1[0-2]))(-|\\/)((0[1-9])|([1-2][0-9])|(3[0-1]))";

        Pattern pattern = Pattern.compile(testDate);
        Matcher matcher = pattern.matcher(date);

        Assert.assertTrue(matcher.matches());

    }
    @Test
    public void TimeConverting(){
        LocalDateTime currentDate = LocalDateTime.now();

        DateFormater df = new DateFormater();
        df.setDate(currentDate);

        String time = df.GetTime();
        String testTime = "^([0-1]?\\d|2[0-3])(?::([0-5]?\\d))?(?::([0-5]?\\d))?$";

        Pattern pattern = Pattern.compile(testTime);
        Matcher matcher = pattern.matcher(time);

        Assert.assertTrue(matcher.matches());
    }
}
