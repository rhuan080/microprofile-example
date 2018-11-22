package net.rhuanrocha.speakers;

import org.bson.types.ObjectId;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SpeakerService {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private SpeakerRepository repository;

    public List<Speaker> findAll(){

        return repository.findAll();

    }

    public Speaker create (Speaker speaker){

        if( Objects.nonNull(speaker.getId()) ){
            throw new IllegalArgumentException("Speaker should not have id to be created");
        }

        speaker.setId(generateId());
        return repository.save(speaker);

    }

    public Speaker findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Speaker> findByName(String name){
       return repository.findByName(name);
    }

    private String generateId(){

        return "S" + new Date().getTime() + new Random().nextLong();

    }
}
