package ImageProcessing;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class FaceDetection {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
        public static void main(String[] args) {
            System.out.println("Done");


            String imgFile = "images/people.jpg";
            Mat src = Imgcodecs.imread(imgFile);
            String xmlFile = "xml/lbpcascade_frontalface.xml";
            CascadeClassifier cc= new CascadeClassifier(xmlFile)  ;

            MatOfRect faceDetection = new MatOfRect();
            cc.detectMultiScale (src, faceDetection);
            System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));

            for (Rect rect: faceDetection.toArray()){
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x+ rect.width, rect.y+rect.height), new Scalar(0,0,255), 3);

                Imgcodecs.imwrite("images/peopleout.jpg", src);

                System.out.println("Image detection finished");
            }
        }
    }

















