package tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/10/10.
 */

public class ToolsSqlite {

    private final SQLiteDatabase db;
    private String sql;

    public ToolsSqlite(Context context) {
        ToolsSqliteOpenHelp toolsSqliteOpenHelp = new ToolsSqliteOpenHelp(context);
        db = toolsSqliteOpenHelp.getWritableDatabase();
    }
    public void addHunt(String title){
        db.execSQL("insert into taobao_hunt (title) values (?)",new String[]{title});
    }
    public List<String> selectHunt(){
        Cursor cursor = db.query("taobao_hunt", null, null, null, null, null, null);
        List<String> list = new ArrayList<>();
        while ((cursor.moveToNext())){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            list.add(title);
        }
        return list;
    }
    public void deleteHunt(int isno,String title){
        if(isno == 0){
            sql = "delete from taobao_hunt ";
            db.execSQL(sql);
        }else {
            sql = "delete from taobao_hunt where title = (?)";
            db.execSQL(sql,new String[]{title});
        }

    }
}
