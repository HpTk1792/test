package com.android.alejandroquiroga;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.Models.ExampleElementDao;

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


    /**
     * Method get all exampleElements on file users
     *
     * @return List<ExampleElement>
     */
    public List<ExampleElement> getExampleElements(){
        return exampleElementDao.getExampleElements();
    }

    /**
     * Method for save exampleElement into SQLite
     *
     * @param exampleElement Object User
     */
    public void saveExampleElement(ExampleElement exampleElement){
        exampleElementDao.addExampleElement(exampleElement);
    }

    /**
     * Method for update a exampleElement into SQLite
     *
     * @param exampleElement
     */

    public void updateExampleElement(ExampleElement exampleElement){
        exampleElementDao.updateExampleElement(exampleElement);
    }

    /**
     * Method for delete a exampleElement into SQLite
     *
     * @param exampleElement
     */

    public void deleteExampleElement(ExampleElement exampleElement){
        exampleElementDao.deleteExampleElement(exampleElement);
    }




    //PostgreSQL

    public void insertInPostgres(final ExampleElement exampleElement){

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
}
