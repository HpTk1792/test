package com.android.alejandroquiroga;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.android.alejandroquiroga.Models.Esdeveniment;
import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.Models.ExampleElementDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;


public class AccessData {

    @SuppressLint("StaticFieldLeak")
    private static AccessData sAccessData;

    // SQLite
    private ExampleElementDao exampleElementDao;

    //TODO Change Credentials
    //PostgreSQL
    private Connection conn;
    private String user = "ada";
    private String pwd = "lovelace";
    private String url = "jdbc:postgresql://192.168.0.22:5432/esdeveniments";

    private AccessData (Context ctx){
        Context appContext = ctx.getApplicationContext();
        ExerciseDatabase database = Room.databaseBuilder(appContext, ExerciseDatabase.class, "Exercise")
                .allowMainThreadQueries().build();
        exampleElementDao = database.getExampleElementDao();
        //TODO Remove or use this
//        exampleElement2Dao = database.getExampleElement2Dao();
    }

    public static AccessData getAccessData(Context ctx){
        if(sAccessData == null) sAccessData = new AccessData(ctx);
        return sAccessData;
    }

    //SQLite Methods

    public List<ExampleElement> getExampleElements(){ return exampleElementDao.getExampleElements(); }
    public ExampleElement getExampleElement(String id) { return exampleElementDao.getExampleElement(id); }
    public void saveExampleElement(ExampleElement exampleElement){ exampleElementDao.addExampleElement(exampleElement); }
    public void updateExampleElement(ExampleElement exampleElement){ exampleElementDao.updateExampleElement(exampleElement); }
    public void deleteExampleElement(ExampleElement exampleElement){ exampleElementDao.deleteExampleElement(exampleElement); }

    //PostgreSQL

    private ArrayList<Esdeveniment> esdevenimentArrayList = new ArrayList<Esdeveniment>();

    public ArrayList<Esdeveniment> getEsdevenimentsList(){ return esdevenimentArrayList; }

    public void insertInPostgres(final Esdeveniment esdeveniment){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    st.executeUpdate("INSERT INTO ExampleElementTable VALUES('" +
                            esdeveniment.title +"','" +
                            esdeveniment.date + "','" +
                            esdeveniment.place + "','" +
                            esdeveniment.email + "','" +
                            esdeveniment.room + "'," +
                            esdeveniment.price + "," +
                            esdeveniment.people + ",'" +
                            esdeveniment.description + "')");
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        hiloPostgres.start();
    }

    public void updateInPostgres(final Esdeveniment esdeveniment, final String title){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    st.executeUpdate("UPDATE EventDetail " +
                            "SET title = '" + esdeveniment.title +"','" +
                            "evdate = '" + esdeveniment.date + "','" +
                            "place = '" + esdeveniment.place + "','" +
                            "email = '" + esdeveniment.email + "','" +
                            "room = '" + esdeveniment.room + "'," +
                            "price = " + esdeveniment.price + "," +
                            "people = " + esdeveniment.people + ",'" +
                            "description = '" + esdeveniment.description + "'" +
                            "WHERE title LIKE " + title + "'");

                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        hiloPostgres.start();
    }

    public void getInPostgres(){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {

                    /*
                    NO SE ME CONECTA A POSTGRE ASÍ QUE LE METO YO LA CHICHA
                    */
                    Date date = new Date(2020,02,28);
                    Esdeveniment esv = new Esdeveniment("Examen",
                            "INS jdA",
                            "Aula 39",
                            20,
                            date,
                            "jda@jda.org",
                            50,
                            "Examen ABP de M3, M6, M8 i M9");
                    esdevenimentArrayList = new ArrayList<Esdeveniment>(Arrays.asList(esv));
                    /*
                    FIN DE LA TRAMPA
                    */

                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    esdevenimentArrayList = (ArrayList<Esdeveniment>) st.executeQuery("SELECT *" +
                            "FROM EventsDetail");

                    conn.close();

                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        hiloPostgres.start();
    }

}
