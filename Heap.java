package DSA;

public class Heap {
    int[] minheap;
    int[] maxheap;
    int minsize;
    int maxsize;

    public Heap(int mincap, int maxcap) {
        minheap = new int[mincap];
        maxheap = new int[maxcap];
        minsize = maxsize = 0;
    }

    public void swap(int a, int b, int[] heap) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // ---------------- MIN HEAP ----------------
    public void minheapinsertion(int value) {
        if (minsize == minheap.length) {
            System.out.println("Min heap is full");
            return;
        }
        minheap[minsize] = value;
        int i = minsize;
        while (i > 0 && minheap[i] < minheap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2, minheap);
            i = (i - 1) / 2;
        }
        minsize++;
    }

    public int minheapdeletion() {
        if (minsize == 0) {
            System.out.println("Min heap is empty");
            return -1;
        }
        int root = minheap[0];
        minheap[0] = minheap[--minsize];
        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left >= minsize) break;
            int smaller = (right < minsize && minheap[right] < minheap[left]) ? right : left;
            if (minheap[i] > minheap[smaller]) {
                swap(i, smaller, minheap);
                i = smaller;
            } else break;
        }
        return root;
    }

    // ---------------- MAX HEAP ----------------
    public void maxheapinsertion(int value) {
        if (maxsize == maxheap.length) {
            System.out.println("Max heap is full");
            return;
        }
        maxheap[maxsize] = value;
        int i = maxsize;
        while (i > 0 && maxheap[i] > maxheap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2, maxheap);
            i = (i - 1) / 2;
        }
        maxsize++;
    }

    public int maxheapdeletion() {
        if (maxsize == 0) {
            System.out.println("Max heap is empty");
            return -1;
        }
        int root = maxheap[0];
        maxheap[0] = maxheap[--maxsize];
        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left >= maxsize) break;
            int larger = (right < maxsize && maxheap[right] > maxheap[left]) ? right : left;
            if (maxheap[i] < maxheap[larger]) {
                swap(i, larger, maxheap);
                i = larger;
            } else break;
        }
        return root;
    }

    // Utility print methods
    public void printMinHeap() {
        for (int i = 0; i < minsize; i++) System.out.print(minheap[i] + " ");
        System.out.println();
    }

    public void printMaxHeap() {
        for (int i = 0; i < maxsize; i++) System.out.print(maxheap[i] + " ");
        System.out.println();
    }

    // ---------------- TEST ----------------
    public static void main(String[] args) {
        Heap h = new Heap(10, 10);

        // Min heap test
        h.minheapinsertion(2);
        h.minheapinsertion(3);
        h.minheapinsertion(4);
        h.minheapinsertion(10);
        h.minheapinsertion(15);
        h.minheapinsertion(5);
        System.out.print("Min heap: ");
        h.printMinHeap();
        System.out.println("Deleted min: " + h.minheapdeletion());
        h.printMinHeap();

        // Max heap test
        h.maxheapinsertion(2);
        h.maxheapinsertion(3);
        h.maxheapinsertion(4);
        h.maxheapinsertion(10);
        h.maxheapinsertion(15);
        h.maxheapinsertion(5);
        System.out.print("Max heap: ");
        h.printMaxHeap();
        System.out.println("Deleted max: " + h.maxheapdeletion());
        h.printMaxHeap();
    }
}
