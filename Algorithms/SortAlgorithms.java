public class SortAlgorithms {

    private void swap(int[] arr, int i, int j) {
        // swap the elements at index i and j
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // bubble sort algorithm
    // time complexity: O(n^2)
    // space complexity: O(1)
    void bubbleSort(int[] arr) {

        // Get the length of the array
        int length = arr.length;

        // Flag to indicate if any swaps were made during an iteration
        boolean swapped = false;

        // Loop through the array length times
        for (int i = 0; i < length; i++) {

            // Reset the "swapped" flag at the start of each iteration
            swapped = false;

            // Loop through the unsorted part of the array
            for (int j = 0; j < length - i - 1; j++) {

                // If two adjacent elements are in the wrong order
                if (arr[j] > arr[j + 1]) {

                    // Set the "swapped" flag to true
                    swapped = true;

                    // Swap the elements
                    swap(arr, j, j + 1);
                }
            }

            // If no swaps were made during an iteration, the array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    // selection sort algorithm
    // time complexity: O(n^2)
    // space complexity: O(1)
    void selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }

    }

    void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int nL = mid - left + 1;
        int nR = right - mid;
        int[] L = new int[nL];
        int[] R = new int[nR];
        for (int i = 0; i < nL; i++) {
            L[i] = arr[left + i];
        }
        for (int i = 0; i < nR; i++) {
            R[i] = arr[mid + 1 + i];
        }
        int i = 0, j = 0, k = left;
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < nL) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < nR) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void countingSort(int arr[]) {
        int n = arr.length;
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int range = max - min + 1;
        int[] count = new int[range];
        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        System.out.println("Bubble Sort");
        int[] arr = { 58, 90, 71, 79, 13, 44, 25, 88, 60, 83, 36, 95, 61, 5, 70, 81, 66, 14, 1, 49, 23, 97, 85, 40, 53,
                8, 33, 68, 6, 75, 30, 38, 16, 72, 93, 99, 21, 55, 87, 31, 22, 56, 3, 11, 64, 29, 35, 91, 19, 78, 63, 46,
                76, 41, 43, 84, 39, 4, 98, 92, 69, 80, 10, 50, 27, 77, 15, 67, 54, 2, 59, 48, 86, 45, 9, 7, 20, 18, 62,
                26, 34, 96, 51, 94, 32, 65, 74, 28, 42, 52, 73, 37, 82, 12, 89, 57, 24, 17, 47 };
        SortAlgorithms sortAlgorithms = new SortAlgorithms();
        sortAlgorithms.bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");// 1 2 3 4 5
        }
        System.out.println();
        System.out.println("********************");
        System.out.println("Selection Sort");
        int[] arr2 = { 58, 90, 71, 79, 13, 44, 25, 88, 60, 83, 36, 95, 61, 5, 70, 81, 66, 14, 1, 49, 23, 97, 85, 40, 53,
                8, 33, 68, 6, 75, 30, 38, 16, 72, 93, 99, 21, 55, 87, 31, 22, 56, 3, 11, 64, 29, 35, 91, 19, 78, 63, 46,
                76, 41, 43, 84, 39, 4, 98, 92, 69, 80, 10, 50, 27, 77, 15, 67, 54, 2, 59, 48, 86, 45, 9, 7, 20, 18, 62,
                26, 34, 96, 51, 94, 32, 65, 74, 28, 42, 52, 73, 37, 82, 12, 89, 57, 24, 17, 47 };
        sortAlgorithms.selectionSort(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        System.out.println("********************");
        System.out.println("Insertion Sort");
        int[] arr3 = { 58, 90, 71, 79, 13, 44, 25, 88, 60, 83, 36, 95, 61, 5, 70, 81, 66, 14, 1, 49, 23, 97, 85, 40, 53,
                8, 33, 68, 6, 75, 30, 38, 16, 72, 93, 99, 21, 55, 87, 31, 22, 56, 3, 11, 64, 29, 35, 91, 19, 78, 63, 46,
                76, 41, 43, 84, 39, 4, 98, 92, 69, 80, 10, 50, 27, 77, 15, 67, 54, 2, 59, 48, 86, 45, 9, 7, 20, 18, 62,
                26, 34, 96, 51, 94, 32, 65, 74, 28, 42, 52, 73, 37, 82, 12, 89, 57, 24, 17, 47 };
        sortAlgorithms.insertionSort(arr3);
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
        System.out.println("********************");
        System.out.println("Merge Sort");
        int[] arr4 = { 58, 90, 71, 79, 13, 44, 25, 88, 60, 83, 36, 95, 61, 5, 70, 81, 66, 14, 1, 49, 23, 97, 85, 40, 53,
                8, 33, 68, 6, 75, 30, 38, 16, 72, 93, 99, 21, 55, 87, 31, 22, 56, 3, 11, 64, 29, 35, 91, 19, 78, 63, 46,
                76, 41, 43, 84, 39, 4, 98, 92, 69, 80, 10, 50, 27, 77, 15, 67, 54, 2, 59, 48, 86, 45, 9, 7, 20, 18, 62,
                26, 34, 96, 51, 94, 32, 65, 74, 28, 42, 52, 73, 37, 82, 12, 89, 57, 24, 17, 47 };
        sortAlgorithms.mergeSort(arr4, 0, arr4.length - 1);
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
        System.out.println();
        System.out.println("********************");
        System.out.println("Counting Sort");
        int[] arr5 = { 58, 90, 71, 79, 13, 44, 25, 88, 60, 83, 36, 95, 61, 5, 70, 81, 66, 14, 1, 49, 23, 97, 85, 40, 53,
                8, 33, 68, 6, 75, 30, 38, 16, 72, 93, 99, 21, 55, 87, 31, 22, 56, 3, 11, 64, 29, 35, 91, 19, 78, 63, 46,
                76, 41, 43, 84, 39, 4, 98, 92, 69, 80, 10, 50, 27, 77, 15, 67, 54, 2, 59, 48, 86, 45, 9, 7, 20, 18, 62,
                26, 34, 96, 51, 94, 32, 65, 74, 28, 42, 52, 73, 37, 82, 12, 89, 57, 24, 17, 47 };
        sortAlgorithms.countingSort(arr5);
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(arr5[i] + " ");
        }
        System.out.println();
        System.out.println("********************");
        // mergeSort(arr, 0, arr.length - 1);
        // quickSort(arr, 0, arr.length - 1);
        // heapSort(arr);
        // countingSort(arr);
        // radixSort(arr);
        // quickSort(arr, 0, arr.length - 1);
        // bucketSort(arr);
        // shellSort(arr);
        // combSort(arr);
        // cycleSort(arr);
        // cocktailSort(arr);
        // gnomeSort(arr);
        // stoogeSort(arr, 0, arr.length - 1);
        // pancakeSort(arr);
        // bitonicSort(arr, 0, arr.length, true);
        // bogoSort(arr);
        // oddEvenSort(arr);
        // sleepSort(arr);
        // gravitySort(arr);
        // cocktailShakerSort(arr);
        // strandSort(arr);
        // treeSort(arr);

    }
}