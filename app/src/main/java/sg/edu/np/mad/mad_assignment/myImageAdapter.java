package sg.edu.np.mad.mad_assignment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myImageAdapter extends RecyclerView.Adapter<myImageViewHolder> {
    ArrayList<String> data;

    public myImageAdapter(ArrayList<String> input) {
        data = input;
    }

    public myImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.blkimg_layout, parent, false);
        return new myImageViewHolder(item);
    }

    public void onBindViewHolder(myImageViewHolder holder, int position){
        String imgName = data.get(position);

        String img_name = imgName.replace(" ", "_").toLowerCase();
        img_name = imgName + "_map";

        int drawable = getDrawable(holder.img.getContext(), img_name);
        holder.img.setImageURI(Uri.parse("android.resource://" + holder.img.getContext() + "/" + drawable));
    }

    public static int getDrawable (Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public int getItemCount(){
        return data.size();
    }
}
