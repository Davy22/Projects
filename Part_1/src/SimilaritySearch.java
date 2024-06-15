/*
For looking through a directory i looked (as a base) at ;https://intellipaat.com/community/38545/how-do-i-iterate-through-the-files-in-a-directory-in-java#:~:text=1%20Answer&text=You%20can%20use%20File%23isDirectory,This%20is%20called%20recursion.
 */
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

//This class is in charge of the going taking in the query .ppm file and the histogram dataset and find the 5 most similar images.
public class SimilaritySearch {

    //Takes in 2 args strings and passes them off to the method that implements the algorithm
    public static void main(String[] args) {
        printNamesOfKMostSimilarImages(args,5);
    }

    /*
    This method prints the name of the k most similar images by first building a colorhistorgeam from the query image (.ppm) collecting all...
    ... the .txt file names from the dataset. Then one by one passes the files to colorhistogram, uses the .compare() method...
    ... and saving the result in a priority queue along with the name of the file as a pair (pair is a simple subclass).
    When all the .txt files are progressed and added to the queue the k most similar images are removed and printed out.
     */
    public static void printNamesOfKMostSimilarImages(String[] args, int k){
        ColorHistogram query = new ColorHistogram(3);
        query.setImage(args[0]);
        ColorHistogram dataHisto;
        PriorityQueue<Pair> queue = new PriorityQueue<>();

        String dataSetPath = System.getProperty("user.dir")+"/"+args[1];

        File[] files = new File(dataSetPath).listFiles();
        for (File histoFile : files) {
            String fileName = histoFile.getName();
            if (fileName.endsWith(".txt")) {
                dataHisto = new ColorHistogram(dataSetPath+"/"+histoFile.getName());
                queue.add(new Pair(histoFile.getName(), query.compare(dataHisto)));
            }
        }

        for(int i=0; i<k && !queue.isEmpty(); i++){
            Pair temp = queue.poll();
            System.out.println(temp.getName().substring(0,temp.getName().length()-4));
        }
    }

    //This is a simple subclass used to store a name and a similarity value. Its purpose is to be uses in a priority queue.
    public static class Pair implements Comparable<Pair> {
        private String name;
        private Double similarity;
        public Pair(String name, double similarity) {
            this.name = name;
            this.similarity = similarity;
        }
        public String getName() {
            return name;
        }
        public Double getSimilarity() {
            return similarity;
        }

        @Override
        public int compareTo(Pair other) {
            return other.similarity.compareTo(this.similarity);
        }
    }
}