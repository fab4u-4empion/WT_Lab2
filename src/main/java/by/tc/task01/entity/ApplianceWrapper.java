package by.tc.task01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ApplianceWrapper {

    private List<Appliance> appliances = new ArrayList<>();

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void add(Appliance appliance) {
        this.appliances.add(appliance);
    }

}
