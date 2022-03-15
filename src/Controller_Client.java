import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Controller_Client {
    @FXML
    private GridPane gridPane;
    private TextArea textArea;

    public void Start(ActionEvent actionevent) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socketServer = null;
        assert false;


        try {
            socketServer = new Socket("localhost", 55555);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            DataOutputStream dataOut = new DataOutputStream(socketServer.getOutputStream());
            //Eingabe-Reader/Ausgabe-Writer erzeugen:

            //Solange der User etwas eingibt (und danach Enter drückt), werden die Daten
            //zum Server geschickt. Eingabe von "X" beendet alles.
            String userInput;
            System.out.println(in.readLine());
            //while (in.readLine() != null) {
            //out.println("1234" + "\n");
            //out.flush();
            dataOut.writeBytes("1234" + "\n");
            dataOut.flush();
            System.out.println("Test");
            String read;
            while ((read = in.readLine()) != null) {
                System.out.println("hallo");
                //SetupDisplay(read);
                String[] readSplit = read.split(":");
                if (Objects.equals(readSplit[1], "1")) {
                    System.out.println(read);
                    System.out.println("Fragentyp1" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0] + ":" + "ja" + "\n");
                    dataOut.flush();
                } else if (Objects.equals(readSplit[1], "2")) {
                    System.out.println(read);
                    System.out.println("Fragentyp2-" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0] + ":" + "1" + "\n");
                    dataOut.flush();
                } else if (Objects.equals(readSplit[1], "3")) {
                    System.out.println(read);
                    System.out.println("Fragentyp3" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0] + ":" + "1.43" + "\n");
                    dataOut.flush();
                }


            }
            dataOut.writeBytes("1" + "\n");
            dataOut.flush();
            System.out.println(in.readLine());

            //}


            /*System.out.println("Test2");
            while ( (textInput = reader.readLine() ) != null && !"e".equals(textInput))
            {

                System.out.println(in.readLine());


            }*/
            //User hat "e" eingegeben: Socket dichtmachen.
            //socketServer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            System.exit(-1);
        }
    }

    public void AddChoice() {

    }

    public void AddSlider(int min, int max) {

    }

    public void AddInputfield() {

    }

    public void NextQuestion(ActionEvent actionEvent) {
    }

    public void SetupDisplay(String read) {


        String[] readSplit = read.split(":");
        if (Objects.equals(readSplit[1], "1")) {
            System.out.println(read);
            System.out.println("Fragentyp1" + readSplit[0]);
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().add("ja");
            choiceBox.getItems().add("nein");
            gridPane.add(choiceBox, 0, 0, 1, 1);


        } else if (Objects.equals(readSplit[1], "2")) {
            System.out.println(read);
            System.out.println("Fragentyp2-" + readSplit[0]);
            //Slider slider = new Slider();
            //            slider.setMin(0);
            //            slider.setMax(100);
            //            slider.setValue(40);
            //            slider.setShowTickMarks(true);
            //            slider.setShowTickLabels(true);
            //            slider.setMinorTickCount(5);
            //            slider.setMajorTickUnit(50);
            //            slider.setBlockIncrement(10);
            //            slider.setPrefSize(500, 5);
            //gridPane.add(slider, 0, 0, 1, 1);

        } else if (Objects.equals(readSplit[1], "3")) {
            System.out.println(read);
            System.out.println("Fragentyp3" + readSplit[0]);
            this.textArea = new TextArea("1.45");
            this.textArea.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane.setFillWidth(textArea, true);
        }

        //Prüfen ob gültige Eingabe getätigt
        //Wenn Fragentyp anders => Ändern der Auswahlmöglichkeit
        //Ändern der Fragenummer

    }
}
