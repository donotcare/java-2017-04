import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class SortTask extends RecursiveAction {
    private static final Random random = new Random();

    private final int start;
    private final int end;
    private final int[] arr;

    public SortTask(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected void compute() {
        if (start < end) {
            int partition = partition(start, end);
            invokeAll(new SortTask(start, partition - 1, arr), new SortTask(partition, end, arr));
        }
    }

    private int partition(int start, int end) {
        int pivot = getPivot(start, end);
        while (start < end) {
            while (arr[start] < pivot) {
                start++;
            }
            while (arr[end] > pivot)
                end--;
            if (arr[start] >= arr[end]) {
                swap(start, end);
            }
            start++;
        }

        return start;
    }

    private int getPivot(int start, int end) {
        return arr[random.nextInt(end - start) + start];
    }

    private void swap(int elementA, int elementB) {
        int temp = arr[elementA];
        arr[elementA] = arr[elementB];
        arr[elementB] = temp;
    }
}
