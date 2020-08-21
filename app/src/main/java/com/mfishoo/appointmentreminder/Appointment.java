package com.mfishoo.appointmentreminder;

public class Appointment {
    private String name;
    private String type;
    private String monthDate;
    private int dayDate;
    private int yearDate;
    private int hourTime;
    private int minuteTime;
    private String AMorPMTime;

    private Appointment(AppointmentBuilder builder){
        this.name = builder.name;
        this.type = builder.type;
        this.monthDate = builder.monthDate;
        this.dayDate = builder.dayDate;
        this.yearDate = builder.yearDate;
        this.hourTime = builder.hourTime;
        this.minuteTime = builder.minuteTime;
        this.AMorPMTime = builder.AMorPMTime;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public int getDayDate() {
        return dayDate;
    }

    public int getYearDate() {
        return yearDate;
    }

    public int getHourTime() {
        return hourTime;
    }

    public int getMinuteTime() {
        return minuteTime;
    }

    public String getAMorPMTime() {
        return AMorPMTime;
    }

    public static class AppointmentBuilder{
        private String name;
        private String type;
        private String monthDate;
        private int dayDate;
        private int yearDate;
        private int hourTime;
        private int minuteTime;
        private String AMorPMTime;

        public AppointmentBuilder name(String name){
            this.name = name;
            return this;
        }

        public AppointmentBuilder type(String type){
            this.type = type;
            return this;
        }

        public AppointmentBuilder monthDate(String monthDate){
            this.monthDate = monthDate;
            return this;
        }

        public AppointmentBuilder dayDate(int dayDate){
            this.dayDate = dayDate;
            return this;
        }

        public AppointmentBuilder yearDate(int yearDate){
            this.yearDate = yearDate;
            return this;
        }

        public AppointmentBuilder hourTime(int hourTime){
            this.hourTime = hourTime;
            return this;
        }

        public AppointmentBuilder minuteTime(int minuteTime){
            this.minuteTime = minuteTime;
            return this;
        }

        public AppointmentBuilder AMorPMTime(String AMorPMTime){
            this.AMorPMTime = AMorPMTime;
            return this;
        }

        public Appointment build(){
            return new Appointment(this);
        }
    }

}
