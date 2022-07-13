package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.FoodCourt;
import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.myImageViewHolder;
import sg.edu.np.mad.mad_assignment.myResultViewHolder;

public class FCAdapter extends RecyclerView.Adapter<FCViewHolder> {
    ArrayList<FoodCourt> data;
    public FCAdapter(ArrayList<FoodCourt> input) {this.data = input;}

    public void setData(ArrayList<FoodCourt> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public FCViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fcstall_recyclerview, parent, false);
        return new FCViewHolder(item);
    }

    public void onBindViewHolder(FCViewHolder holder, @SuppressLint("RecyclerView") int position){

        //set data
//        String fct_name = data.get(position).getFoodCourtName();
//        String fct_loca = data.get(position).getfctLocation();
        FoodCourt f = data.get(position);
        String stall_name = f.getstallname();
        String stall_descr = f.getstalldescription();

        //using data to set text
//        holder.FCTname.setText(fct_name);
//        holder.FCTloca.setText(fct_loca);
        holder.STLname.setText(stall_name);
        holder.STLdescr.setText(stall_descr);

    }

    //return size of array list
    public int getItemCount() {return data.size();}
}
