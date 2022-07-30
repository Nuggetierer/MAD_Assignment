package sg.edu.np.mad.mad_assignment.ui.Study;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class StudyPlaces implements Serializable {

    @Exclude
    private String key;
    private String studyName;
    private String studyDescription;
    private String studyLocation;//Like block 22 #05-01
    private Drawable drawable;


    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public StudyPlaces(Drawable drawable) {
        this.drawable = drawable;
    }

    public StudyPlaces(){}

    public StudyPlaces(String studyName, String studyDescription, String studyLocation) {
        this.studyName = studyName;
        this.studyDescription = studyDescription;
        this.studyLocation = studyLocation;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public String getStudyLocation() {
        return studyLocation;
    }

    public void setStudyLocation(String studyLocation) {
        this.studyLocation = studyLocation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
