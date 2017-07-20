import java.util.concurrent.ForkJoinPool;

public class QuickSort {
    private final int[] arr;

    private final ForkJoinPool forkJoinPool = new ForkJoinPool(4);

    public QuickSort(int[] arr) {
        this.arr = arr;
    }

    public int[] parallelSort() {
        forkJoinPool.invoke(new SortTask(0, arr.length - 1, arr));
        return arr;
    }

    public static void parallelSort(int[] arr) {
        new QuickSort(arr).parallelSort();
    }
}
