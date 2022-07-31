package sg.edu.np.mad.mad_assignment.ui.Study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import sg.edu.np.mad.mad_assignment.R;

public class ImportImages extends AppCompatActivity {

    private Button upload;
    private ImageView uploadimage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_images);

        upload = findViewById(R.id.UploadImagesbutton);
        uploadimage = findViewById(R.id.AddimageView2);
        progressBar = findViewById(R.id.AddImageprogressBar);
    }
}