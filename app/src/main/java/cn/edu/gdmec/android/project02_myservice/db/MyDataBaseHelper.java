package cn.edu.gdmec.android.project02_myservice.db;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.edu.gdmec.android.project02_myservice.Sense;

/**
 * Created by asus on 2018/1/3.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mcontext;
    private SQLiteDatabase db;
    private List<Sense> list;
    final String CREATE_SENSE = " create table sense (" +
            " id integer primary key autoincrement, " +
            " temperature integer," +
            " humidity integer," +
            " lightIntensity integer," +
            " CO2 integer," +
            " pm integer," +
            " status integer," +
            " timer integer";


    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SENSE);
        Toast.makeText(mcontext,"创建成功",Toast.LENGTH_LONG).show();

    }
    /*public List<Sense> selectS (){
        Sense sense = new Sense();
        db = this.getReadableDatabase();
        db.execSQL("select ");

        return sense;
    }*/
    public void insertS(int tem,int hum,int ligt,int co,int ppm,int statu){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("temperature",tem);
        contentValues.put("humidity",hum);
        contentValues.put("lightIntensity",ligt);
        contentValues.put("CO2",co);
        contentValues.put("pm",ppm);
        contentValues.put("status",statu);
        db.insert("sense",null,contentValues);
        Log.i("TTTTT","ccccccccccc");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
