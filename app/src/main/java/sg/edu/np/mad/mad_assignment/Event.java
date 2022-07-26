package sg.edu.np.mad.mad_assignment;

public class Event {
    private String eventDate;
    private String eventName;
    private String eventDescription;
    private String eventType;
    private String attend;

    public Event(){}

    public Event(String e_date, String e_name, String e_desc, String event_type, String e_attend)
    {
        eventDate = e_date;
        eventName = e_name;
        eventDescription = e_desc;
        eventType = event_type;
        attend = e_attend;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
