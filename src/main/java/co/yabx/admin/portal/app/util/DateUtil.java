package co.yabx.admin.portal.app.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil implements Serializable {
	public static String getDate() {
		SimpleDateFormat sd = new SimpleDateFormat("dd-mm-yyyy");
		return sd.format(new Date());
	}
}
