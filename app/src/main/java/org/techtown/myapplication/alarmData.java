package org.techtown.myapplication;

public class alarmData {
    private String timeText;
    private String medicineText;
    private String starttimeText;
    private String endtimeText;

    public alarmData(String timeText, String medicineText, String starttimeText, String endtimeText) {
        this.timeText = timeText;
        this.medicineText = medicineText;
        this.starttimeText = starttimeText;
        this.endtimeText = endtimeText;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getMedicineText() {
        return medicineText;
    }

    public void setMedicineText(String medicineText) {
        this.medicineText = medicineText;
    }

    public String getStarttimeText() {
        return starttimeText;
    }

    public void setStarttimeText(String starttimeText) {
        this.starttimeText = starttimeText;
    }

    public String getEndtimeText() {
        return endtimeText;
    }

    public void setEndtimeText(String endtimeText) {
        this.endtimeText = endtimeText;
    }
}
