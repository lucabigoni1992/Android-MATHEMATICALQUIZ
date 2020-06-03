package luca.bigoni.mathematicalquiz.DataBase;

import android.content.Context;
import android.database.Cursor;
import  android.database.sqlite.SQLiteOpenHelper;
import  android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Exercises.db";
    private static final int DATABASE_VERSION = 1;
    private static String sql;

    private static SQLiteDatabase db;




    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public DataBaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE IF NOT EXISTS EXERCISES \n" +
                "    ( \n" +
                "     ID           INTEGER PRIMARY KEY AUTOINCREMENT   ,\n" +
                "     ID_LEVEL     INTEGER NOT NULL,\n"+
                "     ID_EXER      INTEGER NOT NULL,\n"+
                "     S_EXER       TEXT    NOT NULL,\n"+
                "     S_RIS        TEXT    NOT NULL,\n"+
                "     B_ISCOMPLETE BOOLEAN NOT NULL \n"+
                "    );" ;
        db.execSQL(sql);
        sql = "CREATE UNIQUE INDEX IF NOT EXISTS idx_EXERCISES_ID \n" +
                "ON EXERCISES (ID);" ;
        db.execSQL(sql);
        sql = "CREATE UNIQUE INDEX IF NOT EXISTS idx_EXERCISES_ID_LEVEL_ID_EXER \n" +
                "ON EXERCISES (ID_LEVEL,ID_EXER);";
        db.execSQL(sql);
        sql = "INSERT INTO EXERCISES (ID_LEVEL,ID_EXER,S_EXER, S_RIS,B_ISCOMPLETE)\n" +
                "VALUES (0,0,'2+2'      , '4', 0)," +
                "       (0,1,'2+2*2/2'  , '4', 0),"+
                "       (0,2,'2+2*2/2-2', '0', 0);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         sql = "" ;
        db.execSQL(sql);
    }
    public static void cleanExer() {
        sql = "UPDATE   EXERCISES " +
                "SET    B_ISCOMPLETE=0\n";
        db.execSQL(sql);
    }
    public static void UpdateSuccessExercises(MappingExercises exer) {
        sql = "UPDATE   EXERCISES " +
                "SET    B_ISCOMPLETE=1\n" +
                "WHERE  ID="+exer.ID;
        db.execSQL(sql);
    }
    public static MappingExercises readAllExercises() {
        sql = "SELECT * FROM EXERCISES" ;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()){
            MappingExercises excur=new MappingExercises();
            do {
                excur.B_ISCOMPLETE= c.getInt(5)==1;
                if(excur.B_ISCOMPLETE==false){
                // Passing values
                    excur.ID= c.getInt(0);
                    excur.ID_EXER= c.getInt(1);
                    excur.ID_LEVEL= c.getInt(2);
                    excur.S_EXER= c.getString(3);
                    excur.S_RIS= c.getString(4);
                    excur.B_ISCOMPLETE= c.getInt(5)==1;
                return excur;
                }
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        return null;
    }

}
