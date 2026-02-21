package controller;

import service.ServiceFactory;
import service.custom.SuplierManagementService;
import util.ServiceType;

public class SuplierManagementController {
    private final SuplierManagementService suplierManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPLIERMANAGEMENT);
}
