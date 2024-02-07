
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	// Helper methods
	public static String tail(String str) {
		if (str.length() == 0) {
			return "";
		}

		return str.substring(1);
	}

	public static char head(String str) {
		return str.charAt(0);
	}
	// end of helper methods

	// recursive method for calculating Levenshtein distance between two strings
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int distance = 0;
		int length1 = word1.length();
		int length2 = word2.length();

		// if else statements for calculating Levenshtein distance based on formula given in HW7 PDF
		if (length1 == 0) {
			distance = length2;
		} else if (length2 == 0) {
			distance = length1;
		} else if (head(word1) == head(word2)) {
			distance = levenshtein(tail(word1), tail(word2));
		} else {
			distance = 1 + Math.min(levenshtein(tail(word1), word2),
					Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2))));
		}
		
		return distance;
	}

	// read dictionary
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int[] levenshteins = new int[dictionary.length];
		int min = 0;

		// calculate Levenshtein distance for each word in dictionary
		for (int i = 0; i < dictionary.length; i++) {
			levenshteins[i] = levenshtein(word, dictionary[i]);
		}

		// find minimum Levenshtein distance
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshteins[i] < levenshteins[min]) {
				min = i;
			}
		}

		// if Levenshtein distance is less than threshold, return word
		if (levenshteins[min] <= threshold) {
			return dictionary[min];
		}

		return word;
	}
}