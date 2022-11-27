package com.kodlamaio.bootcampProject.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.applications.Application;
import com.kodlamaio.bootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="bootcamps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bootcamp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dateStart")
	private LocalDate dateStart;
	
	@Column(name="dateEnd")
	private LocalDate dateEnd;
	
	@Column(name="state")
	private String state;
	
	@OneToMany(mappedBy = "bootcamp")
	private List<Application> application;
	
	@ManyToOne
    @JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	

}
