package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class FCViewHolder extends RecyclerView.ViewHolder{

    TextView STLname;
    TextView STLdescr;
    ImageView STLimg;

    public FCViewHolder(@NonNull View itemView) {
        super(itemView);

        STLname = itemView.findViewById(R.id.STLname);
        STLdescr = itemView.findViewById(R.id.STLdescr);
        STLimg = itemView.findViewById(R.id.STLimg);
    }
}
