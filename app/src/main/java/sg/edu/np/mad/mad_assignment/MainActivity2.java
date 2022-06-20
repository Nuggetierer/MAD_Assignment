package sg.edu.np.mad.mad_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView resultRecycler;
    ArrayList<Block> resultList;
    myAdapter resultAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //refresh database for use in this activity
       DBHandler dbHandler = new DBHandler(this, null, null, 1);

        searchView = findViewById(R.id.searchInfo);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });

        resultRecycler = findViewById(R.id.resultsView);
        resultRecycler.setHasFixedSize(true);
        resultRecycler.setLayoutManager(new LinearLayoutManager(this));
        resultList = new ArrayList<>();

        //add information using db
        resultList = dbHandler.retrieveBlocks();

        resultAdapter = new myAdapter(resultList);
        resultRecycler.setAdapter(resultAdapter);
    }

    private void filterList(String text) {
        ArrayList<Block> filteredList = new ArrayList<>();
        for (Block blk : resultList){
            if(blk.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(blk);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "No Matching Blocks", Toast.LENGTH_SHORT).show();
        }
        else{
            resultAdapter.setData(filteredList);
        }
    }
}