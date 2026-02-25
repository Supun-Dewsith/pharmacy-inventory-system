package service.custom.impl;

import model.dto.LotDTO;
import model.dto.MedicineDTO;
import model.dto.OrderDTO;
import model.dto.SuplierDTO;
import service.custom.SuplierManagementService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuplierManagementServiceImpl implements SuplierManagementService {
    @Override
    public List<SuplierDTO> getSuplierData() {

        List<SuplierDTO> suppliers = new ArrayList<>();
        //test data
        // 1. Supplier: MediLife Pharma
        SuplierDTO s1 = new SuplierDTO(1L, "MediLife Pharma", "Dr. Amal Perera", "0771234567", 3, "contact@medilife.lk", "Active", new ArrayList<>());
        OrderDTO o1 = new OrderDTO(101L, 1L, LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 13), new ArrayList<>());
        o1.getLots().add(new LotDTO(501L, "L-9920", LocalDate.of(2028, 5, 20), "Passed - Sealed", 150.00));
        s1.getOrders().add(o1);

        // 2. Supplier: Global Health Supplies
        SuplierDTO s2 = new SuplierDTO(2L, "Global Health", "Sarah Jenkins", "0112987654", 7, "sales@globalhealth.com", "Active", new ArrayList<>());
        OrderDTO o2 = new OrderDTO(102L, 2L, LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 8), new ArrayList<>());
        o2.getLots().add(new LotDTO(502L, "B-4412", LocalDate.of(2027, 12, 10), "Passed - Cold Chain OK", 450.00));
        s2.getOrders().add(o2);

        // 3. Supplier: BioTech Labs (Delayed Supplier)
        SuplierDTO s3 = new SuplierDTO(3L, "BioTech Labs", "Mark Fernando", "0714455667", 14, "orders@biotech.lk", "Active", new ArrayList<>());
        OrderDTO o3 = new OrderDTO(103L, 3L, LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 19), new ArrayList<>());
        o3.getLots().add(new LotDTO(503L, "BT-001", LocalDate.of(2026, 6, 15), "Passed", 1200.00));
        s3.getOrders().add(o3);

        // 4. Supplier: QuickMeds Distributing
        SuplierDTO s4 = new SuplierDTO(4L, "QuickMeds", "Nimali Silva", "0332211445", 2, "info@quickmeds.lk", "Active", new ArrayList<>());
        OrderDTO o4 = new OrderDTO(104L, 4L, LocalDate.of(2026, 2, 20), LocalDate.of(2026, 2, 22), new ArrayList<>());
        o4.getLots().add(new LotDTO(504L, "QM-987", LocalDate.of(2029, 1, 01), "Passed - Express Delivery", 85.00));
        s4.getOrders().add(o4);

        // 5. Supplier: Apex Care (Suspended)
        SuplierDTO s5 = new SuplierDTO(5L, "Apex Care", "John Doe", "0759988776", 5, "admin@apexcare.lk", "Suspended", new ArrayList<>());
        OrderDTO o5 = new OrderDTO(105L, 5L, LocalDate.of(2025, 12, 15), LocalDate.of(2025, 12, 20), new ArrayList<>());
        o5.getLots().add(new LotDTO(505L, "AX-220", LocalDate.of(2026, 3, 15), "Failed - Damaged Box", 300.00));
        s5.getOrders().add(o5);
        s5.getOrders().add(o4);
        s5.getOrders().add(o3);
        s5.getOrders().add(o2);
        s5.getOrders().add(o1);

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        return suppliers;
    }

    @Override
    public boolean addNewSuplier(SuplierDTO suplierDTO) {
        System.out.println(suplierDTO.toString());
        return false;
    }

    @Override
    public boolean updateSuplier(SuplierDTO updatedSuplierDTO) {
        System.out.println(updatedSuplierDTO.toString());

        return false;
    }

}
