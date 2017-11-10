package lsg.bags;

import lsg.LearningSoulsGame;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.consumables.food.Hamburger;
import lsg.weapons.Sword;

import java.util.HashSet;

/**
 * Created by alecoeuc on 10/11/17.
 */
public class Bag {

    private int capacity; //Nb kilos max transportable
    private int weight; //Nb kilo utilisés
    private HashSet<Collectible> items = new HashSet<>();


    // Getteurs & Setteurs


    public int getCapacity() {
        return this.capacity;
    }

    public int getWeight() {
        return this.weight;
    }


    // Constructeurs


    public Bag(int capacity) {
        this.capacity = capacity;
        this.weight = 0;
    }


    // Méthodes


    public void push(Collectible item) {
        if ((this.weight + item.getWeight()) <= capacity) {
            this.items.add(item);
            this.weight += item.getWeight();
        }
    }

    public Collectible pop(Collectible item) {
        Collectible toRemove = null;

        if(this.items.contains(item)) {
            toRemove = item;
            this.weight -= item.getWeight();
            items.remove(item);
        }

        return toRemove;
    }

    public boolean contains(Collectible item) {
        return this.items.contains(item);
    }

    public Collectible[] getItems() {
        Collectible[] toReturn = new Collectible[this.items.size()];
        int i = 0;

        for (Collectible collectible : this.items){
            toReturn[i] = collectible;
            i++;
        }

        return toReturn;
    }

    public String toString() {
        String toReturn = this.getClass().getSimpleName() + " [ " + this.getItems().length + " items | " + this.getWeight() + "/" + this.getCapacity() + " kg ]\n";
        if (this.getItems().length <= 0) {
            toReturn += LearningSoulsGame.BULLET_POINT + "(empty)";
        }
        else {
            for (int i = 0; i < this.getItems().length; i++) {
                toReturn += LearningSoulsGame.BULLET_POINT + this.getItems()[i].toString() + "[" + this.getItems()[i].getWeight() + " kg]\n";
            }
        }

        return toReturn;
    }

    public static void transfer(Bag from, Bag into) {
        Collectible toTransfert;
        boolean ableToTransfert = true;

        for (int i = 0; i<from.getItems().length && ableToTransfert==true; i++) {
            toTransfert = from.pop(from.getItems()[i]);
            if (into.getWeight()+toTransfert.getWeight() < into.getCapacity()){
                into.push(toTransfert);
            }
            else
                from.push(toTransfert);
                ableToTransfert = false;
        }
    }

    public static void main(String[] args) {
        SmallBag smallBag = new SmallBag();
        smallBag.push(new BlackWitchVeil());
        smallBag.push(new Hamburger());
        smallBag.push(new Sword());
        DragonSlayerLeggings dr = new DragonSlayerLeggings();
        smallBag.push(dr);

        System.out.println(smallBag.toString());

        smallBag.pop(dr);
        System.out.println("Pop sur " + dr.toString() + "\n");

        System.out.println(smallBag.toString());
    }

}
