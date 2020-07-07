package andrew.projects.reacon.Util;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DateFormater {
    LocalDateTime date;
    public String ToDateTimeFormat()
    {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
