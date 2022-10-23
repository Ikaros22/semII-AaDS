import org.jetbrains.annotations.NotNull;

public record BubbleSorter(IChecker checker, Util tools) implements ISorter {
    @Override
    public void sort(int @NotNull [] values) {
        boolean changed = true;
            while(changed) {
                changed=false;
                for (int j = 0; j < values.length-1; j++) {
                    if (values[j] > values[j + 1]) {
                        values = tools.swaper(values, j, j + 1);
                        changed=true;
                    }
                }
                checker.check(values);
            }
        }
    }

