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
        System.out.println(suplierDTO.toString());
        return false;
    }

    @Override
    public boolean updateSuplier(SuplierDTO updatedSuplierDTO) throws SQLException{
        System.out.println(updatedSuplierDTO.toString());

        return false;
    }

}
