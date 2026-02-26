package service;

import service.custom.ExpiryStockAlertsService;
import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private final BillingServiceImpl billingService;
    private final ExpiryStockAlertsServiceImpl expiryStockAlertsService;
    private final MainDashBoardServiceImpl mainDashBoardService;
    private final MedicineManagementServiceImpl medicineManagementService;
    private final RepoartsAnalyticsServiceImpl repoartsAnalyticsService;
    private final SuplierManagementServiceImpl suplierManagementService;

    private ServiceFactory() {
        expiryStockAlertsService = new ExpiryStockAlertsServiceImpl();
        repoartsAnalyticsService = new RepoartsAnalyticsServiceImpl();
        billingService = new BillingServiceImpl();
        mainDashBoardService=new MainDashBoardServiceImpl();
        medicineManagementService = new MedicineManagementServiceImpl();
        suplierManagementService = new SuplierManagementServiceImpl();
    }


    public static ServiceFactory getInstance() {
        return instance==null? instance=new ServiceFactory():instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case BILLING: return (T) billingService;
            case EXPIRYANDSTOCKALERTS: return (T) expiryStockAlertsService;
            case MAINDASHBOARD: return (T) mainDashBoardService;
            case MEDICINEMANAGEMENT: return (T) medicineManagementService;
            case REPOARTSANALYTICS: return (T) repoartsAnalyticsService;
            case SUPLIERMANAGEMENT: return (T) suplierManagementService;
        }
        return null;
    }
}
