package Client;

import Server.Mainserver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.glass.ui.Cursor.setVisible;

//public class Controller_Client implements Initializable {
public class Controller_Client{
    public PasswordField pswField;
    public Button startBtn;
    public Label question;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    @FXML
    private AnchorPane anchor;

    //@Override
    //public void initialize(URL location, ResourceBundle resources) {
    public void Start(ActionEvent event) {
        String psw = pswField.getText();
        if(Compare(psw)){
            question.setText("Die Fragen werden derzeit gesendet (Alle paar Sekunden)");
            startBtn.setVisible(false);
            startBtn.setCancelButton(false);
            pswField.setVisible(false);
            //anchor.getChildren().clear();
            //SetCircle(circle1, "aqua");
            //SetCircle(circle2, "aquamarine");
            //SetCircle(circle3, "blue");

            //anchor.getChildren().add(circle1);
            //anchor.getChildren().add(circle2);
            //anchor.getChildren().add(circle3);
            circle1.setVisible(true);
            circle2.setVisible(true);
            circle3.setVisible(true);
            Starten();
        }
        //PSW Kontrolle


    }
    public void Starten() {
        Socket socketServer = null;
        assert false;

        try {
            socketServer = new Socket("localhost", Mainserver.Port);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        DataOutputStream dataOut = null;
        BufferedReader in = null;
        try {

            in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            dataOut = new DataOutputStream(socketServer.getOutputStream());
            //Eingabe-Reader/Ausgabe-Writer erzeugen:


            //Button btn = (Button) fxmlLoader.getNamespace().get("buttonStart");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try{
            String userInput;
            System.out.println(in.readLine());
            //while (in.readLine() != null) {
            //out.println("1234" + "\n");
            //out.flush();
            dataOut.writeBytes("1234" + "\n");
            dataOut.flush();
            System.out.println("Test");
            //System.out.println(in.readLine());
            String read;
            boolean i=false;
            //while ((read = in.readLine()) != "Ende") {
            while (!i) {
                System.out.println("hallo");

                do {
                    read = in.readLine();
                    System.out.println("Checkung");
                }while(read == null);
                System.out.println(read);

                //if(!read.contains(":") && read.length()>1){
                   // read = "1:ende";
                //}
                //SetupDisplay(read);
                // sicherheitscheck

                String[] readSplit = read.split(":");

                switch(readSplit[1]){

                    case "1":
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client_Typ1.fxml"));
                        Parent parent = fxmlLoader.load();
                        Controller_Typ1 dialogController1 = fxmlLoader.<Controller_Typ1>getController();
                        dialogController1.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2] + '?');
                        dialogController1.Select();

                        Scene scene = new Scene(parent, 600, 400);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();

                        System.out.println(read + " & " + dialogController1.Answer());
                        System.out.println("Fragentyp1: " + readSplit[0]);
                        //dataOut.writeBytes(readSplit[0] + ":" + dialogController1.Answer() + "\n");
                        dataOut.writeBytes(dialogController1.Answer() + "\n");
                        dataOut.flush();
                        break;

                    case "2":
                        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Client_Typ2.fxml"));
                        Parent parent2 = fxmlLoader2.load();
                        Controller_Typ2 dialogController2 = fxmlLoader2.<Controller_Typ2>getController();
                        dialogController2.SetMin(Integer.parseInt(readSplit[3]));
                        dialogController2.SetMax(Integer.parseInt(readSplit[4]));
                        dialogController2.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2]);

                        Scene scene2 = new Scene(parent2, 600, 400);
                        Stage stage2 = new Stage();
                        stage2.initModality(Modality.APPLICATION_MODAL);
                        stage2.setScene(scene2);
                        stage2.showAndWait();
                        System.out.println(read + " & " + dialogController2.Slide());
                        //dataOut.writeBytes(readSplit[0] + ":" + dialogController2.Slide() + "\n");
                        dataOut.writeBytes(dialogController2.Slide() + "\n");
                        dataOut.flush();
                        System.out.println("Fragentyp2-" + readSplit[0]);
                        break;

                    case "3":
                        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("Client_Typ3.fxml"));
                        Parent parent3 = fxmlLoader3.load();
                        Controller_Typ3 dialogController3 = fxmlLoader3.<Controller_Typ3>getController();
                        dialogController3.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2] + '?');

                        Scene scene3 = new Scene(parent3, 600, 400);
                        Stage stage3 = new Stage();
                        stage3.initModality(Modality.APPLICATION_MODAL);
                        stage3.setScene(scene3);
                        stage3.showAndWait();
                        System.out.println(read  + " & " + dialogController3.Text());
                        System.out.println("Fragentyp3" + readSplit[0]);
                        //dataOut.writeBytes(readSplit[0] + ":" + dialogController3.Text() + "\n");
                        dataOut.writeBytes(dialogController3.Text() + "\n");
                        dataOut.flush();
                        break;

                    default:
                        i=true;
                        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("Client_Typ4.fxml"));
                        Parent parent4 = fxmlLoader4.load();

                        Scene scene4 = new Scene(parent4, 600, 400);
                        Stage stage4 = new Stage();
                        stage4.initModality(Modality.APPLICATION_MODAL);
                        stage4.setScene(scene4);
                        stage4.showAndWait();
                        //Beenden der Clientanwendung
                        Platform.exit();
                        break;
                }

            }
            System.out.println("Ende");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public boolean Compare(String str){
        try (BufferedReader br = new BufferedReader(new FileReader("pswClient.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(Objects.equals(str, line)){
                    return true;
                }

            }
            System.out.println("Passwort falsch. Sie konnten deshalb nicht verbunden werden.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Beenden der Clientanwendung
        Platform.exit();
        return false;
    }


    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        new Bounce(circle1).setCycleCount(4).setDelay(Duration.valueOf("500ms")).play();
        new Bounce(circle2).setCycleCount(5).setDelay(Duration.valueOf("900ms")).play();
        new Bounce(circle3).setCycleCount(3).setDelay(Duration.valueOf("1200ms")).play();
    }

    public void SetCircle(Circle circle, String color){
       circle.setFill(Paint.valueOf("1f93ff00"));
        circle.setRadius(10.0);
        circle.setStroke(Paint.valueOf(color));
        circle.setStrokeType(StrokeType.valueOf("INSIDE"));
        circle.setStrokeWidth(6.0);

    }*/
}
