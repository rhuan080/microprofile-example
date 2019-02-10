package net.rhuanrocha.subjects;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Subject {

    @Id("_id")
    private String id;

    @Column
    @NotNull(message="{subject.title.notnull")
    private String title;

    @Column
    @NotNull(message="{subject.title.notnull}")
    private Date date;

    @Column
    @NotNull(message="{subject.hour.notnull}")
    @Max(value = 23, message = "{subject.hour.max}")
    @Min(value = 0, message = "{subject.hour.min}")
    private String hour;

    @Column
    @NotNull(message="{subject.minute.notnull}")
    @Max(value = 23, message = "{subject.minute.max}")
    @Min(value = 0, message = "{subject.minute.min}")
    private String minute;

    @Column
    @NotNull(message="{subject.speaker.notnull}")
    private String idSpeaker;

    @Column
    private String description;


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

    public String getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(String idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
