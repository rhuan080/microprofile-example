package net.rhuanrocha.speakers;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class  Speaker {

    @Id("_id")
    private String id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return Objects.equals(id, speaker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Speaker build(String name, String description){
        Speaker speaker = new Speaker();

        speaker.setName(name);
        speaker.setDescription(description);

        return speaker;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Speaker{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
