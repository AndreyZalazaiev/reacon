package andrew.projects.reacon.Util;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DateFormater {
    LocalDateTime date;

    public String GetDate()
    {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    public String GetTime()
    {
        return date.format(DateTimeFormatter.ISO_LOCAL_TIME).substring(0,8);
    }
}
