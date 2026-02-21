package controller;

import service.ServiceFactory;
import service.custom.ExpiryStockAlertsService;
import util.ServiceType;

public class ExpiryStockAlertsController {
    private final ExpiryStockAlertsService expiryStockAlertsService = ServiceFactory.getInstance().getServiceType(ServiceType.EXPIRYANDSTOCKALERTS);
}
