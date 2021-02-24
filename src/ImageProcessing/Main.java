package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        int kernelCols = 3;
        int kernelRows = 3;

        Mat source = Imgcodecs.imread("images/people.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        Mat destination = new Mat(source.rows(),source.cols(),source.type());

        Mat kernel = new Mat(kernelRows,kernelCols, CvType.CV_32F){

            {
                //Vertical prewitt
                /*-1 0 1
                 * -1 0 1
                 * -1 0 1*/

                put(0,0,-1);
                put(0,1,0);
                put(0,2,1);

                put(1,0,-1);
                put(1,1,0);
                put(1,2,1);

                put(2,0,-1);
                put(2,1,0);
                put(2,2,1);

            }
        };

        Imgproc.filter2D(source,destination, -1, kernel);
        Imgcodecs.imwrite("images/prewitt.jpg",destination);


    }
}