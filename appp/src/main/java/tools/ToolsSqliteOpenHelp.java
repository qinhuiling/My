package tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/10/10.
 */

public class ToolsSqliteOpenHelp extends SQLiteOpenHelper {
    public ToolsSqliteOpenHelp(Context context) {
        super(context, "taobao.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table taobao_hunt (title varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
