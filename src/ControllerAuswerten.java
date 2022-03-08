import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerAuswerten {

    public ListView listFrage;

    public Button btnUpdate;
    public Button btnAuswerten;

    public TextField textHaeufigkeit;
    public TextField textMittelwert;
    public TextField textStandardabweichung;

    int index;
    Connection db = null;

    List<FragenAuswerten> fragenList = new ArrayList<>();

    public void initialize() {

        ConnectDatabaseAuswerten database = new ConnectDatabaseAuswerten();
        db = database.getConnectToDatabase();

    }

    public void btnUpdateClicked(ActionEvent actionEvent) {

        System.out.println("Button Update!");

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM schueler");

            //List<Schueler> schuelerList = new ArrayList<>();
            //ObservableList<Schueler> schuelerList = new ObservableList<Schueler>();

            fragenList.clear();   // ArrayList löschen

            while (rs.next()) {
                FragenAuswerten frage = new FragenAuswerten();

                frage.setIndex(rs.getInt("Index"));
                frage.setFrage(rs.getString("Frage"));




                fragenList.add(frage);
            }

            rs.close();
            st.close();

            //System.out.println(schuelerList.get(1).getVorname());

            listFrage.getItems().clear();

            fragenList.forEach(frage -> {
                listFrage.getItems().add(frage.getFrage());
            });

        } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.err.println("Fehler beim Einlesen der Daten aus der Datenbank!");
                btnUpdate.setText("Fehler DB!!!");
        }
    }

    public void btnAuswertenClicked(ActionEvent actionEvent) {

        System.out.println("Button Auswerten!");
    }

    public void listMouseClicked(MouseEvent mouseEvent) {

        index = listFrage.getSelectionModel().getSelectedIndex();

        System.out.println(index);  // ausgewählte Zeile


    }

    public void listCancel(ListView.EditEvent editEvent) {
    }

    public void listCommit(ListView.EditEvent editEvent) {
    }

    public void listStart(ListView.EditEvent editEvent) {
    }


}
