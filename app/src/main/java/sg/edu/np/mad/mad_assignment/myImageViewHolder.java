package sg.edu.np.mad.mad_assignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myImageViewHolder extends RecyclerView.ViewHolder{
    ImageView img;

    public myImageViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.blkimgView);
    }
}
