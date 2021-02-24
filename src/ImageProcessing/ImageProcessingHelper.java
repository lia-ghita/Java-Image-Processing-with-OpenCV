package ImageProcessing;

import javafx.scene.image.Image;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.io.File;
import java.util.List;

public class ImageProcessingHelper {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static Mat createKernel(List<List<Double>> data) {
        Mat kernel = new Mat(3, 3, CvType.CV_32F) {
            {

                for (int r = 0; r < 3 ; r++) {
                    List<Double> row = data.get(r);
                    for (int c = 0; c< 3;c++) {
                       put(r, c, row.get(c).intValue());
                    }

                }

            }
        };


        return kernel;

    }

    public static Image ApplyEdgeDetection(Image image, Mat kernel) {
System.out.println(image.getUrl());
        Mat source = Imgcodecs.imread("images/lena_color_256.bmp", Imgcodecs.IMREAD_GRAYSCALE);
        Mat destination = new Mat(source.rows(),source.cols(),source.type());
        Imgproc.filter2D(source,destination, -1, kernel);
        Imgcodecs.imwrite("images/prewitt.jpg",destination);
        File file = new File("images/prewitt.jpg");
        Image processedImage = new Image(file.toURI().toString());

return processedImage;

    }


}
