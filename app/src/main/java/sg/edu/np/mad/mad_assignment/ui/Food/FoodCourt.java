package sg.edu.np.mad.mad_assignment.ui.Food;

public class FoodCourt {
    private String FoodCourtName;
    private String fctLocation;
    private String stallname;
    private String stalldescription;

    public FoodCourt(){}

    public FoodCourt(String fct_name, String fct_loca, String stall_name, String stall_descr)
    {
        FoodCourtName = fct_name;
        fctLocation = fct_loca;
        stallname = stall_name;
        stalldescription = stall_descr;
    }

    public String getFoodCourtName() {
        return FoodCourtName;
    }

    public void setFoodCourtName(String fct_name) {
        FoodCourtName = fct_name;
    }

    public String getfctLocation() {
        return fctLocation;
    }

    public void setfctLocation(String fct_loca) { fctLocation = fct_loca; }

    public String getstallname() {
        return stallname;
    }

    public void setstallname(String stall_name) {
        stallname = stall_name;
    }

    public String getstalldescription() {
        return stalldescription;
    }

    public void setstalldescription(String stall_descr) {
        stalldescription = stall_descr;
    }
}

