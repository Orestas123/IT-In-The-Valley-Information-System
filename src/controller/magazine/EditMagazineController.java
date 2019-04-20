
package controller.magazine;

import getway.PatientGetway;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Patient;
import view.magazine.MagazineCard;

/**
 * FXML Controller class
 */
public class EditMagazineController implements Initializable {

    @FXML
    private ImageView imageViewPatientImage;
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpDateOfBirth;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFeMale;
    @FXML
    private RadioButton radioOther;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextArea taAddress;

    File file = null;
    File copyFile = null;
    FileChooser chooser = new FileChooser();
    FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    String path = System.getProperty("user.home");
    Random rand = new Random();
    int n = rand.nextInt(999999999) + 1;

    public int patientId = 0;

    PatientGetway patientGetway = new PatientGetway();
    Patient patient = new Patient();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioMale.setToggleGroup(toggleGroup);
        radioFeMale.setToggleGroup(toggleGroup);
        radioOther.setToggleGroup(toggleGroup);
    }

    @FXML
    private void selectPatientImageOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Choose cover (Image only)");
        chooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        file = chooser.showOpenDialog(stage);
        String imagePath = file.getPath();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageViewPatientImage.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(NewMagazineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelOnAction(ActionEvent event) {
        
    }

    @FXML
    private void patientSaveOnAction(ActionEvent event) {
        LocalDate date = dpDateOfBirth.getValue();
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int sex = radioMale.isSelected() ? 1 : radioFeMale.isSelected() ? 2 : 0;
        if (patientGetway.update(new Patient(patient.getId(), copyImage(), tfName.getText(), date, sex, tfEmail.getText(), tfPhone.getText(), taAddress.getText(), dateFormat.format(today)))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Magazine updated successfully");
            alert.setHeaderText("Magazine updated");
            alert.setContentText("Magazine updated successfullly");
            alert.showAndWait();
        }
    }

    public void getPatientDetails(int id) {
        patient = patientGetway.selectedPatient(id);
        patientId = patient.getId();
        tfEmail.setText(patient.getEmail());
        tfName.setText(patient.getName());
        tfPhone.setText(patient.getPhone());
        taAddress.setText(patient.getAddress());
        switch (patient.getSex()) {
            case 1:
                radioMale.setSelected(true);
                break;
            case 2:
                radioFeMale.setSelected(true);
                break;
            default:
                radioOther.setSelected(true);
                break;
        }
        dpDateOfBirth.setValue(patient.getDateOrBirth());
        showImage(patient);

    }

    private void showImage(Patient patient) {
        if (patient.getThumbnail() == null) {
            imageViewPatientImage.setImage(new Image(getClass().getResource("/image/magazine.jpg").toExternalForm()));
        } else {
            File nFile = new File(path + "\\Documents\\ItInTheValley\\" + patient.getThumbnail());
            try {
                BufferedImage bufferedImage = ImageIO.read(nFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageViewPatientImage.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(MagazineCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String copyImage() {
        if (file != null) {
            copyFile = new File(path + "\\Documents\\ItInTheValley\\" + n + file.getName());
        } else {
            return patient.getThumbnail();
        }
        System.out.println(copyFile.getPath());
        try {
            Files.copy(Paths.get(file.getPath()), Paths.get(copyFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(NewMagazineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return copyFile.getName();

    }

}
