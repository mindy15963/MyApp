package org.techtown.myapplication;

public class alarmData {
    private String ampmText;
    private String timeText;
    private String medText;
    private String dayText;

    public alarmData(String ampmText, String timeText, String medText, String dayText) {
        this.ampmText = ampmText;
        this.timeText = timeText;
        this.medText = medText;
        this.dayText = dayText;
    }

    public String getampmText() {
        return ampmText;
    }

    public void setampmText(String ampmText) {
        this.ampmText = ampmText;
    }

    public String gettimeText() {
        return timeText;
    }

    public void settimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getmedText() {
        return medText;
    }

    public void setmedText(String medText) {
        this.medText = medText;
    }

    public String getdayText() {
        return dayText;
    }

    public void setdayText(String dayText) {
        this.dayText = dayText;
    }
}
