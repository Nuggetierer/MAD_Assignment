package sg.edu.np.mad.mad_assignment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventResultViewHolder extends RecyclerView.ViewHolder {
    TextView etxt1;
    TextView etxt2;

    public EventResultViewHolder(View itemView) {
        super(itemView);
        etxt1 = itemView.findViewById(R.id.EventTextView_Top);
        etxt2 = itemView.findViewById(R.id.EventTextView_Bottom);
    }
}
