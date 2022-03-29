package Modules;

import Modules.JaNein;
import Modules.Numerisch;
import Modules.vonBis;

import Auswerten.ConnectDatabaseAuswerten;
//import com.sun.jdi.IntegerValue;

import java.sql.*;
import java.util.Vector;

public class SaveToDatabase {
    static ConnectDatabaseAuswerten a = new ConnectDatabaseAuswerten();
    static Connection db = a.getConnectToDatabase();

    public SaveToDatabase(Vector<Frage> qu) throws SQLException {
        int j = 0;
        qu.get(j).text.split(":");
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM frage";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        ++i;
        //sttmnt.executeUpdate("INSERT INTO antwort values (" + i + qu + min + max + typ + ")");
        //System.out.println("INSERT INTO antwort values (" + i + qu + min + max + typ + ")");
    }
    public SaveToDatabase(JaNein awnser) throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM antwort";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        ++i;
        sttmnt.executeUpdate("INSERT INTO antwort values (" + awnser.Kennummer + ", " + i + ")");
        System.out.println("INSERT INTO ajanein values (" + i + awnser.value + ")");
        sttmnt.executeUpdate("INSERT INTO ajanein values (" + i + awnser.value + ")");
    }
    public SaveToDatabase(Numerisch awnser){
    }
    public SaveToDatabase(vonBis awnser) {

        }


    public static void main(String[] args) throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM frage";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        ++i;
        JaNein j1 = new JaNein("Frage X", i ,true);
        //SaveToDatabase f = new SaveToDatabase("Frage X", 0, 0, 1);
        SaveToDatabase s = new SaveToDatabase(j1);
    }

}

