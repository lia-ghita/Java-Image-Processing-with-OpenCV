package ImageProcessing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;

public class UiController {
    public JFXTextArea fileDropArea;
    public JFXTextField uploadFile;
    public Label dragPopUp;
    public JFXButton uploadFileButton;
    public JFXButton faceDetection;
    public ImageView image;


    public Image FaceDetection (){
        String filePath = uploadFile.getText();
        FaceDetection fd= new FaceDetection(filePath);

        File file = new File("images/peopleout.jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    public File UploadImage(Stage s){

        FileChooser fc = new FileChooser();

        fc.setTitle("Alegeti imaginea");
        File file = fc.showOpenDialog(s);
        if (file!= null){
            return file;
        }

        return null;
    }

}