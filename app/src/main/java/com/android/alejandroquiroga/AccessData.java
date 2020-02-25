package com.android.alejandroquiroga;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.Models.ExampleElementDao;

import java.util.ArrayList;
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
    private String user = "CHANGE ME!";
    private String pwd = "PLEASE!";
    private String url = "jdbc:postgresql://192.168.0.22:5432/ada";

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

    private ArrayList<ExampleElement> test = new ArrayList<ExampleElement>();

    public ArrayList<ExampleElement> getPostgresList(){ return test; }

    public void insertInPostgres(final ExampleElement exampleElement, String query){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    st.executeUpdate("INSERT INTO ExampleElementTable VALUES('" +
                            exampleElement.getId() +"','" +
                            exampleElement.getAttribute1() + "','" +
                            exampleElement.getAttribute2() + "','" +
                            exampleElement.getAttribute3() + "')");
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

    public void updateInPostgres(final ExampleElement exampleElement, int id){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    st.executeUpdate("UPDATE ExampleElementTable " +
                            "SET Attribute1 = '" + exampleElement.getAttribute1() + "'," +
                            "Attribute2 = '" + exampleElement.getAttribute2() + "'," +
                            "Attribute3 = '" + exampleElement.getAttribute3() + "'" +
                            "WHERE Id = '" + exampleElement.getId() + "';");

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

    public void getInPostgres(final ExampleElement exampleElement, int id){


        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    test = (ArrayList<ExampleElement>) st.executeQuery("SELECT *" +
                            "FROM ExampleElementTable");

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
