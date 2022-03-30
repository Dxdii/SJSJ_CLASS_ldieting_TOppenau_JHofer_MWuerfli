package Modules;

import Auswerten.ConnectDatabaseAuswerten;

import java.sql.*;

public class getlengthfrdb {
    static ConnectDatabaseAuswerten a = new ConnectDatabaseAuswerten();
    static Connection db = a.getConnectToDatabase();

    public getlengthfrdb() {

    }

    public int getlenngth() throws SQLException {
        Statement sttmnt = db.createStatement();
        String str = "SELECT count(kennummer)\n" + "FROM frage";
        PreparedStatement p = db.prepareStatement(str);
        ResultSet rs = p.executeQuery();
        System.out.println(rs);
        rs.next();
        int i = rs.getInt("count");
        return i;
    }

    public String getquestions(String z) throws SQLException {
        int i = 1;
        while (i < getlenngth()) {
            Statement sttmnt = db.createStatement();
            String str = "SELECT * " + "FROM frage ";
            PreparedStatement p = db.prepareStatement(str);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String s = rs.getString("text");

                if (s.equals(z)) {
                    String s2 = rs.getString("kennummer");
                    String s3=rs.getString("typ");
                    if(rs.getString("typ").equals("2")){

                                String s4 = rs.getString("min");
                                String s5 = rs.getString("max");

                        return s2+":"+s3+":"+s+":"+s4+":"+s5;
                    }
                    return s2+":"+s3+":"+s;
                }
            }
            i++;

        }

        return z;
    }
}
