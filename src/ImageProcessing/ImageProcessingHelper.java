package ImageProcessing;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.List;

public class ImageProcessingHelper {


    public static Mat createKernel(List<List<Integer>> data) {
        Mat kernel = new Mat(3, 3, CvType.CV_32F) {
            {
                for (List<Integer> row : data) {
                    put(row.get(0), row.get(1), row.get(2));
                }
            }
        };


//        Mat kernel = new Mat(1,1, CvType.CV_32F){
//
//            {
//                //Vertical prewitt
//                /*-1 0 1
//                 * -1 0 1;
//                 * -1 0 1*/
//
//                put(0,0,-1);
//                put(0,1,0);
//                put(0,2,1);
//
//                put(1,0,-1);
//                put(1,1,0);
//                put(1,2,1);
//
//                put(2,0,-1);
//                put(2,1,0);
//                put(2,2,1);
//
//            }
//        };

        return kernel;

    }

}
