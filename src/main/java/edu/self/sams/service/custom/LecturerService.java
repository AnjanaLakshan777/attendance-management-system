package edu.self.sams.service.custom;

import edu.self.sams.dto.LecturerDto;
import edu.self.sams.service.SuperService;

import java.util.List;

public interface LecturerService extends SuperService {
    public String saveLecturer(LecturerDto lecturerDto) throws Exception;
    public String updateLecturer(LecturerDto lecturerDto) throws Exception;
    public String deleteLecturer(String userId) throws Exception;
    public LecturerDto getLecturer(String userId) throws Exception;
    public List<LecturerDto> getLecturers() throws Exception;
}
