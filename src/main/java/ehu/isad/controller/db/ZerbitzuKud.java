package ehu.isad.controller.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    private int id = 5;

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<String> lortuZerbitzuak() {

        String query = "select idservices, izena from services";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();

        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                int kodea = rs.getInt("idservices");
                String izena = rs.getString("izena");

                System.out.println(kodea + ":" + izena);
                emaitza.add(izena);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void dropRow(String izena){
        String query = "delete from zerbitzuakDuxon.services where izena='"+izena+"';";
        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void sartuElem(String izena){
        String query="insert into zerbitzuakDuxon.services (`izena`) values('"+izena+"');";
        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);

    }

}
