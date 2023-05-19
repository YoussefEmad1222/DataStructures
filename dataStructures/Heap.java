public class Heap {
      int heapSize;
      int[] heap;
      int capacity;

      Heap(int size) {
            this.heapSize = size;
            this.capacity = size * 2;
            heap = new int[size];
      }

      Heap(int[] arr) {
            this.heapSize = arr.length;
            this.capacity = arr.length;
            heap = arr;
      }
}