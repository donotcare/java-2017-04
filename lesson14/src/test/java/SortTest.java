import org.junit.Assert;
import org.junit.Test;

public class SortTest {

    @Test
    public void sortSimple() {
        int[] arr = {4, 2, 1, 6, 8, 9};
        QuickSort.parallelSort(arr);
        int[] expected = {1, 2, 4, 6, 8, 9};
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void sortWithDuplicates() {
        int[] arr = {4, 4, 4, 1, 4};
        QuickSort.parallelSort(arr);
        int[] expected = {1, 4, 4, 4, 4};
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void noSortNeed() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.parallelSort(arr);
        Assert.assertArrayEquals(arr, arr);
    }

    @Test
    public void sortReverse() {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort.parallelSort(arr);
        int[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, arr);
    }
}
