package lsg.armor;

import lsg.bags.Collectible;

/**
 * Created by alecoeuc on 19/10/17.
 */
public class ArmorItem implements Collectible {

    private String name;
    private float armorValue;


    // Getteurs & Setteurs


    public String getName() {
        return name;
    }

    public float getArmorValue() {
        return armorValue;
    }


    // Constructeurs


    public ArmorItem (String name, float armorValue) {
        this.name = name;
        this.armorValue = armorValue;
    }

    // Méthodes


    @Override
    public int getWeight() {
        return 4;
    }

    /**
     * Surcharge de toString
     * @return Les statistiques de l'armure
     */
    public String toString() {
        return this.getName() + "(" + this.getArmorValue() + ")";
    }
}
