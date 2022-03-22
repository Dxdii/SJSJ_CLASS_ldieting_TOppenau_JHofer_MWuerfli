package Modules;

import Modules.JaNein;
import Modules.Numerisch;
import Modules.vonBis;

import Auswerten.ConnectDatabaseAuswerten;
import com.sun.jdi.IntegerValue;

import java.sql.*;

public class SaveToDatabase {
    ConnectDatabaseAuswerten a = new ConnectDatabaseAuswerten();
    Connection db = a.getConnectToDatabase();

    public SaveToDatabase(JaNein awnser) throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM antwort";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        int i = rs.getInt("kennummer");
        ++i;
        sttmnt.executeUpdate("INSERT INTO antwort values (" + awnser.Kennummer + i + ")");
        sttmnt.executeUpdate("INSERT INTO ajanein values (" + i + awnser.value + ")");
    }
    public SaveToDatabase(Numerisch awnser){
    }
    public SaveToDatabase(vonBis awnser) {

        }
    public static void main(String[] args) throws SQLException {
        JaNein j1 = new JaNein("Frage X", true);
        SaveToDatabase s = new SaveToDatabase(j1);
    }

}

