package sg.edu.np.mad.mad_assignment.ui.Study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class StudyDetailsAdapter extends RecyclerView.Adapter<StudyDetailsAdapter.MyHolder> {
    int[] data;
    Context context;

    public StudyDetailsAdapter(int[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_details_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.sdimage.setImageResource(R.drawable.atrium_map);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView sdimage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
           sdimage = itemView.findViewById(R.id.blkimgView);
        }
    }

}
