package controller;

import service.ServiceFactory;
import service.custom.BillingService;
import util.ServiceType;

public class BillingController {
    private final BillingService billingService = ServiceFactory.getInstance().getServiceType(ServiceType.BILLING);
}
