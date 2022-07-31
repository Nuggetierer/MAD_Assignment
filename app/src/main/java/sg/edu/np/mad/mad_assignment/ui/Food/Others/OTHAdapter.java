package sg.edu.np.mad.mad_assignment.ui.Food.Others;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.Block;
import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodClub.FCViewHolder;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;

public class OTHAdapter extends RecyclerView.Adapter<OTHViewHolder> {

    ArrayList<FoodCourt> othList;

    public OTHAdapter(ArrayList<FoodCourt> input) {this.othList = input;}

    @Override
    @NonNull
    public OTHViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.othstall_recyclerview, parent, false);
        return new OTHViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull OTHViewHolder holder, int position) {
        //set data
        ArrayList<FoodCourt> OTHfilteredList = new ArrayList<>();
        for (FoodCourt other : othList) {
            if (other.getFoodCourtName().toLowerCase().contains("others")) {
                OTHfilteredList.add(other);
            }
        }
        FoodCourt other = OTHfilteredList.get(position);

        String stall_name = other.getstallname();
        String stall_descr = other.getstalldescription();
        String stall_loca = other.getfctLocation();

        //for image
        String name = stall_name.replace(" ","_").toLowerCase();
        String parse_img_name = name + "_other";
        // need to name it like : 'old_chang_kee_other'

        int drawable = getDrawable(holder.OTHimg.getContext(), parse_img_name);
        holder.OTHimg.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + drawable));

        //using data to set text
        holder.OTHname.setText(stall_name);
        holder.OTHdescr.setText(stall_descr);
        holder.OTHloca.setText(stall_loca);
    }

    public static int getDrawable (Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    private final int othlimit = 5;

    //return size of array list
    public int getItemCount() {
        if (othList.size() == othlimit){
            return othList.size();
        }
        else
        {
            return othlimit;
        }
    }
}
