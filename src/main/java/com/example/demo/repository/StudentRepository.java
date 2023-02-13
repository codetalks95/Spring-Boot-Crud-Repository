package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentEntity;
import com.example.demo.interfaces.StudentInterface;
import com.example.demo.response.Response;
import com.example.demo.util.Utility;

@Repository
public class StudentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);

    @Autowired
    public StudentInterface studentInterface;

    public Response saveStudentsData(StudentEntity entity) {
        Response response = new Response();
        if (entity != null) {
            StudentEntity student = studentInterface.save(entity);
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
            response.setMessage(
                    "The Data with this ID" + " " + studentId + " " + "Is already deleted or not available");
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
                if (entity.getName() != null) {
                    studentEntity.get().setName(entity.getName());
                } else {
                    studentEntity.get().setName(studentEntity.get().getName());
                }
                if (entity.getSection() != null) {
                    studentEntity.get().setSection(entity.getSection());
                } else {
                    studentEntity.get().setSection(studentEntity.get().getSection());
                }
                if (entity.getSchoolName() != null) {
                    studentEntity.get().setSchoolName(entity.getSchoolName());
                } else {
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

    public Response findByName(String name) {
        Response response = new Response();
        List<StudentEntity> studentEntityList = studentInterface.findByName(name);
        if (studentEntityList.isEmpty()) {
            LOGGER.error("The Data with this Name doesn't exist {}", name);
            response.setMessage("The Data with this Name" + " " + name + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        } else {
            response.setMessage("The Data is Present with this Name" + " " + name);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntityList(studentEntityList);
        }
        response.setDateTime(Utility.getDate());
        return response;
    }

    public Response findBySection(String section) {
        Response response = new Response();
        List<StudentEntity> studentEntityList = studentInterface.findBySection(section);
        if (studentEntityList.isEmpty()) {
            LOGGER.error("The Data with this Section doesn't exist {}", section);
            response.setMessage("The Data with this Section" + " " + section + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        } else {
            response.setMessage("The Data is Present with this Section" + " " + section);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntityList(studentEntityList);
        }
        response.setDateTime(Utility.getDate());
        return response;
    }


    public Response findBySchoolName(String schoolName) {
        Response response = new Response();
        List<StudentEntity> studentEntityList = studentInterface.findBySchoolName(schoolName);
        if (studentEntityList.isEmpty()) {
            LOGGER.error("The Data with this School Name doesn't exist {}", schoolName);
            response.setMessage(
                    "The Data with this School Name" + " " + schoolName + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        } else {
            response.setMessage("The Data is Present with this Name" + " " + schoolName);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntityList(studentEntityList);
        }
        response.setDateTime(Utility.getDate());
        return response;
    }

    public Response patchData(StudentEntity entity) {
        Response response = new Response();
        Optional<StudentEntity> studentEntity = studentInterface.findById(entity.getID());
        if (studentEntity.isPresent()) {
            studentEntity.get().setSchoolName(entity.getSchoolName());
            StudentEntity student = studentInterface.save(studentEntity.get());
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

    public Response getSection(String section) {
        Response response = new Response();
        List<StudentEntity> studentEntityList = studentInterface.getSection(section);
        if (studentEntityList.isEmpty()) {
            LOGGER.error("The Data with this section doesn't exist {}", section);
            response.setMessage("The Data with this Name" + " " + section + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        } else {
            response.setMessage("The Data is Present with this Name" + " " + section);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntityList(studentEntityList);
        }
        response.setDateTime(Utility.getDate());
        return response;
    }

    public Response getName(String name) {
        Response response = new Response();
        List<StudentEntity> studentEntityList = studentInterface.getName(name);
        if (studentEntityList.isEmpty()) {
            LOGGER.error("The Data with this name doesn't exist {}", name);
            response.setMessage("The Data with this Name" + " " + name + " " + "doesn't exist in Database");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        } else {
            response.setMessage("The Data is Present with this Name" + " " + name);
            response.setStatusCode(HttpStatus.FOUND);
            response.setStudentEntityList(studentEntityList);
        }
        response.setDateTime(Utility.getDate());
        return response;
    }


    public Response updateName(StudentEntity entity) {
        Response response = new Response();
        if (entity != null && entity.getID() > 0) {
            int studentEntity = studentInterface.updateName(entity.getName(), entity.getSection(), entity.getID());
            if (studentEntity != 0) {
                response.setMessage("Data has been Updated for the ID" + " " + entity.getID());
                response.setStatusCode(HttpStatus.ACCEPTED);
                response.setStudentEntity(studentInterface.findById(entity.getID()).get());
            } else {
                response.setMessage("The Data has Not Been Saved or already Exists");
                response.setStatusCode(HttpStatus.ALREADY_REPORTED);
                response.setStudentEntity(null);
            }
            response.setDateTime(Utility.getDate());
        }
        return response;
    }

}