package sg.edu.np.mad.mad_assignment.ui.Food.FoodClub;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class FCViewHolder extends RecyclerView.ViewHolder{
//    TextView FCTname;
//    TextView FCTloca;
    TextView STLname;
    TextView STLdescr;
    ImageView STLimg;

    public FCViewHolder(View itemView) {
        super(itemView);
//        FCTname = itemView.findViewById(R.id.#haventdoyet);
//        FCTloca = itemView.findViewById(R.id.#haventdoyet);
        STLname = itemView.findViewById(R.id.STLname);
        STLdescr = itemView.findViewById(R.id.STLdescr);
        STLimg = itemView.findViewById(R.id.STLimg);
    }
}
