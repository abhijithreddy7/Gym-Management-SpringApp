package com.abhijith.gymapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    @NotEmpty(message = "firstName is required")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Enter valid name")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message = "lastName is required")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Enter valid name")
    private String lastName;

    @Column(name="trainer_name")
    @NotEmpty(message = "trainer_name is required")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Enter valid unique name")
    private String trainerName;

    @Column(name="contact")
    @NotEmpty(message = "contact is required")
    @Pattern(regexp = "^[0-9]{10}",message = "Enter valid contact with 10 characters")
    private String contact;

    @Column(name="email")
    @NotEmpty(message = "email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Enter valid email")
    private String email;

    @Column(name="experience")
    @NotEmpty(message = "experience is required")
    @Pattern(regexp = "^[0-9]",message = "Enter valid experience in years")
    private String experience;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "trainer_client",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String trainerName, String contact, String email, String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainerName = trainerName;
        this.contact = contact;
        this.email = email;
        this.experience = experience;
    }

    public Trainer(int id, String firstName, String lastName, String trainerName, String contact, String email, String experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainerName = trainerName;
        this.contact = contact;
        this.email = email;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
