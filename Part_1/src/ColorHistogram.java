import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

/*
This class stores a ColorImage, a histogram, a normalized version of that histogram, the number of bits of the color representation desired...
... and the number of pixels in the histogram (used for normalization)
*/
public class ColorHistogram {

    private ColorImage image = null;
    private int[] hist = null;
    private double[] normalHist = null;
    private int d=3;
    private int numberOfPixels;

    //Constructor that specifies the number bits wanted in the image
    public ColorHistogram (int d){
        this.d=d;
    }

    //Constructor that builds an ColorHistogram out of a text file (file must be in the same directory as this file) (not used)
    public ColorHistogram (String filename){
        try {
            Scanner txt = new Scanner(new FileReader(filename));

            hist= new int[txt.nextInt()];
            numberOfPixels = 0;
            int index=0;

            while (txt.hasNextInt()){
                hist[index] = txt.nextInt();
                numberOfPixels += hist[index];
                index++;
            }

            txt.close();
        }
        catch (Exception e) {
            System.out.println("Error reading file :(");
            e.printStackTrace();
        }

        image = null;
        calcNormalHistogram();
    }

    //This makes fills out the ColorHistogram with the information of an image file (.ppm)
    public void setImage(String image){
        this.image = new ColorImage(image);
        normalHist= null;
        calcNormalHistogram();
    }

    //This returns the normalhist of this ColorHistogram
    public double[] getHistogram(){
        return  normalHist;
    }

    //this returns the intersection between the normalHist of 2 ColorHistograms
    public double compare(ColorHistogram hist){
        double[] otherHist = hist.getHistogram();

        if(normalHist.length != otherHist.length){
            throw new IllegalStateException("Histograms cannot be compared as they are of different resolution");
        }

        double total = 0;
        for(int i = 0; i<normalHist.length; i++){
            total += Math.min(this.normalHist[i], otherHist[i]);
        }
        return total;
    }

    //This saves hist in a text file in the same format as the ones giving in the dataset.
    public void save (String filename){
        try {
            PrintWriter saveFile = new PrintWriter(filename+".txt");
            saveFile.println(hist.length);
            for(int i=0; i<hist.length; i++){
                saveFile.print(hist[i]+" ");
            }
            saveFile.println();
            saveFile.close();
        }
        catch (Exception e) {
            System.out.println(e + "error in saving histogram");
            e.printStackTrace();
        }
    }

    //This is a private method that calculates hist of the image.
    private void calcHistogram(){
        if(image==null){
            return;
        }

        hist = new int[(int) Math.pow(2,3*d)];

        int[] info = image.info();
        image.reduceColor(d);
        int[] tempPixel;

        for(int i=0; i<info[0]; i++){
            for(int j=0; j<info[1]; j++){
                tempPixel=image.getPixel(i,j);
                hist[(int) ((tempPixel[0]*Math.pow(2,d*2))+(tempPixel[1]*Math.pow(2,d))+(tempPixel[2]))]++;
            }
        }
        numberOfPixels = info[2];
    }

    //this makes a normalized version of hist and saves it to normalHist
    private void calcNormalHistogram(){
        if(image != null){
            calcHistogram();
        }

        normalHist= new double[hist.length];

        for(int i=0; i<hist.length; i++){
            normalHist[i] = ((double) hist[i] / numberOfPixels);
        }
    }

}