package com.example.demo.interfaces;

import com.example.demo.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInterface extends JpaRepository<StudentEntity, Integer> {

    List<StudentEntity> findByName(String name);

    List<StudentEntity> findBySection(String section);

    List<StudentEntity> findBySchoolName(String schoolName);

    @Query(value = "select * from Student_info s where s.name=:name", nativeQuery = true)
    List<StudentEntity> getName(String name);

    @Query(value = "select * from Student_info s where s.section=:section", nativeQuery = true)
    List<StudentEntity> getSection(String section);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Student_info s SET s.name=:name, s.section=:section WHERE s.id = :id",nativeQuery = true)
    int updateName(String name , String section ,int id );

}