import java.util.Random;

public class QuickSelect {
  private int [] nums;

  public QuickSelect(int[] nums) {
    this.nums = nums;
  }

  public int select(int k) {
    return select(0, nums.length - 1, k - 1);
  }

  private int select(int firstIndex, int lastIndex, int k) {
    int pivot = partition(firstIndex, lastIndex);

    if (k < pivot) {
      return select(firstIndex, pivot - 1, k);
    } else if (k > pivot) {
      return select(pivot + 1, lastIndex, k);
    }

    return nums[pivot];
  }

  private int partition(int firstIndex, int lastIndex) {
    // random number from firstIndex to lastIndex inclusive
    int pivot = new Random().nextInt(lastIndex - firstIndex + 1) + firstIndex;

    swap(lastIndex, pivot); // pivot becomes lastIndex

    for (int i = firstIndex; i < lastIndex; i++) {
      // Finding the lastest k-th: nums[i] > nums[lastIndex]
      // Finding the smallest k-th: nums[i] < nums[lastIndex]
      if (nums[i] > nums[lastIndex]) {
        swap(i, firstIndex);
        firstIndex++;
      }
    }
    // Since pivot was lastIndex, we set pivot to the firstIndex by swap first and last
    swap(firstIndex, lastIndex);

    return firstIndex;
  }

  private void swap(int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {1, 5, 4, 8, -2};
    QuickSelect f = new QuickSelect(nums);

    //  -2 1 4 5 8
    //   8 5 4 1 -2
    //   1 2 3 4 5
    System.out.println(f.select(2)); // 5 : 2nd lastest

    // Left side of pivot is greater -> greater items 2nd largest
    // left side of the pivot is smaller -> 2nd smallest item
  }
}
