package sg.edu.np.mad.mad_assignment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myResultViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView blk_num;
    TextView sch;
    Button goToDetail;
    //Block blk_info; use class to parse information into details activity

    public myResultViewHolder(@NonNull View itemView){
        super(itemView);

        //assigning textview locations
        title = itemView.findViewById(R.id.blockName);
        blk_num = itemView.findViewById(R.id.blkNum);
        sch = itemView.findViewById(R.id.blkSchool);

        //button to go to details need to be created (parse block data)
        goToDetail = itemView.findViewById(R.id.goToDetail);
    }
}
