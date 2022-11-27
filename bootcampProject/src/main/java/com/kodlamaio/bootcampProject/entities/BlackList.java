package com.kodlamaio.bootcampProject.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.applications.Application;
import com.kodlamaio.bootcampProject.entities.users.Applicant;
import com.kodlamaio.bootcampProject.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="blacklists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="dateAdded")
	private LocalDate dateAdded;
	@Column(name="reason")
	private String reason;
	@ManyToOne
	@JoinColumn(name="applicant_id")
    private Applicant applicant;
}
