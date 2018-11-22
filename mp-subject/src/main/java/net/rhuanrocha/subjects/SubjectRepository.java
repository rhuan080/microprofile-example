package net.rhuanrocha.subjects;

import org.jnosql.artemis.Repository;

import java.util.List;

public interface SubjectRepository extends Repository<Subject, String> {

    List<Subject> findAll();

    List<Subject> findByIdSpeaker(String idSpeaker);

}
