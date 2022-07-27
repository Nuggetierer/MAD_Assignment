package sg.edu.np.mad.mad_assignment.ui.Food.Others;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class OTHViewHolder extends RecyclerView.ViewHolder{

    TextView OTHname;
    TextView OTHdescr;
    TextView OTHloca;
    ImageView OTHimg;

    public OTHViewHolder(@NonNull View itemView) {
        super(itemView);

        OTHname = itemView.findViewById(R.id.OTHname);
        OTHdescr = itemView.findViewById(R.id.OTHdescr);
        OTHloca = itemView.findViewById(R.id.OTHloca);
        OTHimg = itemView.findViewById(R.id.OTHimg);
    }
}
