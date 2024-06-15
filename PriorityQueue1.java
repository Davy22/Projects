import java.util.ArrayList;
public class PriorityQueue1  implements PriorityQueueIF<LabelledPoint> {
    LabelledPoint[] array;
    int k;
    int size = -1;
    ArrayList<LabelledPoint> arr;
    PriorityQueue1(int k){
        array = new LabelledPoint[k];
        this.k = k;
    }

    PriorityQueue1(ArrayList<LabelledPoint> arr, int k){
        this.arr = new ArrayList<LabelledPoint>(k);
        this.k = k;
    }
    
    @Override
    public boolean offer(LabelledPoint e) {
        if (k == size) {
            if(array[size-1].getKey() > e.getKey()) {
                array[size-1] = null;
                size--;
            }
            else{return false;}
        }
        int index = 0;
        while(index != size){
            if(array[index].getKey() > e.getKey()) {
                for (int i = size; i > index; i--) {
                    array[i] = array[i-1];
                }
                array[index] = e;
                size++;
                return true;
            }
            index++;
        }
        array[index] =e;
        size++;
        return true;
    }
    

    @Override
    public LabelledPoint poll() {
        if (isEmpty()){
            return null;
        }
        LabelledPoint p = array[size-1];
        array[size-1] = null;
        size--;
        return p;
    }

    @Override
    public LabelledPoint peek() {
        if(isEmpty()) return null;
        return array[size];
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
