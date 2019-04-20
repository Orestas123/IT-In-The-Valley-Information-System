
package controller;

import getway.DrugGetway;
import getway.PatientGetway;
import getway.PrescriptionGetway;
import getway.TemplateGetway;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class HomeContentController implements Initializable {

    @FXML
    private Text lblTotalPatient;
    @FXML
    private Text lblTotalDrug;
    @FXML
    private Text lblTotalPrescription;
    @FXML
    private Text lblTotalTemplate;

    PrescriptionGetway prescriptionGetway = new PrescriptionGetway();
    PatientGetway patientGetway = new PatientGetway();
    DrugGetway drugGetway = new DrugGetway();
    TemplateGetway templateGetway = new TemplateGetway();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            lblTotalPatient.setText(String.valueOf(patientGetway.totalPatient()));
            lblTotalDrug.setText(String.valueOf(drugGetway.totalDrug()));
            lblTotalTemplate.setText(String.valueOf(templateGetway.total()));
            lblTotalPrescription.setText(String.valueOf(prescriptionGetway.totalPrescription()));
        });
    }

    @FXML
    private void handleNewPatient(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/magazine/NewMagazine.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("New Magazine");
        stage.show();
    }

    @FXML
    private void handleNewDrug(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/ads/NewAds.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("New Advert/Media");
        stage.show();
    }

    @FXML
    private void handleNewPrescription(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/magazine/Magazine.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Select Magazine to add advert");
        stage.show();
    }

    @FXML
    private void handleNewPrescriptionTemplate(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/template/NewTemplate.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("New Template");
        stage.show();
    }

}
