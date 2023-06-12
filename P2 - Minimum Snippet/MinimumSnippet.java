import java.util.Arrays;
import java.util.List;

public class MinimumSnippet {

	private int[] indiciesPos;
	private int minEnd;
	private int minStart;

	/*
	 * Compute minimum snippet. Given a document (represented as an List), and a set
	 * of terms (represented as a List), find the shortest subsequence of the
	 * document that contains all of the terms
	 * 
	 * @Parameter: document - An Iterable representing a set of words or character
	 * in a document
	 * 
	 * @Parameter: terms - The terms you need to look for. There should always be at
	 * least one term and your code should throw an IllegalArgumentException if
	 * "terms" is empty.
	 *
	 */
	public MinimumSnippet(Iterable<String> document, List<String> terms) {

		if (terms.isEmpty())
			throw new IllegalArgumentException("Terms can't be empty");

		// an array proportional to the length of "terms"
		int[] arr = new int[terms.size()];
		int length = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.MIN_VALUE;
		}

		/*
		 * since we want to track the index and the document as we traverse the
		 * elements, we need a back index and a front index to keeping track of the
		 * positions of the array.
		 * 
		 */

		int endingIndex = -1;

		for (String a : document) {
			// currently holding the length of the document
			endingIndex++;
			// t holds the index of the last character in the document
			// that matches to the last character in a given term
			int t = terms.indexOf(a);
			// the last index in the term has to be greater than 0
			if (t < 0)
				continue;
			// eningIndex is now set to the index of the last character in a given term
			arr[t] = endingIndex;

			int beginingIndex = minimum(arr);
			if (beginingIndex == Integer.MIN_VALUE)
				continue;

			// represents length of the given term
			int termScope = 1 + endingIndex - beginingIndex;
			if (length > termScope) {
				length = termScope;

				indiciesPos = Arrays.copyOf(arr, arr.length);
				minStart = beginingIndex;
				minEnd = endingIndex;
				// making sure that the length of a term is equal to the size
				if (length == terms.size())
					return;

			}
		}
	}

	/*
	 * Returns the minimum value in a given array of terms
	 */
	private int minimum(int[] postion) {
		int value = Integer.MAX_VALUE;
		for (int i : postion)
			if (value > i)
				value = i;
		return value;
	}

	/*
	 * Returns whether or not all terms were found in the document.
	 */
	public boolean foundAllTerms() {
		return indiciesPos != null;
	}

	/*
	 * Return the starting position of the snippet
	 */
	public int getStartingPos() {
		if (indiciesPos == null)
			throw new UnsupportedOperationException();
		return minStart;

	}

	/*
	 * Return the ending position of the snippet
	 */
	public int getEndingPos() {

		if (indiciesPos == null)
			throw new UnsupportedOperationException();
		return minEnd;
	}

	/*
	 * Return total number of elements contained in the snippet.
	 */
	public int getLength() {
		/*
		 * The ending index minus the starting index gives us one number less than the
		 * length of the term given so we add one to get the total number of elements.
		 */

		if (indiciesPos == null)
			throw new UnsupportedOperationException();
		return 1 + minEnd - minStart;

	}

	/*
	 * Returns the position of one of the search terms as it appears in the original
	 * document
	 */
	public int getPos(int index) {
		if (indiciesPos == null)
			throw new UnsupportedOperationException();
		return indiciesPos[index];

	}

}
