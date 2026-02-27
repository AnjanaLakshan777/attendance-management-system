package edu.self.sams.service.custom;

import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.service.SuperService;

import java.util.ArrayList;

public interface SubjectService extends SuperService {
    public String saveSubject(SubjectDto subjectDto) throws Exception;
    public String updateSubject(SubjectDto subjectDto) throws Exception;
    public String deleteSubject(String subjectCode) throws Exception;
    public SubjectDto getSubject(String subjectCode) throws Exception;
    public ArrayList<SubjectDto> getSubjects() throws Exception;
}
