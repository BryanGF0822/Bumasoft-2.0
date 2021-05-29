package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ResidentialUnit;
import thread.ProgressBarThread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@SuppressWarnings("restriction")
public class  Main extends Application{
    private ResidentUnitGUI ru;
    private ResidentialUnit residentialUnit;
    private boolean start;
    ProgressBarThread progressBarThread;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public Alert getAlert() {
        return alert;
    }

    @FXML
    private AnchorPane anchorToTheBar;


    private ProgressBar progressBars;

    @FXML
    private void initialize(){
        progressBars = new ProgressBar();
        progressBars.setPrefWidth(200);
        anchorToTheBar.getChildren().add(progressBars);


    }

    public Main() throws FileNotFoundException {
        residentialUnit = new ResidentialUnit("","","","",0,0,0,0,0);
        ru = new ResidentUnitGUI(residentialUnit);
    }

    public boolean isStart() {
        return alert.isShowing();
    }

    public ProgressBar getProgressBars() {
        return progressBars;
    }

    public void aumentarBarra(){
        progressBars.setProgress(progressBars.getProgress()+0.1);
        System.out.println("pro");
    }
    static Main m;

    static {
        try {
            m = new Main();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        alert.setTitle("Information Dialog");
        alert.setHeaderText("Informacion");
        alert.setContentText("El registro se agrego con exito");

        Thread thread = new Thread(( )->{
            try {
                Thread.sleep(3000);
                if (alert.isShowing()){
                    Platform.runLater(()->alert.close());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        ProgressBarThread progressBarThread = new ProgressBarThread(this);
        progressBarThread.start();
        alert.getDialogPane().getChildren().clear();

        FXMLLoader prueba = new FXMLLoader(getClass().getResource("fxml/welcome.fxml"));
        prueba.setController(this);
        Parent test = prueba.load();
        alert.getDialogPane().setPrefHeight(300);
        alert.getDialogPane().getChildren().add(test);
        alert.showAndWait();



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/residentialUnitInformation.fxml"));

        fxmlLoader.setController(ru);

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Informacion unidad residencial");
        progressBarThread.stop();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmar.");
        alert.setHeaderText("Cargar.");
        alert.setContentText("¿Desea cargar los datos de la sesion anterior?");

        ButtonType buttonTypeOne = new ButtonType("Si");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {

            try {
                residentialUnit.loadDataApartments();

            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setTitle("Error.");
                t.setHeaderText("Error");
                t.setContentText("No se encontro archivo");
                t.showAndWait();
            }
        }

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent arg0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Confirmar.");
                alert.setHeaderText("Guardar.");
                alert.setContentText("¿Desea guardar los datos antes de salir?");

                ButtonType buttonTypeOne = new ButtonType("Si");
                ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {

                    try {
                        residentialUnit.saveDataApartments();
                        residentialUnit.export();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }

        });


        primaryStage.show();

    }

}