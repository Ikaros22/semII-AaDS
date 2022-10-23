import org.jetbrains.annotations.NotNull;

public record InsertionSorter(IChecker checker) implements ISorter {

    @Override
    public void sort(int @NotNull [] values) {
        for (int j = 1; j < values.length; j++) {
            int key = values[j];
            int index = j - 1;

            while ((index >= 0) && (values[index] > key)) {
                values[index + 1] = values[index];
                index--;
            }

            values[index + 1] = key;
            checker.check(values);
        }
    }
}

