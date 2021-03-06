package ImageProcessing;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;



public class FaceDetection {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
        public FaceDetection(String imgAddress) {
            System.out.println("Done");


            String imgFile = imgAddress;
            Mat src = Imgcodecs.imread(imgFile);
            String xmlFile = "xml/lbpcascade_frontalface.xml";
            CascadeClassifier cc= new CascadeClassifier(xmlFile)  ;

            MatOfRect faceDetection = new MatOfRect();
            cc.detectMultiScale (src, faceDetection);


            for (Rect rect: faceDetection.toArray()){
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x+ rect.width, rect.y+rect.height), new Scalar(0,0,255), 3);

                Imgcodecs.imwrite("images/peopleout.jpg", src);

                System.out.println("Image detection finished");
            }
        }
    }
















