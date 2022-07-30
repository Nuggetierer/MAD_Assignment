package sg.edu.np.mad.mad_assignment.ui.Study;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_assignment.R;

public class StudyViewHolder extends RecyclerView.ViewHolder {
    TextView stxt1, options;
    Button viewmore;
    ImageView simage;
    ConstraintLayout detailslayout;

    public StudyViewHolder(View itemView) {
        super(itemView);
        stxt1 = itemView.findViewById(R.id.StudyTextView);
        viewmore = itemView.findViewById(R.id.StudyButton);
        simage = itemView.findViewById(R.id.StudyimageView);
        detailslayout = itemView.findViewById(R.id.StudyDetailsLayout);
        options = itemView.findViewById(R.id.txt_option);
    }
}
