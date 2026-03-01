package service.custom.impl;

import mapper.SuplierMapper;
import model.dto.LotDTO;
import model.dto.MedicineDTO;
import model.dto.SuplierDTO;
import model.entity.Suplier;
import repository.RepositoryFactroy;
import repository.custom.SuplierRepository;
import service.ServiceFactory;
import service.custom.SuplierManagementService;
import util.RepositoryType;
import util.ServiceType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuplierManagementServiceImpl implements SuplierManagementService {
    SuplierRepository suplierRepository = RepositoryFactroy.getInstance().getRepositoryType(RepositoryType.SUPLIER);
    SuplierMapper suplierMapper = SuplierMapper.INSTANCE;
    @Override
    public List<SuplierDTO> getSuplierData() throws SQLException {
        List<Suplier> all = suplierRepository.getAll();
        List<SuplierDTO> suppliers = new ArrayList<>();
        all.forEach(suplier -> {
            suppliers.add(suplierMapper.toDTO(suplier));
        });
        return suppliers;
    }

    @Override
    public boolean addNewSuplier(SuplierDTO suplierDTO) throws SQLException{
        return suplierRepository.create(new Suplier(
                null,
                suplierDTO.getName(),
                suplierDTO.getContactPerson(),
                suplierDTO.getPhone(),
                suplierDTO.getLeadTime(),
                suplierDTO.getEmail(),
                suplierDTO.getStatus(),
                null
        ));
    }

    @Override
    public boolean updateSuplier(SuplierDTO updatedSuplierDTO) throws SQLException{
        return suplierRepository.update(new Suplier(
                updatedSuplierDTO.getId(),
                updatedSuplierDTO.getName(),
                updatedSuplierDTO.getContactPerson(),
                updatedSuplierDTO.getPhone(),
                updatedSuplierDTO.getLeadTime(),
                updatedSuplierDTO.getEmail(),
                updatedSuplierDTO.getStatus(),
                null
        ));
    }

    @Override
    public boolean deleteSuplier(Long id) throws SQLException {
        return suplierRepository.deleteById(id);
    }

}
