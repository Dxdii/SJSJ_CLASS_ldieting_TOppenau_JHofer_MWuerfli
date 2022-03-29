package Modules;

import Modules.JaNein;
import Modules.Numerisch;
import Modules.vonBis;

import Auswerten.ConnectDatabaseAuswerten;
//import com.sun.jdi.IntegerValue;

import java.io.*;
import java.sql.*;
import java.util.Vector;

public class SaveToDatabase {
    static ConnectDatabaseAuswerten a = new ConnectDatabaseAuswerten();
    static Connection db = a.getConnectToDatabase();

    public SaveToDatabase(Vector<Frage> qu) throws SQLException {
        Statement sttmnt = db.createStatement();

        int i = 0;
        while(i < qu.size()) {
            String st = null;
            String str = qu.get(i).text;
            if(str.equals("")){

            }else {
               st = str.split(":")[2];
                if (str.split(":").length > 3) {

                    System.out.println("INSERT INTO frage values (" + qu.get(i).Kennummer + ", " + "'" + st + "'"  + ", " + str.split(":")[3] + ", " + str.split(":")[4] + ", " + str.split(":")[1] + ")");
                    sttmnt.executeUpdate("INSERT INTO frage values (" + qu.get(i).Kennummer + ", " + "'" + st + "'" + ", " + str.split(":")[3] + ", " + str.split(":")[4] + ", " + str.split(":")[1] + ")");
                    System.out.println("INSERT INTO frage values (" + qu.get(i).Kennummer + ", " + "'" + st + "'" + ", " + str.split(":")[3] + ", " + str.split(":")[4] + ", " + str.split(":")[1] + ")");
                } else {
                    System.out.println("INSERT INTO frage values (" + qu.get(i).Kennummer  + ", " + "'" + st + "'" + ", " +  0  + ", " +  0  + ", " +  str.split(":")[1] + ")");
                    sttmnt.executeUpdate("INSERT INTO frage values (" + qu.get(i).Kennummer  + ", " + "'" + st + "'" + ", " +  0  + ", " +  0  + ", " +  str.split(":")[1] + ")");

                }
            }

            i++;
        }
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
        sttmnt.executeUpdate("INSERT INTO antwort values (" + i + ", " + awnser.Kennummer + ")");
        System.out.println("INSERT INTO ajanein values (" + i + ", " + awnser.value + ")");
        sttmnt.executeUpdate("INSERT INTO ajanein values (" + i + ", " + awnser.value + ")");
    }
    public SaveToDatabase(Numerisch awnser) throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM antwort";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        ++i;
        sttmnt.executeUpdate("INSERT INTO antwort values (" + i + ", " + awnser.Kennummer + ")");
        System.out.println("INSERT INTO ajanein values (" + i + ", " + awnser.value + ")");
        sttmnt.executeUpdate("INSERT INTO anumerisch values (" + i + ", " + awnser.value + ")");
    }
    public SaveToDatabase(vonBis awnser) throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM antwort";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        ++i;
        sttmnt.executeUpdate("INSERT INTO antwort values (" + i + ", " + awnser.Kennummer + ")");
        System.out.println("INSERT INTO ajanein values (" + i + ", " + awnser.value + ")");
        sttmnt.executeUpdate("INSERT INTO avonbis values (" + i + ", " + awnser.value + ")");

    }


    public static void main(String[] args) throws SQLException {
        JaNein j1 = new JaNein("Frage X", 1 ,true);
        //SaveToDatabase s = new SaveToDatabase(j1);
        int i = 0;
        // Auslesen der Fragen in die Fragen Klasse
        FileReader fr = null;
        try {
            fr = new FileReader(new File("Fragen.csv"));
            BufferedReader br = new BufferedReader(fr);
            Vector<Frage> Questions = new Vector<>();
            String l = br.readLine();
            while (l != null) {
                Questions.add(new Frage(l, i));
                l = br.readLine();
                i++;
            }
            br.close();
            SaveToDatabase s2 = new SaveToDatabase(Questions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

