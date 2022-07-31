package sg.edu.np.mad.mad_assignment.ui.Study;

import com.google.firebase.database.Exclude;

public class Images {

    @Exclude
    private String key;
    private String Uri;

    public Images(){};

    public Images(String Uri) {
        this.Uri = Uri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String Uri) {
        this.Uri = Uri;
    }
}
