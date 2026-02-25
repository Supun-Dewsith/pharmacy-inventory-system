package controller;

import service.ServiceFactory;
import service.custom.RepoartsAnalyticsService;
import util.ServiceType;

public class RepoartsAnalyticsController {
    private final RepoartsAnalyticsService repoartsAnalyticsService = ServiceFactory
            .getInstance().getServiceType(ServiceType.REPOARTSANALYTICS);
}
