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
                "     ID                 INTEGER PRIMARY KEY AUTOINCREMENT   ,\n" +
                "     ID_LEVEL           INTEGER NOT NULL,\n"+
                "     ID_EXER            INTEGER NOT NULL,\n"+
                "     S_EXER             TEXT    NOT NULL,\n"+
                "     S_RIS              TEXT    NOT NULL,\n"+
                "     B_ISCOMPLETE       BOOLEAN NOT NULL, \n"+
                "     S_SOLUTION_LOGIC   TEXT    NOT NULL \n"+
                "    );" ;
        db.execSQL(sql);
        sql = "CREATE UNIQUE INDEX IF NOT EXISTS idx_EXERCISES_ID \n" +
                "ON EXERCISES (ID);" ;
        db.execSQL(sql);
        sql = "CREATE UNIQUE INDEX IF NOT EXISTS idx_EXERCISES_ID_LEVEL_ID_EXER \n" +
                "ON EXERCISES (ID_LEVEL,ID_EXER);";
        db.execSQL(sql);
        sql = "INSERT INTO EXERCISES (ID_LEVEL,ID_EXER,S_EXER, S_RIS,B_ISCOMPLETE,S_SOLUTION_LOGIC)\n" +
                "VALUES (0,0,'0 - 1 - 2 - 3 - ... - 5'       , '4'   , 0,'')," +
                "       (0,1,'2 - 4 - 8 - ... - 32'          , '16'  , 0,'')," +
                "       (0,2,'10 - 20 - ... - 40'            , '30'  , 0,'')," +
                "       (0,3,'... - 12 - 36 - 108'           , '4'   , 0,'')," +
                "       (0,4,'3 - 9 - ... - 81 - 243'        , '27'  , 0,'')," +
                "       (0,5,'2 - 12 - ... - 432'            , '72'  , 0,'')," +
                "       (0,6,'120 - 60 - ... - 15'           , '30'  , 0,'')," +
                "       (0,7,'0,7 - ... - 1,7 - 2,2'         , '1.2' , 0,'')," +
                "       (0,8,'34 - ... - 56'                 , '45'  , 0,'')," +
                "       (0,9,'54 - 65 - ... - 87'            , '76'  , 0,'')," +
                "       (0,10,'... - 79 - 97 - 115'          , '61'  , 0,'')," +
                "       (0,11,'... - 58 - 60'                , '56'  , 0,'')," +
                "       (0,12,'1 - 4 - ... - 10'             , '7'   , 0,'')," +
                "       (0,13,'15 - ... - 29 - 36'           , '22'  , 0,'')," +
                "       (0,14,'17 - 24 - ...'                , '8'   , 0,'')," +
                "       (0,15,'8 - ... - 24 - 21 - 72 - 36 - 216'    , '8' , 0,':2 - *6')," +// /2*6
                "       (0,16,'33 - ... - 49 - 57'           , '49'  , 0,'')," +
                "       (0,17,'45 - ... - 61 - 69'           , '53'  , 0,'')," +
                "       (0,18,'13 - 12 - 14 - 13 - ...'      , '15'  , 0,'-1 - +2')," +//-1+2
                "       (0,19,'37 - 33 - ... - 25'           , '29'  , 0,'')," +
                "       (0,20,'39 - ... - 27 - 21'           , '33'  , 0,'')," +
                "       (0,21,'500 - ... - 200 - 100 - 50'   , '250' , 0,':2-50')," +
                "       (0,22,'... - 84 - 42 - 14 - 7'       , '252' , 0,':3:2')," +
                "       (0,23,'100 - 25 - 24 - ... - 5'      , '6'   , 0,':4-1')," +
                "       (0,24,'4000 - 800 - ... - 40 - 10'   , '200' , 0,':5:4')," +
                "       (0,25,'150 - 30 - 35 - ... - 12'     , '6'   , 0,':5+5')," +
                "       (0,26,'12 - 23 - ... - 37 - 40'      , '26'  , 0,'+11+3')," +
                "       (0,27,'... - 25 - 50 - 52 - 104'     , '23'  , 0,'+2·2')," +
                "       (0,28,'. - 90 - 100 - 120 - 130'     , '70'  , 0,'+20+10')," +
                "       (0,29,'20 - ... - 15 - 7 - 10'       , '12'  , 0,'-8+3')," +
                "       (0,30,'13 - 17 - ... - 11 - 1'       , '7'   , 0,'+4-10')," +
                "       (0,31,'? - 15 - 9 - 13 - 7'          , '11'  , 0,'+4-6')," +
                "       (0,32,'10 - 14 - 7 - ... - 4'        , '11'  , 0,'+4-7')," +
                "       (0,33,'5 - 10 - ... - 17 - 19'       , '12'  , 0,'+5+2')," +
                "       (0,34,'... - 20 - 27 - 33 - 40'      , '14'  , 0,'+6+7')," +
                "       (0,35,'... - 10 - 20 - 27 - 54'      , '3'   , 0,'+7·2')," +
                "       (0,36,'2 - 9 - ... - 34 - 102'       , '27'  , 0,'+7·3')," +
                "       (0,37,'2 - 9 - ... - 15 - 14'        , '8'   , 0,'+7-1')," +
                "       (0,38,'2 - 11 - 22 - ... - 42'       , '31'  , 0,'+9+11')," +
                "       (0,39,'... - 12 - 7 - 16 - 11'       , '3'   , 0,'+9-5')," +
                "       (0,40,'10 - 9 - 18 - 17 - ...'       , '34'  , 0,'-1·2')," +
                "       (0,41,'1 - 1 - 2 - 6 - ...'          , '24'  , 0,'*1*2*3*4')," +
                "       (0,42,'13 - 13 - 26 - 78 - ...'      , '312' , 0,'*1*2*3*4')," +
                "       (0,43,'1 - 1 - 3 - ... - 105'        , '15'  , 0,'*1*3*5*7')," +
                "       (0,44,'... - 180 - 60 - 15 - 3'      , '360' , 0,':2:3:4:5'),"+
                "       (0,45,'... - 10 - 14 - 22 - 38'      , '8'   , 0,'+2 +4 +8 +16'),"+
                "       (0,46,'... - 34 - 38 - 43'           , '32'  , 0,'+2 +4 +8 +16'),"+
                "       (0,47,'... - 85 - 89 - 94 - 100'     , '82'  , 0,'+3+4+5'),"+
                "       (0,48,'2 - 5 - 11 - ... - 47'        , '16'  , 0,'+3+4+5+6'),"+
                "       (0,49,'2 - 7 - 17 - 37 - ...'        , '57'  , 0,'+3+6+12+24'),"+
                "       (0,50,'... - 100 - 112 - 130 - 154'  , '95'  , 0,'+5+10+20+40'),"+
                "       (0,51,'2 - 8 - ... - 38 - 62'        , '20'  , 0,'+6 +12 +18 +24'),"+
                "       (0,52,'113 - 100 - 74 - ...'         , '35'  , 0,'-13-26-39'),"+
                "       (0,53,'10 - 8 - 5 - ...'             , '1'   , 0,'-2-3-4'),"+
                "       (0,54,'... - 37 - 34 - 30 - 25'      , '39'  , 0,'-2-3-4-5'),"+
                "       (0,55,'28 - ... - 23 - 19 - 14'      , '26'  , 0,'-2-3-4-5'),"+
                "       (0,56,'93 - 90 - 85 - ... - 66'      , '77'  , 0,'-3-5-8-11'),"+
                "       (0,57,'1 - ... - 10 - 13 - 15'       , '6'   , 0,'+5+4+3+2'),"+
                "       (0,58,'1 - ... - 20 - 60 - 120\t'    , '5'   , 0,'·5·4·3·2'),"+
                "       (0,59,'100 - 104 - ... - 109 - 110'  , '107' , 0,'+4+3+2+1'),"+
                "       (0,60,'... - 134 - 130 - 127 - 125'  , '139' , 0,'-5-4-3-2'),"+
                "       (0,100,''   , '' , 0,'');";
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
                    excur.I_TYPE_OF_EXER= c.getInt(6);
                return excur;
                }
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        return null;
    }

}
