package sg.edu.np.mad.mad_assignment;

public class Posts {
    private String type;
    private String title;
    private String caption;
    private Integer review_stars;
    private String posterID;
    //private (image value type) img_pointer;

    /*
    post type types

    1) posts - initial submit of a post
    2) review - child node to a post contains reviews from other users

    */
    public Posts(){};

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getReview_stars() {
        return review_stars;
    }

    public void setReview_stars(Integer review_stars) {
        this.review_stars = review_stars;
    }
}
