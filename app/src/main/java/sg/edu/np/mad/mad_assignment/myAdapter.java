package sg.edu.np.mad.mad_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myResultViewHolder> {

    ArrayList<String> data;

    public myAdapter(ArrayList<String> input) {data = input;}
    public myResultViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recyclerview, parent, false);
        return new myResultViewHolder(item);
    }
    public void onBindViewHolder(myResultViewHolder holder, int position){
        //using location to match with data
        String s = data.get(position);

        //set data
    }

    //return size of array list
    public int getItemCount() {return data.size();}
}
