package e.dogbi.boardgame;

public class Party {

    private String event;
    private String date;
    private String durration;
    private String games[];
    private String names[];
    private String phone[];

    public  Party(String e, String d, String dur,String g[],String n[],String p[]){
        event =e;
        date =d;
        durration = dur;
        games = g;
        names = n;
        phone =p;
    }


    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

    public String getDurration() {
        return durration;
    }

    public String[] getGames() {
        return games;
    }

    public String[] getNames() {
        return names;
    }

    public String[] getPhone() {
        return phone;
    }



    public void setEvent(String event) {
        this.event = event;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDurration(String durration) {
        this.durration = durration;
    }

    public void setGames(String[] games) {
        this.games = games;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

}
