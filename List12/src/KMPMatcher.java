import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    CheckIfEqual checkifEqual = String::equals;

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        int patternLength = patternToFind.length();
        int textLength = textToSearch.length();
        if (textLength < patternLength) return null;
        if (textLength == patternLength
                && checkifEqual.check(textToSearch, patternToFind)) return Collections.singletonList(0);
        ArrayList<Integer> result = new ArrayList<>();

        int[] psArray = new int[patternLength];
        psArray = new PrefixSuffixFinder().find(patternToFind, patternLength, psArray);

        int patternIndex = 0;
        int textIndex = 0;
        while (textIndex < textLength) {
            if (patternToFind.charAt(patternIndex) == textToSearch.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == patternLength) {
                result.add(textIndex - patternIndex);
                patternIndex = psArray[patternIndex - 1];
            } else if (textIndex < textLength && patternToFind.charAt(patternIndex) != textToSearch.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = psArray[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }
        return result;
    }

    interface CheckIfEqual {
        boolean check(String a, String b);
    }
}
