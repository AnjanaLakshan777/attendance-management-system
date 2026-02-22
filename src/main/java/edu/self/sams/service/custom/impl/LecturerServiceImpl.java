package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.LecturerDao;
import edu.self.sams.dto.LecturerDto;
import edu.self.sams.entity.LecturerEntity;
import edu.self.sams.entity.UserEntity;
import edu.self.sams.service.custom.LecturerService;

import java.util.ArrayList;
import java.util.List;

public class LecturerServiceImpl implements LecturerService {

    private LecturerDao lecturerDao = (LecturerDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.LECTURER);

    @Override
    public String saveLecturer(LecturerDto lecturerDto) throws Exception {
        UserEntity  userEntity = new UserEntity(lecturerDto.getUserId(),lecturerDto.getPassword(),"LECTURER");
        LecturerEntity  lecturerEntity = new LecturerEntity(lecturerDto.getUserId(),lecturerDto.getName(),lecturerDto.getEmail(),lecturerDto.getTeleNo(),userEntity);
        boolean isSaved = lecturerDao.save(lecturerEntity);
        return isSaved?"Lecturer Saved Successfully":"Lecturer Save Failed";
    }

    @Override
    public String updateLecturer(LecturerDto lecturerDto) throws Exception {
        UserEntity  userEntity = new UserEntity(lecturerDto.getUserId(),lecturerDto.getPassword(),"LECTURER");
        LecturerEntity  lecturerEntity = new LecturerEntity(lecturerDto.getUserId(),lecturerDto.getName(),lecturerDto.getEmail(),lecturerDto.getTeleNo(),userEntity);
        boolean isUpdated = lecturerDao.update(lecturerEntity);
        return isUpdated?"Lecturer Update Successfully":"Lecturer Update Failed";
    }

    @Override
    public String deleteLecturer(String userId) throws Exception {
        boolean isDeleted = lecturerDao.delete(userId);
        return isDeleted?"Lecturer Deleted Successfully":"Lecturer Delete Failed";
    }

    @Override
    public LecturerDto getLecturer(String userId) throws Exception {
        LecturerEntity lecturerEntity = lecturerDao.get(userId);
        if(lecturerEntity!=null){
            LecturerDto lecturerDto = new LecturerDto(lecturerEntity.getUserId(),lecturerEntity.getName(),lecturerEntity.getEmail(),lecturerEntity.getTeleNo(),lecturerEntity.getUserEntity().getPassword());
            return lecturerDto;
        }
        return null;
    }

    @Override
    public List<LecturerDto> getLecturers() throws Exception {
        ArrayList<LecturerEntity> lecturerEntities = lecturerDao.getAll();
        if(lecturerEntities!=null){
            ArrayList<LecturerDto> lecturerDtos = new ArrayList<>();
            for(LecturerEntity lecturerEntity : lecturerEntities){
                lecturerDtos.add(new LecturerDto(lecturerEntity.getUserId(),lecturerEntity.getName(),lecturerEntity.getEmail(),lecturerEntity.getTeleNo(),lecturerEntity.getUserEntity().getPassword()));
            }
            return lecturerDtos;
        }
        return null;
    }
}
