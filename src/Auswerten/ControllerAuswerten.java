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

    public void listMouseClicked(MouseEvent mouseEvent) {

        int type;
        Statement st = null;
        int countja = 0;
        int countnein = 0;

        index = listFrage.getSelectionModel().getSelectedIndex();

        System.out.println("Zeile ausgewaehlt: " + index);  // ausgewählte Zeile

        type = fragenList.get(index).getType();
        System.out.println("Typ-Frage: " + type);

        switch (type){
            case 1:
                System.out.println("Fragen-Typ => JA/NEIN");
                textMittelwert.clear();
                textStandardabweichung.clear();
                /*
                SELECT value
                FROM ajanein
                JOIN antwort a on a.kennummer = ajanein.akn
                WHERE kennummer = 1;
                 */
                st = null;
                try {
                    st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT value\n" +
                            "FROM ajanein\n" +
                            "JOIN antwort a on a.kennummer = ajanein.akn\n");
                    while (rs.next()) {
                        if (rs.getBoolean("value") == true) {
                            countja++;
                        } else if (rs.getBoolean("value") == false) {
                            countnein++;
                        }
                    }

                    System.out.println("Ja: " + countja);
                    System.out.println("Nein: " + countnein);

                    pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("JA",countja),
                            new PieChart.Data("NEIN",countnein)
                    );

                    rs.close();
                    st.close();

                    // Diagramm ertsellen:
                    chartKreis.getData().clear();
                    chartKreis.getData().addAll(pieChartData);

                    countja = 0;
                    countnein = 0;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.err.println("Fehler beim Auslesen der Antworten JA/NEIN!");
                }

                break;

            case 2:
                System.out.println("Fragen-Typ => Min-Max");

                st = null;
                try {
                    st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT value\n" +
                            "FROM avonbis\n" +
                            "JOIN antwort a on a.kennummer = avonbis.akn\n");
                    while (rs.next()) {
                        System.out.println("Min-Max Wert: " + rs.getInt("value"));
                    }

                    // Mittelwert:
                    ResultSet rsavg = st.executeQuery("SELECT avg(value)::FLOAT AS value\n" +
                            "FROM avonbis\n" +
                            "JOIN antwort a on a.kennummer = avonbis.akn;");

                    while (rsavg.next()) {
                        System.out.println("Mittelwert: " + rsavg.getFloat("value"));
                        textMittelwert.setText(String.valueOf(Math.round(rsavg.getFloat("value")*100.0)/100.0));
                    }

                    // Standardabweichung:
                    ResultSet rsstd = st.executeQuery("SELECT stddev(value)::FLOAT AS value\n" +
                            "FROM avonbis\n" +
                            "JOIN antwort a on a.kennummer = avonbis.akn;");

                    while (rsstd.next()) {
                        System.out.println("Standardabweichung: " + rsstd.getFloat("value"));
                        textStandardabweichung.setText(String.valueOf(Math.round(rsstd.getFloat("value")*100.0)/100.0));
                    }


                    pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("1",1),
                            new PieChart.Data("3",1),
                            new PieChart.Data("1",1)
                    );
                    //pieChartData.set(1,2);

                    rs.close();
                    st.close();

                    // Diagramm ertsellen:
                    chartKreis.getData().clear();
                    chartKreis.getData().addAll(pieChartData);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.err.println("Fehler beim Auslesen der Antworten Min-Max!");
                }


                break;

            case 3:
                System.out.println("Fragen-Typ => Nummer");
                chartKreis.getData().clear();

                st = null;
                try {
                    st = db.createStatement();
                    ResultSet rs = st.executeQuery("SELECT value\n" +
                            "FROM anumerisch\n" +
                            "JOIN antwort a on a.kennummer = anumerisch.akn\n");
                    while (rs.next()) {
                        System.out.println("Test-Num: " + rs.getInt("value"));
                    }





                    // Mittelwert:
                    ResultSet rsavg = st.executeQuery("SELECT avg(value)::FLOAT AS value\n" +
                            "FROM anumerisch\n" +
                            "JOIN antwort a on a.kennummer = anumerisch.akn;");

                    while (rsavg.next()) {
                        System.out.println("Mittelwert: " + rsavg.getFloat("value"));
                        textMittelwert.setText(String.valueOf(Math.round(rsavg.getFloat("value")*100.0)/100.0));
                    }

                    // Standardabweichung:
                    ResultSet rsstd = st.executeQuery("SELECT stddev(value)::FLOAT AS value\n" +
                            "FROM anumerisch\n" +
                            "JOIN antwort a on a.kennummer = anumerisch.akn;");

                    while (rsstd.next()) {
                        System.out.println("Standardabweichung: " + rsstd.getFloat("value"));
                        textStandardabweichung.setText(String.valueOf(Math.round(rsstd.getFloat("value")*100.0)/100.0));
                    }

                    rs.close();
                    st.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.err.println("Fehler beim Auslesen der Antworten Nummer!");
                }
                break;

            default:
                System.out.println("Fragentyp nicht bekannt! Keine Auswertung möglich!");
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

        // Diagramm ertsellen:
        //chartKreis.getData().clear();
        //chartKreis.getData().addAll(pieChartData);

    }

    public void listCancel(ListView.EditEvent editEvent) {
    }

    public void listCommit(ListView.EditEvent editEvent) {
    }

    public void listStart(ListView.EditEvent editEvent) {
    }

}
