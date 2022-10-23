public class PrefixSuffixFinder {
    public int[] find(String pattern, int patternLength, int[] prefixSuffixArray) {
        int size = 0;
        int index = 1;
        prefixSuffixArray[0] = 0;
        while (index < patternLength) {
            if (pattern.charAt(index) == pattern.charAt(size)) {
                size++;
                prefixSuffixArray[index] = size;
                index++;
            } else {
                if (size != 0) {
                    size = prefixSuffixArray[size - 1];
                } else {
                    prefixSuffixArray[index] = size;
                    index++;
                }
            }
        }
        return prefixSuffixArray;
    }
}
