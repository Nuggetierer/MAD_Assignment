package sg.edu.np.mad.mad_assignment.ui.Study;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sg.edu.np.mad.mad_assignment.R;

public class StudyDetailsAdapter extends RecyclerView.Adapter<StudyDetailsAdapter.MyHolder> {
    private ArrayList data;
    Context context;

    public StudyDetailsAdapter(ArrayList data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_details_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        int pos = position;
        String uri2 = (String) data.get(position);

        Log.d("ParseUri", "" + uri2);
//        holder.sdimage.setImageURI(Uri.parse(uri2));
        Glide.with(context).load(uri2).into(holder.sdimage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView sdimage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
           sdimage = itemView.findViewById(R.id.SdImageLayout);
        }
    }

}
