package com.akpp.baba_api_prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class BabaServiceImpl implements BabaService {

    private BabaRepository babaRepo;

    @Autowired
    public BabaServiceImpl(BabaRepository babaRepo) {
        this.babaRepo = babaRepo;
    }

    @Override
    public List<Baba> findAll() {
        return babaRepo.findAll();
    }

    @Override
    public Baba findOne(UUID id) {
        return babaRepo.findById(id)
                .orElseThrow(() -> new BabaNotFoundException("No baba with such id exists"));
    }

    @Override
    public Baba save(Baba baba) {
        return babaRepo.save(baba);
    }

    @Override
    @Transactional
    public Baba update(UUID id, Baba newBaba) {
        Baba baba = babaRepo.findById(id)
                .orElseThrow(() -> new BabaNotFoundException("No baba with such id exists"));
        baba.setFirstName(newBaba.getFirstName());
        baba.setLastName(newBaba.getLastName());
        baba.setAge(newBaba.getAge());
        baba.setPictureUrl(newBaba.getPictureUrl());
        return babaRepo.save(baba);
    }

    @Override
    public void delete(UUID id) {
        babaRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Baba vote(UUID id, BabaVote vote) {
        Baba baba = babaRepo.findById(id)
                .orElseThrow(() -> new BabaNotFoundException("No baba with such id exists"));
        baba.addVote(vote);
        if (baba.getVotes().size() > 3) {
            double totalScore = baba.getLooks() + baba.getPersonality();
            if (totalScore > 15) {
                baba.setType(BabaType.BABA_BOMBA);
            } else if (totalScore > 8) {
                baba.setType(BabaType.BABA_KAK_BABA);
            } else {
                baba.setType(BabaType.NE_BABA);
            }
        }
        return babaRepo.save(baba);
    }
}
