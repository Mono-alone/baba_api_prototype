package com.akpp.baba_api_prototype;

import java.util.List;
import java.util.UUID;


public interface BabaService {

    List<Baba> findAll();

    Baba findOne(UUID id);

    Baba save(Baba baba);

    Baba update(UUID id, Baba newBaba);

    void delete(UUID id);

    Baba vote(UUID id, BabaVote vote);
}
