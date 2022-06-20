package sg.edu.np.mad.mad_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myResultViewHolder> {

    //data types a little inconsistent enjoy the spaghetti code with sauce of choice
    ArrayList<Block> data;

    public myAdapter(ArrayList<Block> input) {data = input;}
    public myResultViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recyclerview, parent, false);
        return new myResultViewHolder(item);
    }
    public void onBindViewHolder(myResultViewHolder holder, int position){

        //reserved space for when data is empty (i.e no search results)


        //set data
        String blk_name = data.get(position).getName();
        int blk_num = data.get(position).getBlockNo();
        String blk_sch = data.get(position).getSchool();

        //using data to set text
        holder.title.setText(blk_name);
        holder.blk_num.setText(blk_num);
        holder.sch.setText(blk_sch);
    }

    //return size of array list
    public int getItemCount() {return data.size();}
}
