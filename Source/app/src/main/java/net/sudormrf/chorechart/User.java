package net.sudormrf.chorechart;

/**
 * Created by Josh on 2017-11-24.
 */

public class User {

    public String name;
    public int points;
    public int icon;

    public User(){
        this.name = "Anon";
        this.icon = R.drawable.ic_logo_empty;
        this.points = 0;
    }

    public User(String name, int icon) {
        this.name = name;
        this.icon = icon;

        this.points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
