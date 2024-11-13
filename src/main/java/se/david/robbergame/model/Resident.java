package se.david.robbergame.model;

import java.util.HashMap;
import java.util.Map;


public class Resident extends Entity {

    private Map<String, Integer> inventory = new HashMap<>();

    public Resident(int health, int damage, String name) {

        super(health, damage, name);
        addWeapon("fists", damage);

    }

    // Metod för att lägga till vapen med skada (fists+weapon)
    public void addWeapon(String weaponName, int weaponDamage) {
        int totalDamage;

        if (weaponName.equals("pillow")) {
            totalDamage = weaponDamage; // Om vapnet är pillow, läggs bara pillow's skada till
        } else {
            totalDamage = getWeaponDamage("fists") + weaponDamage; // Annars läggs fists-skadan på vapnets skada
        }

        inventory.put(weaponName, totalDamage);
    }


    // Metod för att kontrollera om ett vapen finns i inventory (True/False)
    public boolean hasWeapon(String weapon) {
        return inventory.containsKey(weapon);
    }

    // Metod för att hämta skadan av ett vapen
    public int getWeaponDamage(String weapon) {
        return inventory.getOrDefault(weapon, 0); // Om vapnet inte finns, returneras 0
    }

    // Metod för att hämta lista med vapen i inventory
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
