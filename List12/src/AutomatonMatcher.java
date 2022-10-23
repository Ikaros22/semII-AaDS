import java.util.*;

public class AutomatonMatcher implements IStringMatcher {
    MakeList makeList;
    CheckIfEqual checkifEqual;
    LinkedList<String> patternList;
    int matching;
    int currentPosition;
    LinkedList<String> textList;
    ArrayList<Integer> result;
    int[] jumpTable;

    public AutomatonMatcher() {
        makeList = (s) -> new LinkedList<>(Arrays.asList(s.split("")));
        checkifEqual = String::equals;
    }

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        if (textToSearch.length() < patternToFind.length()) return null;
        if (textToSearch.length() == patternToFind.length()
                && checkifEqual.check(textToSearch, patternToFind))
            return Collections.singletonList(0);
        setup(textToSearch, patternToFind);

        if (checkifEqual.check(textList.getFirst(), patternList.getFirst()))
            checkEquality();

        compareLoop();

        return result;
    }

    private void checkEquality() {
        if (matching == patternList.size()) matching = 0;

        if (patternList.size() + currentPosition <= textList.size()) {
            while (matching < patternList.size() && checkifEqual.check(textList.get(currentPosition + matching), patternList.get(matching)))
                matching++;

            if (matching == patternList.size()) result.add(currentPosition);
            currentPosition += matching;
        } else {
            if (jumpTable[matching] != 0) {
                currentPosition += jumpTable[matching];
            } else
                currentPosition++;
        }
    }

    private void compareLoop() {
        while (currentPosition < textList.size()) {
            if (checkifEqual.check(textList.get(currentPosition), patternList.getFirst()))
                checkEquality();
            else
                currentPosition++;
        }
    }

    private void setup(String textToSearch, String patternToFind) {
        patternList = makeList.convert(patternToFind);
        textList = makeList.convert(textToSearch);
        result = new ArrayList<>();
        matching = 0;
        currentPosition = 0;
        jumpTable = new int[patternList.size()];
        jumpTable = new PrefixSuffixFinder().find(patternToFind, patternList.size(), jumpTable);

    }

    interface MakeList {
        LinkedList<String> convert(String stringToConvert);
    }

    interface CheckIfEqual {
        boolean check(String a, String b);
    }
}
