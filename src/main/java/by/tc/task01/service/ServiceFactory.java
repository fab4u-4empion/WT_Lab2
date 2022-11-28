package by.tc.task01.service;

import by.tc.task01.service.impl.ApplianceServiceImpl;
import by.tc.task01.service.impl.ValidationServiceImpl;

/**
 * factory that creates and stores validation service and appliance service
 * @author ufpnjh
 */
public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private static final ValidationServiceImpl validationServiceInstance = new ValidationServiceImpl();
	private final ApplianceService applianceService = new ApplianceServiceImpl();
	
	private ServiceFactory() {}

	public ValidationServiceImpl getValidationService() {
		return validationServiceInstance;
	}
	public ApplianceService getApplianceService() {
		return applianceService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
