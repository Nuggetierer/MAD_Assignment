package sg.edu.np.mad.mad_assignment;

public class Event {
    private String eventName;
    private String eventDescription;
    private String eventType;

    public Event(){}

    public Event(String e_name, String e_desc, String event_type)
    {
        eventName = e_name;
        eventDescription = e_desc;
        eventType = event_type;
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
