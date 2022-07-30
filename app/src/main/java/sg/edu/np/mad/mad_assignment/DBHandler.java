package sg.edu.np.mad.mad_assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;

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
    public static String column_eventdate = "Event_Date";
    public static String column_eventname = "Event_Name";
    public static String column_eventdescription = "Event_Description";
    public static String column_eventtype = "Event_Type";

    // Information for Food table
    public static String FoodCourt = "Food_Court";
    public static String column_fctName = "Fct_Name";
    public static String column_fctLocation = "Location";
    public static String column_stallname = "Stall_Name";
    public static String column_stalldescription = "Stall_Description";

    public static int DATABASE_VERSION = 2;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //sql code for creating table (Blocks)
        String CREATE_TABLE_1 = "CREATE TABLE " + Blocks +"(" + column_blockno + " INTEGER," + column_name
                + " TEXT," + column_description + " TEXT," + column_school + " TEXT," + column_type + " TEXT" + ")";

        String CREATE_TABLE_2 = "CREATE TABLE " + Events + "(" + column_eventdate + " TEXT," + column_eventname + " TEXT,"
                + column_eventdescription + " TEXT," + column_eventtype + " TEXT" + ")";

        String CREATE_TABLE_3 = "CREATE TABLE " + FoodCourt +"(" + column_fctName + " TEXT," + column_fctLocation + " TEXT," + column_stallname + " TEXT,"
                + column_stalldescription + " TEXT" + ")";

        //execute sql queries
        db.execSQL(CREATE_TABLE_1);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);

        //sql query for filling up tables (pending information)

        //Code to add more queries
        //+ "(" + ", " + "'', " + "'', " + "'', " + "''" + "),"

        String POPULATE_TABLE_1 = "INSERT INTO " + Blocks + "(" + column_blockno + ", " + column_name + ", " +
                column_description + ", " + column_school + ", " + column_type + ")"
                + "VALUES"
                + "(" + "1, " + "'Admin Block', " + "'Consists of the library, atrium, Each A Cup, Old Chang Kee Ltd, Campus Deli, Zesty Tomato', " + "'General', " + "'General'" + "), "
                + "(" + "5, " + "'Dialogue in the Dark', " + "'Experience everyday situations in complete darkness. Offering a platform to raise awareness and facilitate inclusion of all segments in the community, regardless of race, religion and disabilities, as they are no longer visible in the dark. ', " + "'General', " + "'General'" + "),"
                + "(" + "68, " + "'Convention Center', " + "'Located at the center of Ngee Ann Polytechnic campus, the Convention Center acts as a significant venue of countless memorable events for their students.', " + "'General', " + "'General'" + "),"
                + "(" + "46, " + "'Aerospace Hub', " + "'No Description', " + "'General', " + "'General'" + "),"
                + "(" + "53, " + "'School of Film and Media Studies', " + "'FMS offers innovative platforms for realistic learning, industry mentorship, entrepreneurship and service learning. With its well-equipped studio and soundstage spaces, its top-notch learning environment inspires industry-standard work.', " + "'FMS', " + "'FMS'" + "),"
                + "(" + "31, " + "'School of Infocomm Technology', " + "'At ICT, you will get to learn in a Smart Learning Space well-equipped with the Internet of Things. You will also have varied opportunities to earn professional certifications, gain industry exposure, and work with external organizations to develop innovative IT solutions.', " + "'Best School', " + "'ICT'" + "),"
                + "(" + "72, " + "'School of Business and Accountancy', " + "'BA diplomas are well plugged into the digital future. Thanks to the new Business Digitalisation Track and modules in Business Analytics and Digital Business Solutions, you will be ready for the digital economy, regardless of the industry you are in.', " + "'BA', " + "'BA'" + "),"
                + "(" + "7, " + "'School of Engineering East', " + "'At SoE, there are many exciting opportunities to inspire our students’ passion for learning and innovating. Our strong industry links also ensure that they pick up relevant industry skills and are exposed to emerging technologies. Thanks to our industry-standard facilities, students can gain an authentic learning experience, or work on future city projects through mentorships, learning journeys and internships. Plus, they’ll even get to develop engineering solutions that benefit society through service-learning or work on an integrated real-world project.', " + "'General', " + "'General'" + "),"
                + "(" + "37, " + "'School of Engineering West', " + "'At SoE, there are many exciting opportunities to inspire our students’ passion for learning and innovating. Our strong industry links also ensure that they pick up relevant industry skills and are exposed to emerging technologies. Thanks to our industry-standard facilities, students can gain an authentic learning experience, or work on future city projects through mentorships, learning journeys and internships. Plus, they’ll even get to develop engineering solutions that benefit society through service-learning or work on an integrated real-world project.', " + "'General', " + "'General'" + "),"
                + "(" + "58, " + "'Interdisciplinary Sciences', " + "'Students will be given opportunities to broaden their horizons in the exciting fields of Communication, Innovation, World Issues, Project ID and explore topics in Humanities, Business, Mathematics or Foreign Languages (Japanese, French or Korean) so as to gain an advantage as global smart professionals.', " + "'IS', " + "'IS'" + "),"
                + "(" + "34, " + "'School of Design and Environment', " + "'At DE, students gain new economy skills such as design thinking and user experience/interface (UX/UI) design. They also apply design thinking and project-based learning methods in the management of hotel and leisure facilities to improve service standards and operational efficiencies, while incorporating green technologies and best practices. Lastly, learn how to market and manage public and private properties to help owners maximize the value of their assets.', " + "'DE', " + "'DE'" + "),"
                + "(" + "81, " + "'School of Health Sciences', " + "'HS provides the best learning environment to prepare you for the rigors of work and industry demands. Our top-notch facilities include the Patient Simulation Center and the Optometry Center, as well as fully equipped operating theaters, intensive care units, optometry laboratories and nursing wards. That means that even before you go on your attachment, you’ll already get hands-on experience in real-world environments.', " + "'HS', " + "'HS'" + "),"
                + "(" + "52, " + "'School of Humanities and Social Sciences', " + "'The school of Humanities & Social Sciences aims to develop young changemakers who are passionate about building a better community through advocacy, education and culture, by opening doors to diverse disciplines in humanities and social sciences.', " + "'Humanities', " + "'Humanities'" + "),"
                + "(" + "83, " + "'School of Life Sciences and Chemical Technology', " + "'These five courses are endorsed by industry experts, renowned research institutes and top universities locally and abroad. LSCT also has close links with organizations such as National Parks Board and PUB, Singapore’s National Water Agency. Our strong partnerships will provide you with lots of opportunities to learn in a real-world setting.', " + "'LSCT', " + "'LSCT'" + "),"
                + "(" + "51, " + "'Makan Place', " + "'Makan Place is the biggest food court in Ngee Ann Polytechnic. There are a whooping 19 stalls where the food is much cheaper and better in quality as compared to other places. There is even a subway located here.', " + "'Food Court', " + "'Food Court'" + "),"
                + "(" + "22, " + "'Food Club', " + "'This food court serves as one of the main gathering points for students and staff. Serving mainly engineering and infocomm technology students. ', " + "'Food Court', " + "'Food Court'" + "),"
                + "(" + "18, " + "'PoolSide', " + "'A kopitiam serving food and beverages right next to Ngee Ann Polytechnic’s Swimming Pool.', " + "'Food Court', " + "'Food Court'" + "),"
                + "(" + "73, " + "'Munch', " + "'A kopitiam offering food and beverages. Serving mainly those from the School of Business & Accountancy', " + "'Food Court', " + "'Food Court'" + "),"
                + "(" + "56, " + "'Sandbox', " + "'The Sandbox is a safe environment for people to develop their applications. Sandbox symbolizes a spirit- providing a risk-free and safe avenue for students to try out their ideas, explore and test their innovation and entrepreneurship potential.', " + "'General', " + "'General'" + "),"
                + "(" + "0, " + "'Solar Tech Centre', " + "'Used by Solar Photovoltaic (PV) Technology students to learn the fundamentals of solar photovoltaic (PV) technology and design and installation of standalone and grid-connected PV systems.', " + "'General', " + "'General'" + "),"
                + "(" + "0, " + "'Training Field', " + "'No Description', " + "'General', " + "'General'" + "),"
                + "(" + "0, " + "'Swimming Pool', " + "'No Description', " + "'Genral', " + "'General'" + "),"
                + "(" + "0, " + "'Sports Complex', " + "'No Description', " + "'General', " + "'General'" + "),"
                + "(" + "0, " + "'Main Field', " + "'No Description', " + "'General', " + "'Genreral'" + ")";

        db.execSQL(POPULATE_TABLE_1);

        String POPULATE_TABLE_2 = "INSERT INTO " + Events + "(" + column_eventdate + ", " + column_eventname + ", " +
                column_eventdescription + " ," + column_eventtype  + ")"
                + "VALUES"
                + "(" + "'17 Apr'," +  "'CSOP'," + "'Ict Orientation Camp to introduce freshman to the school through icebreakers and activities'," + "'Ict event'" + "),"
                + "(" + "'18-26 Aug', " +"'Exams Week'," + "'A week dedicated to common test and exams'," + "'Ict event'" + "),"
                + "(" + "'12 Aug'," +  "'CSF Day'," + "'A day that celebrates all achievements done for Cybeer Security'," + "'Ict event'" + "),"
                + "(" + "'9 Aug'," +  "'National Day'," + "'National day, the school will be organising games and activities for students to commemorate Singapores birthday'," + "'Np School Wide Event'" + "),"
                + "(" + "'10 July'," +  "'No Bag Day'," + "'Students will carry their stuff with anything but a backpack'," + "'Np School Wide Event'" + "),"
                + "(" + "'23 June'," +"'Sports and Dance camp'," + "'A camp that has Sport Activities such as Track and Field, Frisbee and captains ball'," + "'Np School Wide Event'" + ")";

        db.execSQL(POPULATE_TABLE_2);

        String POPULATE_TABLE_3 = "INSERT INTO " + FoodCourt +"(" + column_fctName + ", " + column_fctLocation + ", " + column_stallname + ", "
                + column_stalldescription + ")"
                + "VALUES"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'INDONESIAN', " + "'Perfect for Muslim diners since food is halal, and is known for their Ayam Penyet.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'KOREAN', " + "'Perfect for Muslim diners since food is halal, and is known for their hotplate set.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'WESTERN FUSION', " + "'The main comes with 1 carb, 1 side and a choice of either salad or miso soup.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'SALAD', " + "'Toss and turn with your favourite ingredients.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'MIXED VEG RICE', " + "'Mixed Rice Veg Dishes.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'WAFFLE & TAKOYAKI', " + "'Not very hungry? Visit us for a light snack.'" + "),"
                + "(" + "'Food Club'," + "'Located at Block 22', " + "'YOGURT', " + "'Order a fresh yogurt or parfait, to cool down from the warm and humid weather!'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'SUBWAY', " + "'Perfect for Muslim diners since food is halal, they may select a wide variety of meats, vegetables, fresh baked breads and flavorful condiments and sauces.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'THAI', " + "'Perfect for Muslim diners since food is halal, and is known for their Tom Yum Fried Rice and Garlic Chicken.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'WESTERN', " + "'Best known for their Crispy Chicken Cutlet, Crispy Breaded Fish Rice and Popcorn Chicken.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'JAPANESE', " + "'Best known for their Curry Rice with Cheese Omelette or Chicken Kaarage.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'MALA', " + "'Most loved by spicy food enthusiasts with 5 levels of spiciness.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'VEGETARIAN', " + "'Mixes Rice Veg Dishes.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'BAN MIAN', " + "'Craving for something soupy? We are that place.'" + "),"
                + "(" + "'Makan Place'," + "'Located at Block 51', " + "'YONG TAU FU', " + "'Choices of 3 noodles and 1 rice. With a choice of soup, dry and laksa.'" + "),"
                + "(" + "'Others'," + "'Located at Block 73', " + "'Old Chang Kee', " + "'Grab a quick and light snack.'" + "),"
                + "(" + "'Others'," + "'Located at OurSpace@72', " + "'TuckShop', " + "'Best known for the Cheesy Hot Dog, Chicken Sandwich and Ice Cream Bread/Wafer.'" + "),"
                + "(" + "'Others'," + "'Located at Block 27 (beside OIC)', " + "'What Tea', " + "'Refreshing teas to brighten your busy day.'" + "),"
                + "(" + "'Others'," + "'Located at Agile@Blk58', " + "'Canvas Yogurt', " + "'Mix & Match with fruits and toppings.'" + "),"
                + "(" + "'Others'," + "'Located at Block 1', " + "'Cheers', " + "'A convenience store with microwaveable food, snacks and drinks.'" + ")";

        db.execSQL(POPULATE_TABLE_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Blocks);
        db.execSQL("DROP TABLE IF EXISTS " + Events);
        db.execSQL("DROP TABLE IF EXISTS " + FoodCourt);
        onCreate(db);
    }

    //function to find block use for search function to return to recycler view
    //specific blocks
    //could be redundant
    public Block findblock(String block_name)
    {
        //sql code to query database for block information
        String query = "SELECT * FROM " + Blocks + " WHERE " + column_name
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
    //redundant
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

    //Create a list of all blocks
    public ArrayList<Block> retrieveBlocks()
    {
        String query = "SELECT * FROM " + Blocks;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Block> queryData = new ArrayList<>();

        int counter = 0;

        while (true){
            if(cursor.moveToPosition(counter)){
                Block queryBlock = new Block();
                queryBlock.setBlockNo(cursor.getInt(0));
                queryBlock.setName(cursor.getString(1));
                queryBlock.setDescription(cursor.getString(2));
                queryBlock.setSchool(cursor.getString(3));
                queryBlock.setType(cursor.getString(4));

                queryData.add(queryBlock);
                counter += 1;
            }
            else
            {
                break;
            }
        }
        cursor.close();
        db.close();
        return queryData;
    }

    public ArrayList<Event> retrieveEvent()
    {
        String query = "SELECT * FROM " + Events;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Event> queryEData = new ArrayList<>();

        int counter = 0;

        while (true){
            if(cursor.moveToPosition(counter)){
                Event queryEvent = new Event();
                queryEvent.setEventDate(cursor.getString(0));
                queryEvent.setEventName(cursor.getString(1));
                queryEvent.setEventDescription(cursor.getString(2));
                queryEvent.setEventType(cursor.getString(3));

                queryEData.add(queryEvent);
                counter += 1;
            }
            else
            {
                break;
            }
        }
        cursor.close();
        db.close();
        return queryEData;
    }

    //function to get event information from database
    //redundant
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

    //Create a list of all food courts
    public ArrayList<FoodCourt> retrieveFoodCourt()
    {
        String query = "SELECT * FROM " + FoodCourt;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<FoodCourt> queryFCData = new ArrayList<>();

        int counter = 0;

        while (true){
            if(cursor.moveToPosition(counter)){
                FoodCourt queryFCT = new FoodCourt();
                queryFCT.setFoodCourtName(cursor.getString(0));
                queryFCT.setfctLocation(cursor.getString(1));
                queryFCT.setstallname(cursor.getString(2));
                queryFCT.setstalldescription(cursor.getString(3));

                queryFCData.add(queryFCT);
                counter += 1;
            }
            else
            {
                break;
            }
        }
        cursor.close();
        db.close();
        return queryFCData;
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
