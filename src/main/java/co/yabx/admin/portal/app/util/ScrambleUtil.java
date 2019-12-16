package co.yabx.admin.portal.app.util;

import java.util.ArrayList;

public class ScrambleUtil {
	public final static String shuffleWord(final String word) {
		if (word.length() < 2)
			return word;
		// Create a list of characters.
		java.util.List<Character> characters = new ArrayList<Character>();

		// For each character in strWord, add that character to the list.
		for (char c : word.toCharArray()) {
			characters.add(c);
		}

		// StringBuilder objects are the most efficient way to perform variable-length
		// string concatenation.
		StringBuilder output = new StringBuilder(word.length());

		// Pick a letter randomly from the list, remove it from the list, and then
		// append it to the output string.
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * (characters.size()));
			output.append(characters.remove(randPicker));
		}
		if (word.equals(output.toString()))
			return shuffleWord(word);
		return output.toString();

	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(shuffleWord("a")); System.exit(0); }
	 */
}
