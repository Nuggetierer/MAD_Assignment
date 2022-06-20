package sg.edu.np.mad.mad_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Block> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        //intent passes a search information
        Intent receive_intent = getIntent();

        //reminder to set the information parsed as search_string
        String search_info = receive_intent.getStringExtra("search_string", null);

        //use search information (in string) used to run a query to get data

        //these few lines a little bit ?????

        //for when there is no search
        Block holder = new Block();
        if (search_info == null) {

            int i = 0;
            while (true) {
                holder = dbHandler.getblock(i);

                if (holder == null) {
                    break;
                } else {
                    resultList.add(holder);
                    i++;
                }
            }
        }
        //for when there is a search
        else {
            while (true) {
                holder = dbHandler.findblock(search_info);

                if (holder == null) {
                    break;
                } else {
                    resultList.add(holder);
                }
            }

            RecyclerView resultRecycler = findViewById(R.id.resultsView);
            myAdapter resultAdapter = new myAdapter(resultList);
            LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
            resultRecycler.setLayoutManager(myLayoutManager);
            resultRecycler.setAdapter(resultAdapter);
        }
    }
}