package org.techtown.myapplication;

public class alarmData {
    /**
     * 알람 데이터
     * -알람을 저장합니다.
     * @author 유세빈, 김은석, 이하나, 김동권
     */
    private String ampmText;
    private String timeText;
    private String medText;
    private String dayText;

    /**
     * 알람 데이터 할당
     * @param ampmText
     * @param timeText
     * @param medText
     * @param dayText
     */
    public alarmData(String ampmText, String timeText, String medText, String dayText) {
        this.ampmText = ampmText;
        this.timeText = timeText;
        this.medText = medText;
        this.dayText = dayText;
    }

    /**
     * 오전오후 반환
     * @return ampmText
     */
    public String getampmText() {
        return ampmText;
    }

    /**
     * 오전오후 할당
     * @param ampmText
     */
    public void setampmText(String ampmText) {
        this.ampmText = ampmText;
    }

    /**
     * 시간 반환
     * @return timeText
     */
    public String gettimeText() {
        return timeText;
    }

    /**
     * 시간 할당
     * @param timeText
     */
    public void settimeText(String timeText) {
        this.timeText = timeText;
    }

    /**
     * 약 이름 반환
     * @return medText
     */
    public String getmedText() {
        return medText;
    }

    /**
     * 약 이름 할당
     * @param medText
     */
    public void setmedText(String medText) {
        this.medText = medText;
    }

    /**
     * 날짜 반환
     * @return dayText
     */
    public String getdayText() {
        return dayText;
    }

    /**
     * 날짜 할당
     * @param dayText
     */
    public void setdayText(String dayText) {
        this.dayText = dayText;
    }
}
