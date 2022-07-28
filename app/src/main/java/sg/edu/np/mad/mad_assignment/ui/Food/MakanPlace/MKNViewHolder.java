package sg.edu.np.mad.mad_assignment.ui.Food.MakanPlace;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class MKNViewHolder extends RecyclerView.ViewHolder{

    TextView MknSTLname;
    TextView MknSTLdescr;
    ImageView MknSTLimg;

    public MKNViewHolder(@NonNull View itemView) {
        super(itemView);

        MknSTLname = itemView.findViewById(R.id.MknSTLname);
        MknSTLdescr = itemView.findViewById(R.id.MknSTLdescr);
        MknSTLimg = itemView.findViewById(R.id.MknSTLimg);
    }
}