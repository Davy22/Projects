import java.util.ArrayList;
import java.util.Scanner;

public class KNN {
    
    private PointSet points;
    private int k;
    
    public KNN(PointSet points, int k) {
        this.points = points;
        this.k = k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public ArrayList<LabelledPoint> findKNN(LabelledPoint query, int choice) {
        switch (choice) {
            case 1:
                // code block for choice 1
                PriorityQueue1 q = new PriorityQueue1(k);
                for (LabelledPoint p : points.getPointsList()) {
                    p.setKey(p.distanceTo(query));
                    q.offer(p);
                    if (q.size() > k) {
                        q.poll();
                    }
                }
                ArrayList<LabelledPoint> neighbors1 = new ArrayList<>();
                while (!q.isEmpty()) {
                    neighbors1.add(q.poll());
                }
                return neighbors1;

            case 2:
                // code block for choice 2
                PriorityQueue2 a = new PriorityQueue2(k);
                for (LabelledPoint p : points.getPointsList()) {
                    p.setKey(p.distanceTo(query));
                    a.offer(p);
                    if (a.size() > k) {
                        a.poll();
                    }
                }
                ArrayList<LabelledPoint> neighbors2 = new ArrayList<>();
                while (!a.isEmpty()) {
                    neighbors2.add(a.poll());
                }
                return neighbors2;

            case 3:
                // code block for choice 3
                PriorityQueue3 b = new PriorityQueue3(k);
                for (LabelledPoint p : points.getPointsList()) {
                    p.setKey(p.distanceTo(query));
                    b.offer(p);
                    if (b.size() > k) {
                        b.poll();
                    }
                }
                ArrayList<LabelledPoint> neighbors3 = new ArrayList<>();
                while (!b.isEmpty()) {
                    neighbors3.add(b.poll());
                }
                return neighbors3;

            default:
                // code block for default case
                return new ArrayList<>(); // Return an empty list for the default case.
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int choice = scanner.nextInt();

        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT("siftsmall_query.fvecs"));
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT("siftsmall_base.fvecs"));
        Long startTime = System.currentTimeMillis();

        KNN PQ = new KNN(pointSet, k);
        for (LabelledPoint i : queryPts.getPointsList()) {
            LabelledPoint p = i;
            ArrayList<LabelledPoint> close = PQ.findKNN(p,choice); // Provide a choice parameter (1 in this case).
            System.out.print(i.getLabel() + ": ");
            for (LabelledPoint point : close) {
                System.out.print(point.getLabel() + ", ");
            }
            System.out.println();
        }

        Long endTime = System.currentTimeMillis(); // End timer after your operations.
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
