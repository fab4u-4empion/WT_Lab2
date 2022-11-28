package by.tc.task01.dao.impl;

import by.tc.task01.dao.ApplianceDAO;
import by.tc.task01.entity.*;
import by.tc.task01.entity.criteria.Criteria;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * this class is an implementation of the ApplianceDAO interface
 * all data is stored in xml file
 * @see ApplianceDAO
 * @author ufpnjh
 */
public class ApplianceDAOImpl implements ApplianceDAO{

	/**
	 * .xml file path
	 */
	private static final String xmlFilePath = "D:\\qwert.xml";

	/**
	 * saves an appliance in the xml file
	 * if the file already had appliances, adds the appliance to the end of file
	 * @param appliances
	 */
	@Override
	public void save(List<Appliance> appliances) {
		try(XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(xmlFilePath)))){
			for(Appliance appliance : appliances){
				encoder.writeObject(appliance);
			}
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}

	}

	/**
	 * returns list with all found appliances and an empty list if nothing was found
	 * @param criteria
	 * @return list with all found appliances and an empty list if nothing was found
	 */
	@Override
	public List<Appliance> find(Criteria criteria) {
		List<Appliance> foundAppliances = new ArrayList<>();
		Appliance appliance;

		try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(xmlFilePath)))) {
			do {
				appliance = (Appliance) decoder.readObject();
				if (fitsCriteria(appliance, criteria)) {
					foundAppliances.add(appliance);
				}

			} while (appliance != null);
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
		catch (FileNotFoundException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}

		return foundAppliances;

	}

	/**
	 * Checks whether the appliance's properties fit certain criteria.
	 * @param appliance appliance which criteria need to be checked
	 * @param criteria a set of criteria
	 * @return true if appliance fits the specified criteria
	 * @throws IllegalAccessException if access to appliance field is closed
	 */
	private boolean fitsCriteria(Appliance appliance, Criteria criteria) throws IllegalAccessException {
		if (!appliance.getClass().getSimpleName().equals(criteria.getGroupSearchName())){
			return false;
		}

		Set<String> properties = criteria.getCriteria().keySet();
		for (String property : properties) {
			try {
				Field field = appliance.getClass().getDeclaredField(property);
				field.setAccessible(true);
				Object fieldValue = field.get(appliance);
				if (!fieldValue.toString().equals(criteria.getCriteria().get(property).toString())) {
					return false;
				}
			} catch (NoSuchFieldException e){

			}
		}
		for (String property : properties) {
			try {
				Field parentField = appliance.getClass().getSuperclass().getDeclaredField(property);
				parentField.setAccessible(true);
				Object parentFieldValue = parentField.get(appliance);
				if (!parentFieldValue.toString().equals(criteria.getCriteria().get(property).toString())) {
					return false;
				}
			} catch (NoSuchFieldException e) {
			}
		}
		return true;
	}
}


//you may add your own new classes