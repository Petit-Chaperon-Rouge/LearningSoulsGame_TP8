package lsg.consumables;

import lsg.bags.Collectible;

/**
 * Created by alecoeuc on 07/11/17.
 */
public class Consumable implements Collectible {

    private String name;
    private int capacity;
    private String stat;


    // Getters & Setters


    public String getName(){
        return this.name;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public String getStat() {
        return this.stat;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    // Constructeur


    public Consumable(String name, int capacity, String stat) {
        this.name = name;
        this.capacity = capacity;
        this.stat = stat;
    }


    // Méthodes


    public int use(){
        int saveCapacity = this.getCapacity();
        this.setCapacity(0);
        return saveCapacity;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    /**
     * Surcharge de toString
     * @return nom [capacité statistique point(s)]
     */
    public String toString(){
        return this.getName() + " [" + this.getCapacity() + " " + this.getStat() + " point(s)]";
    }

}
