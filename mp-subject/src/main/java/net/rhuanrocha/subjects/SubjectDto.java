package net.rhuanrocha.subjects;

import net.rhuanrocha.speakers.Speaker;

import java.util.Date;

public class SubjectDto {

    private String id;

    private String title;

    private Date date;

    private String hour;

    private String minute;

    private String description;

    private Speaker speaker;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public static SubjectDto build(Subject subject){

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setDate(subject.getDate());
        subjectDto.setDescription(subject.getDescription());
        subjectDto.setHour(subject.getHour());

        subjectDto.setId(subject.getId());
        subjectDto.setMinute(subject.getMinute());
        subjectDto.setTitle(subject.getTitle());

        return subjectDto;
    }

    public static SubjectDto build(Subject subject, Speaker speaker){

        SubjectDto subjectDto = build(subject);
        subjectDto.setSpeaker( speaker );

        return subjectDto;
    }
}
