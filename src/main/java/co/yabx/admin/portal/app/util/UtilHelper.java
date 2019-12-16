package co.yabx.admin.portal.app.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
 * 
 * @author Asad.ali
 *
 */
public class UtilHelper {

	static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

	public static String getCurrentTimestampAsString() {
		return String.valueOf(System.currentTimeMillis());
	}

	public static String getRandomString(int length) {
		return new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build().generate(length);
	}

	public static String getRandomStringUpperCase(int length) {
		return new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build().generate(length)
				.toUpperCase();
	}

	public static String getRandomStringLowerCase(int length) {
		return new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(CharacterPredicates.LETTERS).build()
				.generate(length).toLowerCase();
	}

	public static String getNumericString(int length) {
		return new RandomStringGenerator.Builder().withinRange('0', '9').filteredBy(CharacterPredicates.DIGITS).build()
				.generate(length);
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance) / 1000;
	}

	public static File zipBytes(List<byte[]> files, List<String> fileNames, String filename) {
		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// compress the files
			try ( // create the ZIP file
					ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));) {
				// compress the files
				int count = 0;
				for (byte[] file : files) {
					// add ZIP entry to output stream
					try (InputStream is = new ByteArrayInputStream(file); InputStream zis = new ZipInputStream(is);) {
						// add ZIP entry to output stream
						out.putNextEntry(new ZipEntry(fileNames.get(count++)));
						// transfer bytes from the file to the ZIP file
						int len;
						while ((len = is.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						// complete the entry
						out.closeEntry();
					}

				}
				// complete the ZIP file
			}
			return zipfile;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			Logger.getLogger(UtilHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static File zip(List<File> files, String filename) {
		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// compress the files
			try ( // create the ZIP file
					ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));) {
				// compress the files
				for (File file : files) {
					// add ZIP entry to output stream
					try (FileInputStream in = new FileInputStream(file)) {
						// add ZIP entry to output stream
						out.putNextEntry(new ZipEntry(file.getName()));
						// transfer bytes from the file to the ZIP file
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						// complete the entry
						out.closeEntry();
					}
				}
				// complete the ZIP file
			}
			return zipfile;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			Logger.getLogger(UtilHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static boolean deleteFileIfExists(String absFilePath) {
		try {
			File file = new File(absFilePath);
			return Files.deleteIfExists(file.toPath());
		} catch (IOException ex) {
			// Not required to catch IO Exception
			// Logger.getLogger(UtilHelper.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

	public static String removeMultipleSpaces(String string) {
		return string.trim().replaceAll(" +", " ");
	}

	public static String removeSubString(String string, String sub) {
		return string.replace(sub, "");
	}

	public static String removeSubStringLowercase(String string, String sub) {
		return string.toLowerCase().replace(sub.toLowerCase(), "");
	}

	public static Date getDateAddMinutes(int minutes) {
		Calendar c = getCalenderInstance();
		long t = c.getTimeInMillis();
		return new Date(t + (minutes * ONE_MINUTE_IN_MILLIS));
	}

	public static Date getFirstDayOfMonth() {
		Calendar c = getCalenderInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getFirstDayOfWeek() {
		// get today and clear time of day
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

		return cal.getTime();
	}

	public static Date getZerothHourOfDay() {
		Calendar c = getCalenderInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getZerothHourOfNextDay() {
		Calendar c = getCalenderInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	public static Date getFirstDayOfYear() {
		Calendar c = getCalenderInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getDate() {
		return new Date();
	}

	public static Calendar getCalenderInstance() {
		return Calendar.getInstance(); // this takes current date
	}
}
