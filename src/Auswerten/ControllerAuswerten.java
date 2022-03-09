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


        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Num1",7),
                new PieChart.Data("Num2",14),
                new PieChart.Data("Num3",2),
                new PieChart.Data("Num4",5)
            );

        chartKreis.getData().addAll(pieChartData);


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
