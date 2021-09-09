package com.akpp.baba_api_prototype;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
public class BabaVote {

    @Id
    @GeneratedValue
    public long id;

    @Min(value = 0, message = "Looks can be rated from 0 to 10")
    @Max(value = 10, message = "Looks can be rated from 0 to 10")
    private int looks;

    @Min(value = 0, message = "Personalty can be rated from 0 to 10")
    @Max(value = 10, message = "Personality can be rated from 0 to 10")
    private int personality;

    public BabaVote() {
    }

    public BabaVote(int looks, int personality) {
        this.looks = looks;
        this.personality = personality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLooks() {
        return looks;
    }

    public void setLooks(int looks) {
        this.looks = looks;
    }

    public int getPersonality() {
        return personality;
    }

    public void setPersonality(int personality) {
        this.personality = personality;
    }

    @Override
    public String toString() {
        return "BabaVote{" +
                "id=" + id +
                ", looks=" + looks +
                ", personality=" + personality +
                '}';
    }
}
