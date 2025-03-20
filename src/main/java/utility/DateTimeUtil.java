package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static String getTimeStamp() {
		//SimpleDateFormat dateFormat =new SimpleDateFormat("MMddhhmmss");
		//String formatedTimeStamp = dateFormat.format(new Date());
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMdd_HHmmss"));
		return timestamp;
	}
}
