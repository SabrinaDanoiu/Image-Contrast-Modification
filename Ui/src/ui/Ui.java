/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static ui.ImageTest.alterImageFile;
import static ui.ImageTest.readImageFile;
import static ui.ImageTest.showDeadTime;
import static ui.SClass.writeImageFile;
/**
 *
 * @author zabi
 */
public class Ui extends Application {
    
    private Component panel;
    private String url_cat_meme = "https://www.facebook.com/catforlove/";
    private Object rate;
    private Boolean auto_update;
    private String contrastModifier;
    
    private File sursa;
    private File destination;
    static BufferedImage readImage = null;
    static BufferedImage alteredImage = null;
    
    String Time1 = "";
    String Time2 = "";
    String Time3 = "";
    String Time4 = "";
    
    @Override
    public void start(Stage primaryStage) {
        
        //CHOOSE IMAGE
        
        Button btn = new Button();
        btn.setText("Choose image");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                long startTime1 = System.currentTimeMillis();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & BMP Images", "jpg", "bmp"); 
                
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                     sursa = fileChooser.getSelectedFile();
                     System.out.println("Selected file: " + sursa.getAbsolutePath());
                     long endTime1 = System.currentTimeMillis();
                    System.out.println("\nCitirea sursei a durat: " + (endTime1 - startTime1) + " millisecunde\n");
                    if((endTime1 - startTime1) > 31000)
                        Time1 = "Citirea sursei si a destinatiei";
                }
            }
        }   
    );
        
        // PROCESS IMAGE
        
        Button btn1 = new Button();
        btn1.setText("Process image");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //etapa 2
                
                long startTime2 = System.currentTimeMillis();
                readImage = readImageFile(sursa);
                long endTime2 = System.currentTimeMillis();
                System.out.println("Citirea imaginii a durat: " + (endTime2 - startTime2) + " millisecunde\n");
                if((endTime2 - startTime2) > 450)
                    Time2 = "Citirea imaginii";
                     
                
               //etapa 3
    	
                long startTime3 = System.currentTimeMillis();
                alteredImage = alterImageFile(readImage);
                long endTime3 = System.currentTimeMillis();
                System.out.println("Procesarea imaginii a durat: " + (endTime3 - startTime3) + " millisecunde\n");
                if((endTime3 - startTime3) > 150)
                        Time3 = "Procesarea imaginii";

            }
        }   
        );
        
        
        // DOWNLOAD IMAGE
        
        Button btn2 = new Button();
        btn2.setText("Download image");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    destination = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + destination.getAbsolutePath());
                    long startTime4 = System.currentTimeMillis();
                    writeImageFile(alteredImage, destination);
                    long endTime4 = System.currentTimeMillis();


                    System.out.println("Scrierea imaginii a durat: " + (endTime4 - startTime4) + " millisecunde\n");
                    if((endTime4 - startTime4) > 100)
                            Time4 = "Scrierea imaginii";
                    showDeadTime(Time1,Time2,Time3,Time4);
                }
                
            }
        }   
        );
        
        // SIMPLE LABEL
        
        Label label = new Label("Rate this app:");
        
        // TOGGLE BUTTONS + COLOR BUTTONS
        
        ToggleButton toggleButton = new ToggleButton("Bad");
        ToggleButton toggleButton1 = new ToggleButton("Medium");
        ToggleButton toggleButton2 = new ToggleButton("Good");
        
        ToggleGroup rating = new ToggleGroup();
        
        toggleButton.setStyle("-fx-base: salmon;");
        toggleButton1.setStyle("-fx-base: lightblue;");
        toggleButton2.setStyle("-fx-base: lightgreen;");

        toggleButton.setToggleGroup(rating);        
        toggleButton1.setToggleGroup(rating);
        toggleButton2.setToggleGroup(rating);
        
        toggleButton.setUserData("Bad");
        toggleButton1.setUserData("Medium");
        toggleButton2.setUserData("Good");

        
        rating.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        @Override
    public void changed(ObservableValue<? extends Toggle> ov,
        Toggle toggle, Toggle new_toggle) {
            if (new_toggle != null)
                rate = rating.getSelectedToggle().getUserData();

         }

        });
        
        // HYPERLINK 
        
        Hyperlink link = new Hyperlink("Like us on Facebook");
        
        link.setOnAction(e -> {
             try {
                Desktop.getDesktop().browse(new URL(url_cat_meme).toURI());
            } catch (URISyntaxException | IOException err) {
                JOptionPane.showMessageDialog(panel, err, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // CHECKBOX
        
         CheckBox c1 = new CheckBox("Auto-update the app");
         c1.setOnAction((event) -> {
            auto_update = c1.isSelected();
            System.out.println("Auto Update is enabled: " + auto_update);
        });
         
         
         // GRAFIC LABEL
         
        Image image = new Image(getClass().getResourceAsStream("bmp.bmp"));
        Label label1 = new Label("bmp");
        label1.setGraphic(new ImageView(image));
        
        // GRAFIC BUTTON + PROGRESS BAR
        
        Button gbtn = new Button();
        gbtn.setGraphic(new ProgressBar(-1));
        
        // CHOICE BOX
        
        ChoiceBox choiceBox = new ChoiceBox();

        choiceBox.getItems().add("High");
        choiceBox.getItems().add("Medium");
        choiceBox.getItems().add("Low");
        
        // TEXT BOX
        
        TextField tF = new TextField();
        Button btnTf = new Button("Click to add choice");

        btnTf.setOnAction(action -> {
            choiceBox.getItems().add(tF.getText());
            // System.out.println(tF.getText());
        });
        
        // BUTTON FOR FXML CONTENT
        
        Button btnExtra = new Button();
        btnExtra.setText("See extra");
        btnExtra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage info_stage = new Stage();
                Parent infos_scene = null;
                try {
                    infos_scene = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
                }

                info_stage.setScene(new Scene(infos_scene, 600, 400));
                info_stage.show();
            }
            
            
        });
        
         // DEFINE LAYOUT
         
        Pane root = new Pane();
        
        btn.setLayoutX(40);
        btn.setLayoutY(20);
        btn1.setLayoutX(235);
        btn1.setLayoutY(20);
        btn2.setLayoutX(430);
        btn2.setLayoutY(20);
        label.setLayoutX(80);
        label.setLayoutY(80);
        toggleButton.setLayoutX(20);
        toggleButton.setLayoutY(110);
        toggleButton1.setLayoutX(80);
        toggleButton1.setLayoutY(110);
        toggleButton2.setLayoutX(170);
        toggleButton2.setLayoutY(110);
        link.setLayoutX(420);
        link.setLayoutY(112);
        c1.setLayoutX(240);
        c1.setLayoutY(115);
        label1.setLayoutX(340);
        label1.setLayoutY(120);
        gbtn.setLayoutX(410);
        gbtn.setLayoutY(150);
        choiceBox.setLayoutX(20);        
        choiceBox.setLayoutY(160);
        tF.setLayoutX(20);        
        tF.setLayoutY(200);
        btnTf.setLayoutX(210);  
        btnTf.setLayoutY(200);
        btnExtra.setLayoutX(150);  
        btnExtra.setLayoutY(240);
        

        

        
        // DISPLAY ELEMENTS
        
        root.getChildren().add(btn);
        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        root.getChildren().add(label);
        root.getChildren().add(toggleButton);
        root.getChildren().add(toggleButton1);
        root.getChildren().add(toggleButton2);
        root.getChildren().add(link);
        root.getChildren().add(c1);
        root.getChildren().add(label1);
        root.getChildren().add(gbtn);
        root.getChildren().add(choiceBox);
        root.getChildren().add(tF);
        root.getChildren().add(btnTf);
        root.getChildren().add(btnExtra);

        
        // DISPLAY SCENE

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Contrast Modification App!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
