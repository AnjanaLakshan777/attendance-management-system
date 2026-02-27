package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.SubjectDao;
import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.entity.SubjectEntity;
import edu.self.sams.service.custom.SubjectService;
import java.util.ArrayList;

public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao = (SubjectDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.SUBJECT);

    @Override
    public String saveSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity subjectEntity = new SubjectEntity(subjectDto.getSubjectCode(), subjectDto.getSubjectName());
        boolean isSaved = subjectDao.save(subjectEntity);
        return isSaved? "Subject Saved" : "Subject Not Saved";
    }

    @Override
    public String updateSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity subjectEntity = new SubjectEntity(subjectDto.getSubjectCode(), subjectDto.getSubjectName());
        boolean isUpdated = subjectDao.update(subjectEntity);
        return isUpdated? "Subject Updated" : "Subject Not Updated";
    }

    @Override
    public String deleteSubject(String subjectCode) throws Exception {
        boolean isDeleted = subjectDao.delete(subjectCode);
        return isDeleted? "Subject Deleted" : "Subject Not Deleted";
    }

    @Override
    public SubjectDto getSubject(String subjectCode) throws Exception {
        SubjectEntity subjectEntity = subjectDao.get(subjectCode);
        if(subjectEntity != null){
            return new SubjectDto(subjectEntity.getSubjectCode(), subjectEntity.getSubjectName());
        }
        return null;
    }

    @Override
    public ArrayList<SubjectDto> getSubjects() throws Exception {
        ArrayList<SubjectEntity> subjectEntities = subjectDao.getAll();
        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();
        if(subjectEntities != null){
            for (SubjectEntity subjectEntity : subjectEntities) {
                subjectDtos.add(new SubjectDto(subjectEntity.getSubjectCode(), subjectEntity.getSubjectName()));
            }
            return subjectDtos;
        }
        return null;
    }
}
