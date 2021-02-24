package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.List;

public class ImageProcessingHelper {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static Mat createKernel(List<List<Double>> data) {
        Mat kernel = new Mat(3, 3, CvType.CV_32F) {
            {
             /* put(0,0,-1);
                put(0,1,0);
                put(0,2,1);

                put(1,0,-1);
                put(1,1,0);
                put(1,2,1);

                put(2,0,-1);
                put(2,1,0);
                put(2,2,1);*/

                for (int r = 0; r < 3 ; r++) {
                    List<Double> row = data.get(r);
                    for (int c = 0; c< 3;c++) {
                       put(r, c, row.get(c).intValue());
                    }

                }

//               for (List<Double> row : data) {
//                    put(row.get(0).intValue(), row.get(1).intValue(), row.get(2).intValue());
//                }
            }
        };


        return kernel;

    }

}
