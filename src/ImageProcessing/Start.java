package ImageProcessing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Start extends Application {

    private Stage primaryStage;
    private FXMLLoader firstLoader;
    private FXMLLoader secondLoader;
    private UiController uiController;
    private EditorController editorController;
    private Scene firstScene;
    private Scene secondScene;

    public Start(){
        try {
            firstLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
            secondLoader = new FXMLLoader(getClass().getResource("Editor.fxml"));
            firstScene = new Scene(firstLoader.load());
            secondScene = new Scene(secondLoader.load());
            uiController = firstLoader.getController();
            editorController = secondLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        iniEvents();
    }

    private void iniEvents(){


        uiController.uploadFileButton.setOnAction(event->{
            File f =  uiController.UploadImage(primaryStage);
            if (f!=null){
                loadFile(f);
            }
        });



        editorController.backButton.setOnAction(event ->{
            primaryStage.setScene(firstScene);
            editorController.TheLegendText.clear();
        });

        uiController.fileDropArea.setOnDragOver(dragEvent -> {
            if(dragEvent.getDragboard().hasFiles()){
                uiController.dragPopUp.toFront();
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });

        uiController.fileDropArea.setOnDragDropped(event->{
            boolean success = false;
            Dragboard db = event.getDragboard();
            if(db.hasFiles()){
                File file = db.getFiles().get(0);
                loadFile(file);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
            uiController.dragPopUp.toBack();
        });

        uiController.faceDetection.setOnAction(event->{
         Image f=  uiController.FaceDetection();
          uiController.image.setImage(f);
        });

        uiController.gaussianBlur.setOnAction(event->{
            Image f=  uiController.GaussianBlur();
            uiController.image.setImage(f);
        });


        firstScene.setOnDragEntered(dragEvent -> {
            if(dragEvent.getDragboard().hasFiles()){
                uiController.fileDropArea.setEffect(new BoxBlur());
                uiController.dragPopUp.toFront();
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        firstScene.setOnDragExited(dragEvent ->{
            if(dragEvent.getDragboard().hasFiles()){
                uiController.fileDropArea.setEffect(null);
                uiController.dragPopUp.toBack();
                uiController.fileDropArea.toBack();
                uiController.image.toFront();
            }
        } );
    }

    public void loadFile(File f){
        uiController.uploadFile.setText(f.getPath());
        File file = f;
        Image image = new Image(file.toURI().toString());
        uiController.image.setImage(image);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LFC");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}