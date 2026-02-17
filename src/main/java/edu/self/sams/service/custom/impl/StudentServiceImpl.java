package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.StudentDao;
import edu.self.sams.dto.StudentDto;
import edu.self.sams.entity.StudentEntity;
import edu.self.sams.service.custom.StudentService;

import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.STUDENT);

    @Override
    public String saveStudent(StudentDto studentDto) throws Exception {
        StudentEntity  studentEntity = new StudentEntity(studentDto.getRegNo(),studentDto.getName(),studentDto.getEmail(),studentDto.getTeleNo());
        boolean isSaved = studentDao.save(studentEntity);
        return isSaved ? "Student Saved Successfully" : "Student Save Failed";
    }

    @Override
    public String updateStudent(StudentDto studentDto) throws Exception {
        StudentEntity  studentEntity = new StudentEntity(studentDto.getRegNo(),studentDto.getName(),studentDto.getEmail(),studentDto.getTeleNo());
        boolean isUpdated = studentDao.update(studentEntity);
        return isUpdated ? "Student Update Successfully" : "Student Update Failed";
    }

    @Override
    public String deleteStudent(String regNo) throws Exception {
        boolean isDeleted = studentDao.delete(regNo);
        return isDeleted ? "Student Deleted Successfully" : "Student Delete Failed";
    }

    @Override
    public StudentDto getStudent(String regNo) throws Exception {
        StudentEntity studentEntity = studentDao.get(regNo);
        if(studentEntity != null){
            StudentDto studentDto = new StudentDto(studentEntity.getRegNo(),studentEntity.getName(),studentEntity.getEmail(),studentEntity.getTeleNo());
            return studentDto;
        }
        return null;
    }

    @Override
    public ArrayList<StudentDto> getStudents() throws Exception {
        ArrayList <StudentEntity> studentEntities = studentDao.getAll();
        if(studentEntities != null){
            ArrayList<StudentDto> studentDtos = new ArrayList<>();
            for(StudentEntity studentEntity : studentEntities){
                studentDtos.add(new StudentDto(studentEntity.getRegNo(),studentEntity.getName(),studentEntity.getEmail(),studentEntity.getTeleNo()));
            }
            return studentDtos;
        }
        return null;
    }
}
