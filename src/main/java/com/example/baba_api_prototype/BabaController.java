package com.example.baba_api_prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("baba")
public class BabaController {

    private BabaRepository babaRepo;

    @Autowired
    public BabaController(BabaRepository babaRepo) {
        this.babaRepo = babaRepo;
    }

    @GetMapping
    public List<Baba> findAll() {
        return babaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Baba findOne(@PathVariable("id") UUID id) {
        return babaRepo.findById(id).orElseThrow();
    }

    @PostMapping("/{babaId}/vote")
    public Baba vote(@PathVariable("babaId") UUID babaId, @RequestBody BabaVote babaVote) {
        Baba baba = babaRepo.findById(babaId).orElseThrow();
        baba.addVote(babaVote);
        if (baba.getVotes().size() > 3) {
            baba.setType(BabaType.BABA_BOMBA);
        }
        return babaRepo.save(baba);
    }

    @PostMapping
    public Baba addBaba(@RequestBody Baba baba) {
        return babaRepo.save(baba);
    }
}
