public class AgendaItem {
    private String time;
    private String activity;

    public AgendaItem(String Time, String activity){
        this.time = time;
        this.activity = activity;
    }

    public String getTime(){
        return time;
    }
    public String getActivity(){
        return activity;
    }
}
