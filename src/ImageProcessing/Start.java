package ImageProcessing;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ImageProcessing.ImageProcessingHelper.ApplyEdgeDetection;


public class Start extends Application {

    private Stage primaryStage;
    private FXMLLoader firstLoader;
    private FXMLLoader secondLoader;
    private UiController uiController;
    private EditorController editorController;
    private Scene firstScene;
    private Scene secondScene;

    private List<Map> algorithms;


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


    private void applyAlgorithm(int i) {
        Map entry = algorithms.get(i);
        List<List<Double>> data = (List<List<Double>>) entry.get("data");

        Mat kernel = ImageProcessingHelper.createKernel(data);
        Image processedImage = ApplyEdgeDetection(editorController.sourceImage.getImage(),kernel);

        editorController.processedImage.setImage(processedImage);

    }

    private void iniEvents(){


        uiController.uploadFileButton.setOnAction(event->{
            File f =  uiController.UploadImage(primaryStage);
            if (f!=null){
                loadFile(f);
            }
        });


        uiController.edgeDetection.setOnAction(event ->{
            primaryStage.setScene(secondScene);
            Image f= uiController.image.getImage();

            editorController.sourceImage.setImage(f);
        });



        editorController.backButton.setOnAction(event ->{
            primaryStage.setScene(firstScene);
            editorController.sourceImage.setImage(null);
            editorController.processedImage.setImage(null);
        });

        try {
            Gson gson = new Gson();

            algorithms = gson.fromJson(new FileReader(new File("./Algorithms.json")), List.class);


            for (Map entry :  algorithms) {
                editorController.methodCombo.getItems().add(entry.get("name"));
            }


            editorController.methodCombo.setOnAction(actionEvent -> {

                System.out.println("   >>> " + editorController.methodCombo.getSelectionModel().getSelectedIndex());

            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        editorController.applyBtn.setOnAction(actionEvent -> {
            applyAlgorithm(editorController.methodCombo.getSelectionModel().getSelectedIndex());
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
        this.primaryStage.setTitle("Procesarea imaginilor");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}