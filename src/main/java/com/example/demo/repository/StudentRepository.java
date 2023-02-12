package com.example.demo.repository;

import com.example.demo.entity.StudentEntity;
import com.example.demo.interfaces.StudentInterface;
import com.example.demo.response.Response;
import com.example.demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);

    @Autowired
    public StudentInterface studentInterface;

    public Response saveStudentsData(StudentEntity entity) {
        Response response = new Response();
        if (entity != null) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setName(entity.getName());
            studentEntity.setSection(entity.getSection());
            studentEntity.setSchoolName(entity.getSchoolName());
            StudentEntity student = studentInterface.save(studentEntity);
            if (student.getName() != null && student.getName().equals(entity.getName())) {
                response.setMessage("The Data has been Saved with the Id" + " " + student.getID());
                response.setStatusCode(HttpStatus.CREATED);
                response.setStudentEntity(student);
            } else {
                response.setMessage("The Data has Not Been Saved or already Exists");
                response.setStatusCode(HttpStatus.ALREADY_REPORTED);
                response.setStudentEntity(null);
            }
            response.setDateTime(Utility.getDate());
        }
        return response;

    }

    public Response getStudentById(Integer studentId) {
        Response response = new Response();
        Optional<StudentEntity> studentEntity = studentInterface.findById(studentId);
        if (!studentEntity.isPresent()) {
            LOGGER.error("The Data with this ID doesn't exist {}", studentId);
            response.setMessage("The Data with this ID" + " " + studentId + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setStudentEntity(null);
        } else {
            response.setMessage("The Data is Present with this ID" + " " + studentId);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntity(studentEntity.get());
        }
        response.setDateTime(Utility.getDate());
        return response;
    }

    public Response delStudentById(Integer studentId) {
        Response response = new Response();
        Optional<StudentEntity> studentEntity = studentInterface.findById(studentId);
        if (studentEntity.isPresent()) {
            studentInterface.deleteById(studentId);
            response.setMessage("The Data with this ID" + " " + studentId + " " + "has been deleted");
            response.setStatusCode(HttpStatus.ACCEPTED);
        } else {
            LOGGER.debug("The Debug Started with this Id {}", studentId);
            response.setMessage("The Data with this ID" + " " + studentId + " " + "Is already deleted or not available");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        }
        response.setStudentEntity(studentEntity.get());
        response.setDateTime(Utility.getDate());
        return response;
    }

    public Response savePartialData(StudentEntity entity) {
        Response response = new Response();
        if (entity != null && entity.getID() != 0) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setName(entity.getName());
            studentEntity.setSection(entity.getSection());
            studentEntity.setID(entity.getID());
            studentEntity.setSchoolName(entity.getSchoolName());
            StudentEntity student = studentInterface.save(studentEntity);
            if (student.getID() != 0) {
                response.setMessage("Partial Data Saved" + " " + student.getID());
                response.setStatusCode(HttpStatus.ACCEPTED);
                response.setStudentEntity(student);
            } else {
                response.setMessage("The Data has Not Been Saved or already Exists");
                response.setStatusCode(HttpStatus.ALREADY_REPORTED);
                response.setStudentEntity(null);
            }
            response.setDateTime(Utility.getDate());
        }
        return response;

    }

    public Response updateData(StudentEntity entity) {
        Response response = new Response();
        if (entity != null && entity.getID() != 0) {
            Optional<StudentEntity> studentEntity = studentInterface.findById(entity.getID());
            if (studentEntity.isPresent()) {
                if(entity.getName()!=null){
                    studentEntity.get().setName(entity.getName());
                }else{
                    studentEntity.get().setName(studentEntity.get().getName());
                }
                if(entity.getSection()!=null){
                    studentEntity.get().setSection(entity.getSection());
                }else{
                    studentEntity.get().setSection(studentEntity.get().getSection());
                }
                if(entity.getSchoolName()!=null){
                    studentEntity.get().setSchoolName(entity.getSchoolName());
                }else{
                    studentEntity.get().setName(studentEntity.get().getSchoolName());
                }
                StudentEntity student = studentInterface.save(studentEntity.get());
                if (student.getID() == (entity.getID())) {
                    response.setMessage("The Data has been updated with the Id" + " " + student.getID());
                    response.setStatusCode(HttpStatus.CREATED);
                    response.setStudentEntity(student);
                } else {
                    response.setMessage("The Data has Not Been updated");
                    response.setStatusCode(HttpStatus.ALREADY_REPORTED);
                    response.setStudentEntity(null);
                }
                response.setDateTime(Utility.getDate());
            }
        }
        return response;
    }

    public List<StudentEntity> findByName(String name) {
       return studentInterface.findByName(name);
    }
}