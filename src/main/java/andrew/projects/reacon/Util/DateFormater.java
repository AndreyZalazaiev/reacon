package andrew.projects.reacon.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateFormater {
    LocalDateTime date;

    public String GetDate() {

        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String GetTime() {
        return date.format(DateTimeFormatter.ISO_LOCAL_TIME).substring(0, 8);
    }
    public String GetMessageFormat()
    {
        return date.format(DateTimeFormatter.ofPattern("MMM dd"));
    }
}
