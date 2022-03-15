package Auswerten;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
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

    public PieChart chartKreis;

    int index;
    Connection db = null;

    List<FragenAuswerten> fragenList = new ArrayList<>();

    ObservableList<PieChart.Data> pieChartData;

    public void initialize() {
        // Verbindung zu Datenbank herstellen beim Aufrufen der GUI!
        ConnectDatabaseAuswerten database = new ConnectDatabaseAuswerten();
        db = database.getConnectToDatabase();

    }

    public void btnUpdateClicked(ActionEvent actionEvent) {

        System.out.println("Button Update!");

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM frage");

            //List<FragenAuswerten> fragenList = new ArrayList<>();
            //ObservableList<FragenAuswerten> fragenList = new ObservableList<FragenAuswerten>();

            fragenList.clear();   // ArrayList löschen

            btnUpdate.setStyle("-fx-background-color: rgb(0,225,0);");

            while (rs.next()) {
                FragenAuswerten frage = new FragenAuswerten();

                frage.setKennnummer(rs.getInt("kennummer"));
                frage.setFrage(rs.getString("text"));
                frage.setMin(rs.getInt("min"));
                frage.setMax(rs.getInt("max"));
                frage.setType(rs.getInt("typ"));

                fragenList.add(frage);
            }

            rs.close();
            st.close();

            System.out.println(fragenList.get(1).getFrage());

            listFrage.getItems().clear();   // Einträge in der Liste löschen!

            fragenList.forEach(frage -> {
                listFrage.getItems().add(frage.getFrage());
            });

        } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.err.println("Fehler beim Einlesen der Daten aus der Datenbank!");
                btnUpdate.setText("Fehler DB!!!");
                btnUpdate.setStyle("-fx-background-color: rgb(225,0,0);");
        }
    }

    public void btnAuswertenClicked(ActionEvent actionEvent) {

        System.out.println("Button Auswerten!");

/*
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Num1",7),
                new PieChart.Data("Num2",14),
                new PieChart.Data("Num3",2),
                new PieChart.Data("Num4",5)
            );


        chartKreis.getData().clear();

        chartKreis.getData().addAll(pieChartData);
 */

    }

    public void listMouseClicked(MouseEvent mouseEvent) {

        int type;

        int countja = 0;
        int countnein = 0;

        index = listFrage.getSelectionModel().getSelectedIndex();

        System.out.println(index);  // ausgewählte Zeile

        type = fragenList.get(index).getType();
        System.out.println("Typ-Frage: " + type);


        switch (type){
            case 1:
                System.out.println("Fragen-Typ => JA/NEIN");
                /*
                SELECT value
                FROM ajanein
                JOIN antwort a on a.kennummer = ajanein.akn
                WHERE kennummer = 1;
                 */

                Statement st = null;
                try {
                    st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT value\n" +
                            "                FROM ajanein\n" +
                            "                JOIN antwort a on a.kennummer = ajanein.akn\n" +
                            "                WHERE kennummer = 1");

                    while (rs.next()) {
                        if (rs.getBoolean("value") == true) {
                            countja++;
                        } else if (rs.getBoolean("value") == false) {
                            countnein++;
                        }

                    }

                    rs.close();
                    st.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.err.println("Fehler beim Auslesen der Antworten JA/NEIN!");
                }



                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("JA",7),
                        new PieChart.Data("NEIN",14)
                );



                break;

            case 2:
                System.out.println("Fragen-Typ => Min-Max");

                break;

            case 3:
                System.out.println("Fragen-Typ => Nummer");

                break;

            default:
                System.err.println("Fehler Fragen-Typ nicht bekannt!");
                break;
        }
/*
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Num1",7),
                new PieChart.Data("Num2",14),
                new PieChart.Data("Num3",2),
                new PieChart.Data("Num4",5)
        );


 */


        chartKreis.getData().clear();

        chartKreis.getData().addAll(pieChartData);

    }

    public void listCancel(ListView.EditEvent editEvent) {
    }

    public void listCommit(ListView.EditEvent editEvent) {
    }

    public void listStart(ListView.EditEvent editEvent) {
    }


}
