package sg.edu.np.mad.mad_assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "info.db";

    //Information for block_info table
    public static String Blocks = "Blocks";
    public static String column_blockno = "BlockNo";
    public static String column_name = "Name";
    public static String column_description = "Description";
    public static String column_school = "School";
    public static String column_type = "Type";

    //Information for event table
    public static String Events = "Events";
    public static String column_eventname = "Event_Name";
    public static String column_eventdescription = "Event_Description";
    public static String column_eventtype = "Event_Type";

    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //sql code for creating table (Blocks)
        String CREATE_TABLE_1 = "CREATE TABLE " + Blocks +"(" + column_blockno + " INTEGER," + column_name
                + " TEXT," + column_description + " TEXT," + column_school + " TEXT," + column_type + " TEXT" + ")";

        String CREATE_TABLE_2 = "CREATE TABLE " + Events + "(" + column_eventname + " TEXT,"
                + column_eventdescription + " TEXT," + column_eventname + " TEXT," + column_type + " TEXT" + ")";

        //execute sql queries
        db.execSQL(CREATE_TABLE_1);
        db.execSQL(CREATE_TABLE_2);

        //sql query for filling up tables (pending information)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Blocks);
        db.execSQL("DROP TABLE IF EXISTS " + Events);
        onCreate(db);
    }

    //function to find block use for search function to return to recycler view
    //specific blocks
    public Block findblock(String block_name)
    {
        //sql code to query database for block information
        String query = "SELECT * FROM " + Blocks + "WHERE" + column_name
                + "=\"" + block_name + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Block queryData = new Block();

        if(cursor.moveToFirst()){
            queryData.setBlockNo(cursor.getInt(0));
            queryData.setName(cursor.getString(1));
            queryData.setDescription(cursor.getString(2));
            queryData.setSchool(cursor.getString(3));
        }
        else{
            queryData = null;
        }

        cursor.close();
        db.close();

        return queryData;
    }

    //use in a loop to get every blocks information or use in recycler view to get block indexed i
    //could be faster if when recycler view is open prior to get block keep the database open
    public Block getblock(int i)
    {
        String query = "SELECT * FROM " + Blocks;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Block queryData = new Block();

        if(cursor.moveToPosition(i)){
            queryData.setBlockNo(cursor.getInt(0));
            queryData.setName(cursor.getString(1));
            queryData.setDescription(cursor.getString(2));
            queryData.setSchool(cursor.getString(3));
        }
        else{
            queryData = null;
        }

        cursor.close();
        db.close();

        return queryData;
    }

    //function to get event information from database
    public Event getevent(int i)
    {
        String query = "SELECT * FROM " + Events;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Event queryData = new Event();

        if(cursor.moveToPosition(i)){
            queryData.setEventName(cursor.getString(0));
            queryData.setEventDescription(cursor.getString(1));
        }
        else{
            queryData = null;
        }

        cursor.close();
        db.close();

        return queryData;
    }

    //Above functions may not work for actual usage with recycler view below are rewritten functions
}

//        ⣿⣿⣿⣿⣿⠟⠋⠄⠄⠄⠄⠄⠄⠄⢁⠈⢻⢿⣿⣿⣿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⠃⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⡀⠭⢿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⡟⠄⢀⣾⣿⣿⣿⣷⣶⣿⣷⣶⣶⡆⠄⠄⠄⣿⣿⣿⣿
//        ⣿⣿⣿⣿⡇⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠄⠄⢸⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣇⣼⣿⣿⠿⠶⠙⣿⡟⠡⣴⣿⣽⣿⣧⠄⢸⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⣾⣿⣿⣟⣭⣾⣿⣷⣶⣶⣴⣶⣿⣿⢄⣿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⣿⣿⣿⡟⣩⣿⣿⣿⡏⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⣿⣹⡋⠘⠷⣦⣀⣠⡶⠁⠈⠁⠄⣿⣿⣿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⣿⣍⠃⣴⣶⡔⠒⠄⣠⢀⠄⠄⠄⡨⣿⣿⣿⣿⣿⣿
//        ⣿⣿⣿⣿⣿⣿⣿⣦⡘⠿⣷⣿⠿⠟⠃⠄⠄⣠⡇⠈⠻⣿⣿⣿⣿
//        ⣿⣿⣿⣿⡿⠟⠋⢁⣷⣠⠄⠄⠄⠄⣀⣠⣾⡟⠄⠄⠄⠄⠉⠙⠻
//        ⡿⠟⠋⠁⠄⠄⠄⢸⣿⣿⡯⢓⣴⣾⣿⣿⡟⠄⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠄⠄⣿⡟⣷⠄⠹⣿⣿⣿⡿⠁⠄⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠄⣸⣿⡷⡇⠄⣴⣾⣿⣿⠃⠄⠄⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠄⣿⣿⠃⣦⣄⣿⣿⣿⠇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⢸⣿⠗⢈⡶⣷⣿⣿⡏⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
