import java.util.Arrays;

public class MinHeap {
      int heapSize;
      int[] heap;
      int capacity;

      // constructor to build heap from size
      MinHeap() {
            this.heapSize = 0;
            this.capacity = 15;
            this.heap = new int[this.capacity];
      }

      // constructor to build heap from array
      MinHeap(int[] arr) {
            this.heapSize = arr.length;
            this.capacity = arr.length;
            this.heap = arr;
            MinHeapify();
      }

      // get the index of the parent
      private int parent(int i) {
            return (i - 1) / 2;
      }

      // get the index of the left child
      private int left_child(int i) {
            return 2 * i + 1;
      }

      // get the index of the right child
      private int right_child(int i) {
            return 2 * i + 2;
      }

      // swap elements in the heap
      private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
      }

      // get the size of the heap
      int getSize() {
            return this.heapSize;
      }

      // insert an element into the heap with complexity O(logN)
      boolean insertElement(int element) {
            // if the heap is full
            if (this.heapSize == this.capacity) {
                  this.capacity *= 2;
                  doubleTheHeap();
            }
            this.heap[this.heapSize] = element;
            int i = this.heapSize;
            while (i > 0 && this.heap[parent(i)] > this.heap[i]) {
                  swap(parent(i), i);
                  i = parent(i);
            }
            this.heapSize++;
            return true;
      }

      // delete the minimum element in the heap with complexity O(logN)
      private boolean deleteMin() {
            if (this.heapSize == 0) {
                  return false;
            }
            swap(0, this.heapSize - 1);
            this.heapSize--;
            int i = 0;
            while (i < this.heapSize) {
                  int right_child = right_child(i);
                  int left_child = left_child(i);
                  // if the right child and the left child are smaller than the parent and within
                  // the heap range
                  if (right_child < this.heapSize && left_child < this.heapSize && this.heap[right_child] < this.heap[i]
                              && this.heap[left_child] < this.heap[i]) {
                        // if the right child is smaller than the left child
                        if (this.heap[right_child] < this.heap[left_child]) {
                              swap(right_child, i);
                              i = right_child;
                        }
                        // if the left child is smaller than the right child
                        else {
                              swap(left_child, i);
                              i = left_child;
                        }
                  }
                  // if the right child only is smaller than the parent
                  else if (right_child < this.heapSize && this.heap[right_child] < this.heap[i]) {
                        swap(right_child, i);
                        i = right_child;

                  } // if the left child only is smaller than the parent
                  else if (left_child < this.heapSize && this.heap[left_child] < this.heap[i]) {
                        swap(left_child, i);
                        i = left_child;
                  }
                  // if the parent is smaller than the children
                  else {
                        break;
                  }
            }
            return true;
      }

      private void doubleTheHeap() {
            int[] newHeap = new int[this.capacity];
            for (int i = 0; i < this.heapSize; i++) {
                  newHeap[i] = this.heap[i];
            }
            this.heap = newHeap;

      }

      int extractMin() {
            int min = this.heap[0];
            boolean valid = deleteMin();
            if (valid) {
                  return min;
            } else {
                  System.out.println("Heap is empty");
                  return -1;
            }
      }

      boolean isEmpty() {
            return this.heapSize == 0;
      }

      boolean isFull() {
            return this.heapSize == this.capacity;
      }

      void printHeap() {
            for (int i = 0; i < this.heapSize; i++) {
                  System.out.print(this.heap[i] + " ");
            }
      }

      void MinHeapify(int i) {
            int smallest = i;
            int left = left_child(i);
            int right = right_child(i);
            if (left < this.heapSize && this.heap[left] < this.heap[smallest]) {
                  smallest = left;
            }
            if (right < this.heapSize && this.heap[right] < this.heap[smallest]) {
                  smallest = right;
            }
            if (smallest != i) {
                  swap(i, smallest);
                  MinHeapify(smallest);
            }

      }

      private void MinHeapify() {
            for (int i = this.heapSize / 2 - 1; i >= 0; i--) {
                  MinHeapify(i);
            }
      }

      int[] heapSort(int[] arr) {
            MinHeap minHeap = new MinHeap();
            for (int i = 0; i < arr.length; i++) {
                  minHeap.insertElement(arr[i]);
            }
            int[] sortedArr = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                  sortedArr[i] = minHeap.extractMin();
            }
            return sortedArr;
      }

      public static void main(String[] args) {
            MinHeap minHeap = new MinHeap();
            minHeap.insertElement(10);
            minHeap.insertElement(20);
            minHeap.insertElement(15);
            minHeap.insertElement(30);
            minHeap.insertElement(40);
            minHeap.insertElement(50);
            minHeap.insertElement(100);
            minHeap.insertElement(25);
            minHeap.insertElement(35);
            minHeap.insertElement(45);
            minHeap.insertElement(55);
            minHeap.insertElement(60);
            minHeap.insertElement(65);
            minHeap.insertElement(70);
            minHeap.printHeap();
            int arr[] = { 12, 11, 13, 5, 6, 7, 8 };
            int[] sortedArr = minHeap.heapSort(arr);
            System.out.println(Arrays.toString(sortedArr));
            MinHeap minHeap2 = new MinHeap(arr);
            minHeap2.printHeap();
      }

}