package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;
import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;

public class FCAdapter extends RecyclerView.Adapter<FCViewHolder> {

    ArrayList<FoodCourt> FCList;

    public FCAdapter(ArrayList<FoodCourt> input) {this.FCList = input;}

    @Override
    @NonNull
    public FCViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fcstall_recyclerview, parent, false);
        return new FCViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull FCViewHolder holder, int position) {
        //set data
        ArrayList<FoodCourt> FCfilteredList = new ArrayList<>();
        for (FoodCourt FC : FCList) {
            if (FC.getFoodCourtName().toLowerCase().contains("food club")) {
                FCfilteredList.add(FC);
            }
        }
        Log.d("size ", "" + FCList.size());

        FoodCourt FC = FCfilteredList.get(position);

        String stall_name = FC.getstallname();
        String stall_descr = FC.getstalldescription();

        //for image
        String name = FCfilteredList.get(position).toString().replace(" ","_").toLowerCase();
        String parse_img_name = name + "_foodclub";
        // need to name it like : 'indonesian_foodclub'

        int drawable = getDrawable(holder.STLimg.getContext(), parse_img_name);
        holder.STLimg.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + drawable));

        //using data to set text
        holder.STLname.setText(stall_name);
        holder.STLdescr.setText(stall_descr);
    }

    public static int getDrawable (Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    private final int fdcblimit = 7;

    //return size of array list
    public int getItemCount() {
        if (FCList.size() == fdcblimit){
            return FCList.size();
        }
        else
        {
            return fdcblimit;
        }

//    public class FCViewHolder extends RecyclerView.ViewHolder{
//        TextView STLname, STLdescr;
//
//        public FCViewHolder(@NonNull View itemView){
//            super(itemView);
//            STLname = itemView.findViewById(R.id.STLname);
//            STLdescr = itemView.findViewById(R.id.STLdescr);
//        }
//    }
    }
}

