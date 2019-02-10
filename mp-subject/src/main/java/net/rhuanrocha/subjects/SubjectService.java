package net.rhuanrocha.subjects;

import net.rhuanrocha.speakers.Speaker;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SubjectService {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private SubjectRepository repository;

    public List<Subject> findAll(){
        return repository.findAll();
    }

    public Subject create (Subject subject){

        if( Objects.nonNull(subject.getId()) ){
            throw new IllegalArgumentException("Subject should not have id to be created");
        }

        subject.setId(generateId());
        return repository.save(subject);

    }

    public List<Subject> findByIdSpeaker(String idSpeaker){
        return repository.findByIdSpeaker( idSpeaker );
    }

    public Subject findById(String id){
        return repository.findById( id ).orElse(null);
    }

    private String generateId(){

        return "SJ" + new Date().getTime() + new Random().nextLong();

    }





}
