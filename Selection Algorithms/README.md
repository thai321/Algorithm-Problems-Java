# Selection Algorithms
- Selection algorithm is an algorithm for finding the <b>k-th</b> smallest number in a list/ array such a number is called the k-th order statistic
- For example: finding maximum, minimum or median
- The aim is to achieve `O(N)` linear time complexity for this particular operation !!! ~ not that easy
- Methods: quickselect, media of median method...

### Sorting
- Intuition: let's sort the array in which we want to find the given titem
- After sorting -> we can access the item with the help of the index
- For example: if we sort an array in descending order, the array[0] yields the maximum item
- Inefiicient approach: if we want to find just a single item (maximum, minimum or median)
- Efficient approach: if we want to find several items at the same time
- Why? `O(N*log(N))  versus O(N)`
- Intuition: selection can be reduced to sorting and vice versa

### Data Structures:
+ We can use a data structure in order to find items
+ Sublinear time can be reached: `O(log N)`
+ For example: constrcut a balanced binary search tree or a heap
+ Problem: it has some memory complexity, we have to contruct the data strcuture first!!!
+ So in overall not the best solution

### Online Selection:
- Online algorithm: we want to find a given item (maximum, minimum or median) for a stream
- We keep downloading data and we want to find items at runtime
- <u>Problem</u>: We do not know all the values in advance !!!
- We will not be able to construct an alggorithm that finds the best solution: we can have a good guess ... a value that probably the once we are looking for
- "Secretary problem"

## Quickselect
- It is a selection algorithm to find the <b>k-th</b> smallest/ largest item in an <b>unordered array</b>
- Hoare constructed the algorithm -> <b>"Hoare-algoirthm"</b>
- <b>It has a very good average case running time: `O(N)`</b>
- Worst case scenario: O(N<sup>2</sup>)
- <b>In-place algorithm</b>
- Concept is similar to quicksort:
  - Choose a pivot element at random Partition the array
  - Partition the array
  - Instead of recursing into both sides, we just take one side
  - <b>O(N*logN) --> O(N)</b>

## `Hoare algorithm`
1) `Parition`
    - The parition  method is just for partiting the array according to the pivot:
      - Choose a pivot value at random: we generate a random number in the range [firstIndex, lastIndex]
      - Re-arranges the list in a way that all elements less than pivot are on left side of pivot and others on right. it then returns index of the pivot element

Generate a pivot item ar random. Let's say at 5
| 7 | -2 |<span style="background-color: #FFFF00"> 5 </span>| 8 | 1  | 6 |
|---|----|---|---|----|---|

Then,

| 1 | -2 |<span style="background-color: #FFFF00"> 5 </span>| 8 | 7  | 6 |
|---|----|---|---|----|---|

- We are done, we return the index of the pivot! Of course in the course of the algorithm, we may have to make several parititon procedure!!!
- `Important`: we just need one half of the array:
  - Left side: if we want to find the small items
    - For example: third smallest value etc.
  - Right subarray: we want the large items
    - For example: second largest value etc.

2) `Select`
- After the partitioning -> we are looking for the <b>k-th</b> smallest item for example. So we keep the left subarray in the partition phase
  - After partitioning <b><u>there are 3 cases</b></u>
      1. `k == pivot`
         + It means we have found the <b>k-th</b> smallest item we are after, because this is how partitioning works: there are exactly <b>k-1</b> items that are smallest than the <b>pivot</b> // in this case <b> `pivot == k`</b> !!!
      2. `k < pivot`
          + The <b>k-th</b> smallest item is on the left side of the pivot, thats why we can discard the other subarray (right) // unlike quicksort
      3. `k > pivot` <b>k-th</b> smallest item is on the right side of the pivot


| Performance            | Time complexity |
|------------------------|-----------------|
| Best case performance  | O(N)            |
| Worst case performance | O(N<sup>2</sup>)|
| Average                | O(N)            |

- Worst case runnint time: quadratic complexity
  - For example: we want to find the maximum in a sorted array and we always choose the first element to be the pivot

```java
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
```

## `Median of medians select`

- Each partition() phase takes `O(N)` time where N is smaller and smaller.
  - We keep discrading more and more items: If we are not able to discard many items on every iteration
    - -> the running time will be quaratic
      - -> O(N<sup>2</sup>) worst case running time
- We may make sure the running time remains `O(N)` if we keep discarding half of the array on every iteration
  - How? We have to pick a "good" pivot
    - If we pick the median as a pivot: there will be approximately same amount of items on the left and right subarrays!!!
  - It is the approximated median: but enough to make sure we discard more items

- It is basically the same as quickselect, the only difference is how we get the pivot value
  - quick select: we generate a random index
  - median of medians: we calculate the aproximated median
  - `O(N)` running time guaranteed
  - `O(log N)` worst case memory complexity

## `Introselect`
- It is a hubrid algorithm: combining two algorithms in order to take advaantage of the best features
  - Quickselect is in-place( no need extra memory), this is advantage
  - Median of medians select: always fast `O(N)`
- Let's combine them : introselect starts with quickselect in order to obtain good average performace, and then falls back to median of medians if progress is too slow.

## `Online Selection: the Secretary Problem`
- Online alorithm related problem
- we want to find the <b>k-th</b> smallest/ largest item of a stream
- Partition based algorithms can not be used: we do not know the data in advance
- The problem is to select (under these constraints) a specific element of the input sequence of data with largest probability

- Very important problem of optimal stopping theory <b>(NP-hard problem)</b>
- Also known as "best choice problem"
- <u>Problem</u>: we want to hire the best secretary out of N applicants. Applicant are interviewed one by one + after rejecting, the applicant can not be recalled. We can rank the applicant among all applicants interviewed so far, but we are unaware of the quality of yet unseen applicants.
- What is the optimal strategy?
- We want to maximize the probability of selecting the best applicant

- If we can mek the decision at the end: we just hav eto make a maximum finding
- It can be done in `O(N)`... no problem
- BUT we have to make the decision immediately !!!

<b>Solution:</b>
- Alwasy reject the first `n/e` applicatns and then we have to stop at the one who is better than al the previos ones
  - e: natural logarithm ~ 2.718...
  - It is very popular problem because it has a well defined solution
  - The probability of choosing the best applicant is `1/e`
  - So `37%` chance we find the optimal one
