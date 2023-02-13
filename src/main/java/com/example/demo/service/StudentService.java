package com.example.demo.service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public Response saveStudentInfo(StudentEntity entity) {
        return studentRepository.saveStudentsData(entity);
    }

    public Response getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    public Response delStudentById(Integer studentId) {
        return studentRepository.delStudentById(studentId);
    }

    public Response savePartialData(StudentEntity entity) {
        return studentRepository.savePartialData(entity);
    }

    public Response updateData(StudentEntity entity) {
        return studentRepository.updateData(entity);
    }

    public Response findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Response findBySection(String section) {
        return studentRepository.findBySection(section);
    }

    public Response getName(String name) {
        return studentRepository.getName(name);
    }

    public Response getSection(String section) {
        return studentRepository.getSection(section);
    }

    public Response findBySchoolName(String schoolName) {
        return studentRepository.findBySchoolName(schoolName);
    }

    public Response patchData(StudentEntity entity) {
        return studentRepository.patchData(entity);
    }

    public Response updateName(StudentEntity entity) {
        return studentRepository.updateName(entity);
    }
}
