package ImageProcessing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.menu.MenuItemBase;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UiController {
    public JFXTextArea fileDropArea;
    public JFXTextField uploadFile;
    public Label dragPopUp;
    public JFXButton uploadFileButton;
    public ImageView image;


    public File UploadGrammar(Stage s){
        String grammar="";
        FileChooser fc = new FileChooser();

        fc.setTitle("Alegeti imaginea");
        File file = fc.showOpenDialog(s);
        if (file!= null){
            return file;
        }

        return null;
    }

}