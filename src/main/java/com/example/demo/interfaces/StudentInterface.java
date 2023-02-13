package com.example.demo.interfaces;

import com.example.demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentInterface extends JpaRepository<StudentEntity, Integer> {

	// TODO:- CREATE CUSTOM QUERY FOR FINDING SECTION,SCHOOL NAME.
	List<StudentEntity> findByName(String name);

	List<StudentEntity> findBySection(String section);

	List<StudentEntity> findBySchoolName(String schoolName);

	@Query(value = "select * from Student_info s where s.name=:name", nativeQuery = true)
	List<StudentEntity> getName(String name);

	@Query(value = "select * from Student_info s where s.section=:section", nativeQuery = true)
	List<StudentEntity> getSection(String section);

	@Query(value = "update Student_info s set s.name='nitesh' where s.section=:section", nativeQuery = true)
	Optional<StudentEntity> updateName(String section);

}
