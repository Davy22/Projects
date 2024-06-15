import java.lang.reflect.Array;
import java.util.ArrayList;

public class PriorityQueue2 implements PriorityQueueIF<LabelledPoint> {
    private LabelledPoint[] list;
    private int capacity;
    private int size;

    PriorityQueue2(int capacity) {
        this.capacity = capacity;
        list = new LabelledPoint[capacity];
        size = 0;
    }

    public int parent(int index) {
        return (index-1)/ 2;
    }

    public void swap(int index1, int index2) {
        LabelledPoint temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    private void upHeap(int index) {
        if (index == 0)
            return; // Already at the root

        int parentIndex = parent(index);

        if (list[index].getKey() > list[parentIndex].getKey()) {
            swap(index, parentIndex);
            upHeap(parentIndex);
        }
    }

    private void downHeap(int index) {
        int left = (2*index)+ 1;
        int right = (2*index)+ 2;

        int largest;

        if(left < size){
            largest = left;
        }else{return;}

        if (right < size && list[left].getKey() < list[right].getKey()) {
            largest = right;
        }

        if (list[largest].getKey() > list[index].getKey()) {
            swap(index, largest);
            downHeap(largest);
        }
    }

    @Override
    public boolean offer(LabelledPoint e) {
        if (size == capacity) {
            if (list[0].getKey() > e.getKey()) {
                list[0] = e;
                downHeap(0);
            } else {
                return false;
            }
        } else {
            list[size] = e;
            upHeap(size);
            size++;
        }
        return true;
    }

    @Override
    public LabelledPoint poll() {
        if (isEmpty())
            return null;

        LabelledPoint max = list[0];

        list[0] = list[size - 1];
        size--;

        downHeap(0);

        return max;
    }

    @Override
    public LabelledPoint peek() {
        if (isEmpty()){return null;}
            
        return list[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
