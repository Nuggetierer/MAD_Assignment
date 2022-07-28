package sg.edu.np.mad.mad_assignment.ui.Food.MakanPlace;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.ui.Food.FoodCourt;

public class MKNAdapter extends RecyclerView.Adapter<MKNViewHolder> {

    ArrayList<FoodCourt> MknList;

    public MKNAdapter(ArrayList<FoodCourt> input) {
        this.MknList = input;
    }

    @Override
    @NonNull
    public MKNViewHolder onCreateViewHolder(ViewGroup parent, int view) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.mknstall_recyclerview, parent, false);
        return new MKNViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MKNViewHolder holder, int position) {
        //set data
        ArrayList<FoodCourt> MknfilteredList = new ArrayList<>();
        for (FoodCourt Mkn : MknList) {
            if (Mkn.getFoodCourtName().toLowerCase().contains("makan place")) {
                MknfilteredList.add(Mkn);
            }
        }

        FoodCourt Mkn = MknfilteredList.get(position);

        String stall_name = Mkn.getstallname();
        String stall_descr = Mkn.getstalldescription();

        //for image
        String name = MknfilteredList.get(position).toString().replace(" ", "_").toLowerCase();
        String parse_img_name = name + "_makanplace";
        // need to name it like : 'indonesian_makanplace'

        int drawable = getDrawable(holder.MknSTLimg.getContext(), parse_img_name);
        holder.MknSTLimg.setImageURI(Uri.parse("android.resource://" + MainActivity.PACKAGENAME + "/" + drawable));

        //using data to set text
        holder.MknSTLname.setText(stall_name);
        holder.MknSTLdescr.setText(stall_descr);
    }

    public static int getDrawable(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    //return size of array list
    public int getItemCount() {
        return MknList.size();
    }
}
