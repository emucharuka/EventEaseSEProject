public class MusicEvent extends Event {
    public MusicEvent(String id, String name, String location) {
        super(id, name, "Music", location);
    }
}
class ConferenceEvent extends Event {
    public ConferenceEvent(String id, String name, String location) {
        super(id, name, "Conference", location);
    }
}

class OtherEvent extends Event {
    public OtherEvent(String id, String name, String customType, String location) {
        super(id, name, customType, location);
    }
}

