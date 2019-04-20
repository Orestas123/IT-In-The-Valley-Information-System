package view.issue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import model.Patient;
import view.magazine.MagazineCard;

public abstract class MagazineCardIssue extends HBox {
    
    protected final ImageView imageView;
    protected final VBox vBox;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    String path = System.getProperty("user.home");
    
    public MagazineCardIssue(Patient patient) {
        
        imageView = new ImageView();
        vBox = new VBox();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        
        setAlignment(javafx.geometry.Pos.CENTER);
        setSpacing(5.0);
        getStylesheets().add("/view/prescription/../../css/main.css");
        
        imageView.setFitHeight(108.0);
        imageView.setFitWidth(93.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        showImage(patient);
        
        vBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        
        label.setText(patient.getName());
        
        label0.setText("Days : " + age(patient) + "");
        
        String sex = patient.getSex() == 1 ? "Cyber-Security" : patient.getSex() == 2 ? "IT-Tech" : "Other";
        
        label1.setText("Type : " + sex);
        vBox.setPadding(new Insets(0.0, 10.0, 0.0, 5.0));
        
        getChildren().add(imageView);
        vBox.getChildren().add(label);
        vBox.getChildren().add(label0);
        vBox.getChildren().add(label1);
        getChildren().add(vBox);
        
    }
    
    private void showImage(Patient patient) {
        if (patient.getThumbnail() == null) {
            imageView.setImage(new Image(getClass().getResource("/image/magazine.jpg").toExternalForm()));
        } else {
            File file = new File(path + "\\Documents\\ITInTheValley\\" + patient.getThumbnail());
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(MagazineCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String age(Patient patient) {
        String age = "";
        LocalDate birthdate = patient.getDateOrBirth();
        LocalDate now = LocalDate.now();
        age = String.valueOf(ChronoUnit.YEARS.between(birthdate, now));
        return age;
    }
}
