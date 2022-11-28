package by.tc.task01.dao;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.util.List;



/**
 * this dao interface declares some methods for working with appliances
 * all methods are identical to ApplianceService methods
 * @see by.tc.task01.service.ApplianceService
 * @author ufpnjh
 */
public interface ApplianceDAO {
	List<Appliance> find(Criteria criteria);

	void save(List<Appliance> appliance);
}
