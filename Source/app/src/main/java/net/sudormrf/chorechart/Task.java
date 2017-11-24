package net.sudormrf.chorechart;

/**
 * Created by jon on 24/11/17.
 */

public class Task {

    public String name = "Test task";
    public String deadline = "Right now!";
    public int icon = R.drawable.ic_logo_empty;

    Task() {}
    Task(String name, String deadline, int icon) {
        this.name = name;
        this.deadline = deadline;
        this.icon = icon;
        //TODO: Make the icon the profile picture of the allocated user
    }
}
