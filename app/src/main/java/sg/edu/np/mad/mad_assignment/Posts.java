package sg.edu.np.mad.mad_assignment;

public class Posts {
    private String type;
    private String caption;
    private Integer review_stars;
    //private (image value type) img_pointer;

    public Posts(){};

    public String getType() {
        return type;
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
