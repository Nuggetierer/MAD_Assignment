package sg.edu.np.mad.mad_assignment.ui.Map;

import androidx.appcompat.app.AppCompatActivity;

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

import sg.edu.np.mad.mad_assignment.R;

public class BlockDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_details);

        Intent receivingEnd = getIntent();
        String BlockName = receivingEnd.getStringExtra("Block Name");
        String BlockNumber = receivingEnd.getStringExtra("Block Number");
        String BlockDescription = receivingEnd.getStringExtra("Block Description");

        TextView BlkName = findViewById(R.id.Name);
        TextView BlkNo = findViewById(R.id.blockNo);
        TextView BlkDescr = findViewById(R.id.blockDescr);

        BlkName.setText(BlockName);
        BlkNo.setText(BlockNumber);
        BlkDescr.setText(BlockDescription);

        ImageView blkIMG = findViewById(R.id.blkImage);
        blkIMG.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.cat));
        // cat = file name of photo
    }
}