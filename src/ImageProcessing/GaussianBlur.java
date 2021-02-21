package ImageProcessing;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class GaussianBlur {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public GaussianBlur(String imgAddress) {
        System.out.println("Done");


        String imgFile = imgAddress;
        Mat src = Imgcodecs.imread(imgFile);
        Mat destination = new Mat(src.rows(), src.cols(), src.type());
        Imgproc.GaussianBlur(src, destination, new Size(5,5), 0);
        Imgcodecs.imwrite("images/gaussianBlurLatest.jpg", destination);
        }
    }

