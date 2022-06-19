package sg.edu.np.mad.mad_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent recieve_intent = getIntent();

        for(int i = 0; i < 100; i++){
            resultList.add(String.valueOf(i));
        }

        RecyclerView resultRecycler = findViewById(R.id.resultsView);
        myAdapter resultAdapter = new myAdapter(resultList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        resultRecycler.setLayoutManager(myLayoutManager);
        resultRecycler.setAdapter(resultAdapter);
    }
}