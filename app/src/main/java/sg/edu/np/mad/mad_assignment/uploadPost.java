package sg.edu.np.mad.mad_assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class uploadPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_upload);

        //Initialising
        TextView postName = findViewById(R.id.postAddTitle);
        TextView postCaption = findViewById(R.id.postAddCaption);
        //ImageView postImage = findViewById(R.id.something); not sure how upload images work

        Button submitButton = findViewById(R.id.postUploadButton);
        Button cancelButton = findViewById(R.id.postCancelButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostsDBHandler db = new PostsDBHandler();

                Posts newPost = new Posts();

                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                String posterID = prefs.getString("uid", "");

                String postNameSub = postName.getText().toString();
                String postCaptionSub = postCaption.getText().toString();

                newPost.setTitle(postNameSub);
                newPost.setCaption(postCaptionSub);
                newPost.setType("post");
                newPost.setPosterID(posterID);

                db.upload(newPost);

                //return to somewhere
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go somewhere
            }
        });
    }
}
