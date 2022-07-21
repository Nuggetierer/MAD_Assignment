package sg.edu.np.mad.mad_assignment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//functions to manage posts in the firebase database
public class PostsDBHandler {
    //initialising
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("https://mad-assg2-default-rtdb.asia-southeast1.firebasedatabase.app/");

    //takes post information and upload to firebase
    public void upload(Posts post){
        String post_type = post.getType();

        db.child(post_type).setValue(post);
    }

    //read posts into arraylist of post (bad idea for when there is more data)
    public ArrayList<Posts> getPosts(){
        ArrayList<Posts> postList = new ArrayList<Posts>();

        return postList;
    }
}
