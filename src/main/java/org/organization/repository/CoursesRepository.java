package org.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.organization.controller.AllCourseData;

@Repository
public interface CoursesRepository extends JpaRepository<AllCourseData, String>{

}
