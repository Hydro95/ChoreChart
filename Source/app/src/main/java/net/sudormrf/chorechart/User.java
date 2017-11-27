package net.sudormrf.chorechart;

import java.io.Serializable;

/**
 * Created by Josh on 2017-11-24.
 */

public class User {

    private String name;
    private int points;
    private int icon;

    public User(){
        this.name = "Anon";
        this.points = 0;
        this.icon = R.drawable.ic_logo_empty;
    }

    public User(String name, int icon) {
        this.name = name;
        this.icon = icon;

        this.points = 0;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public int getPoints() {
        return this.points;
    }
    public void setPoints(int newPoints) {
        this.points = newPoints;
    }

    public int getIcon() {
        return this.icon;
    }
    public void setIcon(int newIcon) {
        this.icon = newIcon;
    }


}
