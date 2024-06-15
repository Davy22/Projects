import java.io.FileReader;
import java.util.Scanner;
import java.lang.Math;

//ColorImage stores the dimensions, depth and name of the image, along with a 3-dimensional integer array representation of the image.
public class ColorImage {
    private int width;
    private int height;
    private int depth;
    private int[][][] pixels;

    //This constructor uses a file name (without its extension), reads the .ppm file and builds the 3d int array
    public ColorImage(String filename){
        try {
            Scanner ppm = new Scanner(new FileReader(filename+".ppm"));
            ppm.nextLine();
            ppm.nextLine();

            width = ppm.nextInt();
            height = ppm.nextInt();
            depth = ppm.nextInt();
            pixels = new int[width][height][3];

            for(int j=0; j<height; j++){
                for(int i=0; i<width; i++){
                    pixels[i][j]= new int[] {ppm.nextInt(), ppm.nextInt(), ppm.nextInt()};
                }
            }

            ppm.close();
        }
        catch (Exception e) {
            System.out.println("Error reading image file, make sure the query string does not already have a file extension (uses q00 instead of q00.jpg or q00.ppm)." +
                    "\nOr check that query file is in the correct directory (it should be placed along side the java files directly and not in a subfolder)");
            e.printStackTrace();
        }
    }

    //returns the 3-channel value of pixel at column i row j in the form of a 3-element array
    public int[] getPixel(int i, int j){
        return pixels[i][j];
    }

    //reduces the pixel array colors to a d-bit representation
    public void reduceColor(int d){
        int div= (int)((depth+1)/(Math.pow(2, d)));
        for(int j=0; j<height; j++){
            for(int i=0; i<width; i++){
                for(int k=0; k<3; k++){
                    pixels[i][j][k] = pixels[i][j][k]/div;
                }
            }
        }
    }

    //returns the dimension of the image and the number of pixels in the image
    public int[] info(){
        return new int[] {width, height, width*height};
    }
}