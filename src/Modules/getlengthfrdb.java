package Modules;

import Auswerten.ConnectDatabaseAuswerten;
import java.sql.*;

public class getlengthfrdb {
    static ConnectDatabaseAuswerten a = new ConnectDatabaseAuswerten();
    static Connection db = a.getConnectToDatabase();

    public getlengthfrdb(){

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
}
