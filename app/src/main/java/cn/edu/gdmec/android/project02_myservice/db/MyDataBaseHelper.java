package cn.edu.gdmec.android.project02_myservice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asus on 2018/1/3.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mcontext;
    private SQLiteDatabase db;
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


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
