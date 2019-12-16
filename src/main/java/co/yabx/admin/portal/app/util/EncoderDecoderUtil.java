package co.yabx.admin.portal.app.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncoderDecoderUtil {

	public static String base64Encode(String data) {
		if (data != null && !data.isEmpty())
			return new String(base64Encode(data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
		else
			return data;
	}

	public static byte[] base64Encode(byte[] byteArray) {
		if (byteArray != null) {
			return Base64.getEncoder().encode(byteArray);
		}
		return byteArray;
	}

	public static String base64Decode(String str) {
		if (str != null)
			return new String(base64Decode(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
		return str;
	}

	public static byte[] base64Decode(byte[] byteArray) {
		if (byteArray != null && byteArray.length != 0) {
			return Base64.getDecoder().decode(byteArray);
		}
		return byteArray;
	}
}
