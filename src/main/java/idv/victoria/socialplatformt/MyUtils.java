package idv.victoria.socialplatformt;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyUtils {

    /*
     * Formatting timestamp into yyyy年MM月dd日HH时mm分ss秒
     *
     * @param Timestamp createdAt
     * @return
     * */
    public static String formatTimestamp(final Timestamp timestamp) {

        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Format timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");

        return localDateTime.format(formatter);
    }


}
