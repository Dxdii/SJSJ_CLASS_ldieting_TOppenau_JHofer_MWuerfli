package Auswerten;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabaseAuswerten {
    public Connection getConnectToDatabase() {

        Connection db = null;

        try {
            System.out.println("Try to read something from DB");

            // Treiber laden
            Class.forName("org.postgresql.Driver");

            // Aufbau der Verbindung zur DB
            db = DriverManager.getConnection("jdbc:postgresql://localhost/jhofer",
                    "reader", "reader");

                    /* SSH-Tunnel:
                    jdbc:postgresql://localhost/toppenau
                    ssh -p 22 toppenau@htl-steyr.ac.at -L 5432:xserv:5432
                     */

            System.out.println("Verbindung erfolgreich!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("SQL-Datenbank Driver nicht gefunden!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Verbindung mit DB fehlgeschlagen!");
        } finally {
            return db;
        }

    }
}
