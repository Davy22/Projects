import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF<LabelledPoint>{
        PriorityQueue<LabelledPoint> pq;
        int k;
    
    PriorityQueue3(int k) {
        this.k = k;
        pq = new PriorityQueue<>(k, new PComparator());
    }
    
    PriorityQueue3(PriorityQueue<LabelledPoint> pq){
        pq = new PriorityQueue<>(k, new PComparator());
    }

    @Override
    public boolean offer(LabelledPoint e) {
        if(pq.size() == k){
             if(pq.peek().getKey() > e.getKey()){
                pq.poll();
            }else{
               return false;
            }
        }
        return pq.offer(e);
    }

    @Override
    public LabelledPoint poll() {
        return pq.poll();
    }

    @Override
    public LabelledPoint peek() {
        
        return pq.peek();
    }

    @Override
    public int size() {
        return pq.size();
    }

    @Override
    public boolean isEmpty() {
        return pq.isEmpty();
    }
    
    public class PComparator implements Comparator<LabelledPoint> {
        
        public int compare(LabelledPoint o1, LabelledPoint o2) {
            if (o1.getKey() > o2.getKey()) {
                return -1;
            } else if (o1.getKey() < o2.getKey()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
