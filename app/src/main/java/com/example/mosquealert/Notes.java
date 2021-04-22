package com.example.mosquealert;

public class Notes {
    int id;
    String waktoName ,startTime,entTime,StartTimeMilli,endTimeMilli,audioMode;
    int switchStatus;

    public Notes() {
    }

    public Notes(int id, String waktoName, String startTime, String entTime, String startTimeMilli, String endTimeMilli, String audioMode, int switchStatus) {
        this.id = id;
        this.waktoName = waktoName;
        this.startTime = startTime;
        this.entTime = entTime;
        StartTimeMilli = startTimeMilli;
        this.endTimeMilli = endTimeMilli;
        this.audioMode = audioMode;
        this.switchStatus = switchStatus;
    }

    public Notes(String waktoName, String startTime, String entTime, String startTimeMilli, String endTimeMilli, String audioMode, int switchStatus) {
        this.waktoName = waktoName;
        this.startTime = startTime;
        this.entTime = entTime;
        StartTimeMilli = startTimeMilli;
        this.endTimeMilli = endTimeMilli;
        this.audioMode = audioMode;
        this.switchStatus = switchStatus;
    }

    public String getWaktoName() {
        return waktoName;
    }

    public void setWaktoName(String waktoName) {
        this.waktoName = waktoName;
    }

    public String getAudioMode() {
        return audioMode;
    }

    public void setAudioMode(String audioMode) {
        this.audioMode = audioMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEntTime() {
        return entTime;
    }

    public void setEntTime(String entTime) {
        this.entTime = entTime;
    }

    public String getStartTimeMilli() {
        return StartTimeMilli;
    }

    public void setStartTimeMilli(String startTimeMilli) {
        StartTimeMilli = startTimeMilli;
    }

    public String getEndTimeMilli() {
        return endTimeMilli;
    }

    public void setEndTimeMilli(String endTimeMilli) {
        this.endTimeMilli = endTimeMilli;
    }



    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }
}
