package com.kodlamaio.bootcampProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.applications.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
Application findById(int id);
}
