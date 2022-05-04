package com.abhijith.gymapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="client")
public class Client {
	
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

	@Column(name="contact")
	@NotEmpty(message = "contact is required")
	@Pattern(regexp = "^[0-9]{10}",message = "Enter valid contact with 10 characters")
	private String contact;

	@Column(name="email")
	@NotEmpty(message = "contact is required")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Enter valid email")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name = "trainer_client",
			joinColumns = @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "trainer_id")
	)
	@NotEmpty(message = "select at least one trainer")
	private List<Trainer> trainers;
	
	public Client() {
		
	}
	
	public Client(int id, String firstName, String lastName, String email, String contact) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact=contact;
	}


	public Client(String firstName, String lastName, String email, String contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact=contact;
	}

	// define getter/setter

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

	public List<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(List<Trainer> trainers) {
		this.trainers = trainers;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", contact=" + contact +
				", email='" + email + '\'' +
				'}';
	}
}
