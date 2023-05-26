import java.util.Arrays;

public class MaxHeap {
      int heapSize;
      int[] heap;
      int capacity;

      // constructor to build heap from size
      MaxHeap() {
            this.heapSize = 0;
            this.capacity = 15;
            this.heap = new int[this.capacity];
      }

      // constructor to build heap from array
      MaxHeap(int[] arr) {
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
            while (i > 0 && this.heap[parent(i)] < this.heap[i]) {
                  swap(parent(i), i);
                  i = parent(i);
            }
            this.heapSize++;
            return true;
      }

      // delete the minimum element in the heap with complexity O(logN)
      private boolean deleteMax() {
            if (heapSize == 0) {
                  return false;
            }
            swap(0, heapSize - 1);
            heapSize--;
            int i = 0;
            while (i < heapSize) {
                  int rightChild = right_child(i);
                  int leftChild = left_child(i);

                  if (rightChild < heapSize && leftChild < heapSize
                              && heap[rightChild] > heap[i] && heap[leftChild] > heap[i]) {
                        if (heap[rightChild] > heap[leftChild]) {
                              swap(rightChild, i);
                              i = rightChild;
                        } else {
                              swap(leftChild, i);
                              i = leftChild;
                        }
                  } else if (rightChild < heapSize && heap[rightChild] > heap[i]) {
                        swap(rightChild, i);
                        i = rightChild;
                  } else if (leftChild < heapSize && heap[leftChild] > heap[i]) {
                        swap(leftChild, i);
                        i = leftChild;
                  } else {
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

      int extractMax() {
            int max = this.heap[0];
            boolean valid = deleteMax();
            if (valid) {
                  return max;
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

      // heapify the subtree rooted at index i with complexity O(logN)
      private void MaxHeapify(int i) {
            int largest = i;
            int left = left_child(i);
            int right = right_child(i);
            if (left < this.heapSize && this.heap[left] > this.heap[largest]) {
                  largest = left;
            }
            if (right < this.heapSize && this.heap[right] > this.heap[largest]) {
                  largest = right;
            }
            if (largest != i) {
                  swap(i, largest);
                  MaxHeapify(largest);
            }

      }

      // heapify the entire heap with complexity O(N)
      void MinHeapify() {
            for (int i = this.heapSize / 2 - 1; i >= 0; i--) {
                  MaxHeapify(i);
            }
      }

      int[] heapSort(int[] arr) {
            MaxHeap maxHeap = new MaxHeap(arr);
            int[] sortedArr = new int[arr.length];
            for (int i = arr.length - 1; i >= 0; i--) {
                  sortedArr[i] = maxHeap.extractMax();
            }
            return sortedArr;
      }

      public static void main(String[] args) {
            MaxHeap minHeap = new MaxHeap();
            minHeap.insertElement(3);
            minHeap.insertElement(2);
            minHeap.insertElement(9);
            minHeap.insertElement(10);
            minHeap.insertElement(1);
            minHeap.insertElement(15);
            
            minHeap.insertElement(5);
            minHeap.insertElement(4);
            minHeap.insertElement(6);
            minHeap.insertElement(7);
            minHeap.insertElement(45);
            minHeap.insertElement(8);
           
            minHeap.printHeap();
            System.out.println();

            int arr[] = { 12, 11, 13, 5, 6, 7, 8 };
            int[] sortedArr = minHeap.heapSort(arr);
            System.out.println(Arrays.toString(sortedArr));
      }

}