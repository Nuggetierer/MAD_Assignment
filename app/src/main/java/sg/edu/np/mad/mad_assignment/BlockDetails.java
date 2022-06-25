package sg.edu.np.mad.mad_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import sg.edu.np.mad.mad_assignment.R;

public class BlockDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_details);

        Block blk_info = new Block();
        Intent receivingEnd = getIntent();

        blk_info = (Block) receivingEnd.getSerializableExtra("block_info");

        String BlockName = blk_info.getName();
        String BlockNumber = String.valueOf(blk_info.getBlockNo());
        String BlockDescription = blk_info.getDescription();

        TextView BlkName = findViewById(R.id.Name);
        TextView BlkNo = findViewById(R.id.blockNo);
        TextView BlkDescr = findViewById(R.id.blockDescr);

        BlkName.setText(BlockName);
        BlkNo.setText(BlockNumber);
        BlkDescr.setText(BlockDescription);

//        RecyclerView name = findViewById(R.id.recyclerVIEW);
//        name.setLayoutManager(new LinearLayoutManager(this));
//        String imgname_1 = blk_info.getName();
//        String imgname_2 = imgname_1 + "_pic";
//
//        ArrayList<String> data = new ArrayList<>();
//        data.add(imgname_1);
//        data.add(imgname_2);
//
//        myImageAdapter imgAdp = new myImageAdapter(data);
//        name.setAdapter(imgAdp);

        //Image of block location
        ImageView blkIMG = findViewById(R.id.blockMap);

        String blk_name = BlockName.replace(" ", "_").toLowerCase();

        String img_name = blk_name + "_map";

        int drawable = getDrawable(this, img_name);

        blkIMG.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + drawable));

    }

    public static int getDrawable (Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}