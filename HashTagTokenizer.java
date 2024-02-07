

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	// check if word exists in dictionary
	public static boolean existInDictionary(String word, String []dictionary) {
		int size = word.length();

		// loop through dictionary then loop through word since I don't know if .equals is allowed
		for (int i = 0; i < dictionary.length; i++) {
			if (size == dictionary[i].length()) {
				for (int j = 0; j < size; j++) {
					if (word.charAt(j) != dictionary[i].charAt(j)) {
						break;
					} else if (j == size - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// recursive function that prints out words detected in hashtag that exist in dictionary
	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		
		hashtag = hashtag.toLowerCase();
        int N = hashtag.length();

		// Recursive case: check if the first N characters of the hashtag exist in the dictionary.
        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtag.substring(0, i), dictionary)) {
				System.out.println(hashtag.substring(0, i));
				N = i;
				break;
			}
		}

		breakHashTag(hashtag.substring(N), dictionary);
    }

}
