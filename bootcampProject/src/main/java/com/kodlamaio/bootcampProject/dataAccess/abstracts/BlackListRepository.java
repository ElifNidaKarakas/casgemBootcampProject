package com.kodlamaio.bootcampProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Integer> {
BlackList getById(int id);
BlackList getByApplicantId(int id);
}
