package net.rhuanrocha.speakers;

import org.bson.types.ObjectId;
import org.jnosql.artemis.Repository;

import java.util.List;

public interface SpeakerRepository extends Repository<Speaker, String> {

    List<Speaker> findAll();

    List<Speaker> findByName(String name);


}
