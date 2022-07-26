package sg.edu.np.mad.mad_assignment.ui.Study;

import android.graphics.drawable.Drawable;

public class StudyPlaces {
    private String studyName;
    private String studyDescription;
    private String studyLocation;//Like block 22 #05-01
    private String studycoordinates;// Set incase needed for map pathing
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

    public StudyPlaces(String studyName, String studyDescription, String studyLocation, String studycoordinates) {
        this.studyName = studyName;
        this.studyDescription = studyDescription;
        this.studyLocation = studyLocation;
        this.studycoordinates = studycoordinates;
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

    public String getStudycoordinates() {
        return studycoordinates;
    }

    public void setStudycoordinates(String studycoordinates) {
        this.studycoordinates = studycoordinates;
    }
}
