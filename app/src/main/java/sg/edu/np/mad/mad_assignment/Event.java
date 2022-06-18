package sg.edu.np.mad.mad_assignment;

public class Event {
    private String eventName;
    private String eventDescription;

    public Event(){}

    public Event(String e_name, String e_desc)
    {
        eventName = e_name;
        eventDescription = e_desc;
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
}
