package mapper;

import model.dto.LotDTO;
import model.dto.SuplierDTO;
import model.dto.SuplierOrderDTO;
import model.entity.Lot;
import model.entity.Suplier;
import model.entity.SuplierOrder;

import java.util.stream.Collectors;

public class SuplierMapper {
    public static final SuplierMapper INSTANCE = new SuplierMapper();

    private SuplierMapper(){}

    public SuplierDTO toDTO(Suplier suplier){
        if(suplier==null) return null;

        SuplierDTO dto = new SuplierDTO();
        dto.setId(suplier.getId());
        dto.setName(suplier.getName());
        dto.setContactPerson(suplier.getContactPerson());
        dto.setPhone(suplier.getPhone());
        dto.setLeadTime(suplier.getLeadTime());
        dto.setEmail(suplier.getEmail());
        dto.setStatus(suplier.getStatus());

        if(suplier.getOrders()!=null){
            dto.setOrders(suplier.getOrders().stream()
                    .map(this::toSuplierOrderDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public SuplierOrderDTO toSuplierOrderDTO(SuplierOrder order){
        SuplierOrderDTO dto = new SuplierOrderDTO();
        dto.setOrderID(order.getOrderID());
        dto.setSuplierID(order.getSuplierID());
        dto.setOrderDate(order.getOrderDate());
        dto.setReceivedDate(order.getReceivedDate());

        if(order!=null){
            dto.setLots(order.getLots().stream()
                    .map(this::toLotDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public LotDTO toLotDTO(Lot lot){
        LotDTO dto = new LotDTO();
        dto.setLotID(lot.getLotID());
        dto.setLotNumber(lot.getLotNumber());
        dto.setExpiryDate(lot.getExpiryDate());
        dto.setQualityInspectionResults(lot.getQualityInspectionResults());
        dto.setUnitCost(lot.getUnitCost());
        return dto;
    }
}
