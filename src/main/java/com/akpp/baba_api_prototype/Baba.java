package com.akpp.baba_api_prototype;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
public class Baba {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Positive(message = "Age cannot be negative")
    private int age;

    private String pictureUrl;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<BabaVote> babaVotes = new ArrayList<>();

    private BabaType type;

    public Baba() {
    }

    public Baba(String firstName, String lastName, int age, String pictureUrl, BabaType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.pictureUrl = pictureUrl;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getPersonality() {
        return babaVotes.stream()
                .mapToDouble(BabaVote::getPersonality)
                .average()
                .orElse(0.0);
    }

    public double getLooks() {
        return babaVotes.stream()
                .mapToDouble(BabaVote::getLooks)
                .average()
                .orElse(0.0);
    }

    public BabaType getType() {
        return type;
    }

    public List<BabaVote> getVotes() {
        return babaVotes;
    }

    public void setVotes(List<BabaVote> babaVotes) {
        this.babaVotes = babaVotes;
    }

    public void addVote(BabaVote babaVote) {
        babaVotes.add(babaVote);
    }

    public void setType(BabaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Baba{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", picture=" + pictureUrl +
                ", type=" + type +
                '}';
    }
}
