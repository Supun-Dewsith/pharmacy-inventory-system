package controller;

import service.ServiceFactory;
import service.custom.MedicineManagementService;
import util.ServiceType;

public class MedicineManagementController {
    private final MedicineManagementService medicineManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.MEDICINEMANAGEMENT);
}
