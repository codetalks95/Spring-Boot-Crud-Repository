package com.example.demo.service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public StudentResponse saveStudentInfo(StudentEntity entity) {
        return studentRepository.saveStudentsData(entity);
    }

    public StudentResponse getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    public StudentResponse delStudentById(Integer studentId) {
        return studentRepository.delStudentById(studentId);
    }

    public StudentResponse savePartialData(StudentEntity entity) {
        return studentRepository.savePartialData(entity);
    }

    public StudentResponse updateData(StudentEntity entity) {
        return studentRepository.updateData(entity);
    }

    public StudentResponse findByName(String name) {
        return studentRepository.findByName(name);
    }

    public StudentResponse findBySection(String section) {
        return studentRepository.findBySection(section);
    }

    public StudentResponse getName(String name) {
        return studentRepository.getName(name);
    }

    public StudentResponse getSection(String section) {
        return studentRepository.getSection(section);
    }

    public StudentResponse findBySchoolName(String schoolName) {
        return studentRepository.findBySchoolName(schoolName);
    }

    public StudentResponse patchData(StudentEntity entity) {
        return studentRepository.patchData(entity);
    }

    public StudentResponse updateName(StudentEntity entity) {
        return studentRepository.updateName(entity);
    }
}
