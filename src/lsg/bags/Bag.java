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

    /**
     * Ajoute un item dans le sac s'il y a la place
     * @param item L'item à ajouter
     */
    public void push(Collectible item) {
        if ((this.weight + item.getWeight()) <= capacity) {
            this.items.add(item);
            this.weight += item.getWeight();
        }
    }

    /**
     * Retire un item du sac
     * @param item L'item à retirer
     * @return L'item retiré, null s'il ne s'y trouvait pas
     */
    public Collectible pop(Collectible item) {
        Collectible toRemove = null;

        if(this.items.contains(item)) {
            toRemove = item;
            this.weight -= item.getWeight();
            items.remove(item);
        }

        return toRemove;
    }

    /**
     * Indique si l'item passé en paramètre se trouve bien dans le sac
     * @param item
     * @return true si l'item se trouve bien dans le sac, false sinon
     */
    public boolean contains(Collectible item) {
        return this.items.contains(item);
    }

    /**
     * Retourne un tableau contenant les items du sac
     * @return Un tableau contenant les items du sac
     */
    public Collectible[] getItems() {
        Collectible[] toReturn = new Collectible[this.items.size()];
        int i = 0;

        for (Collectible collectible : this.items){
            toReturn[i] = collectible;
            i++;
        }

        return toReturn;
    }

    /**
     * Affiche le contenu du sac
     * @return Une chaine contenant le contenu du sac
     */
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

    /**
     * Transfère le contenu du sac source dans le sac de destination dans la limite de capacité du dernier.
     * Les items qui n'ont pas pu être transféré restent dans le sac source
     * @param from Le sac source
     * @param into Le sac de destination
     */
    public static void transfer(Bag from, Bag into) {
        for (Collectible toTransfert : from.getItems()) {
                into.push(toTransfert);
            if (into.contains(toTransfert))
                from.pop(toTransfert);
        }
    }


    public static void main(String[] args) {
        SmallBag smallBag = new SmallBag();
        MediumBag mediumBag = new MediumBag();
        smallBag.push(new BlackWitchVeil());
        smallBag.push(new Hamburger());
        smallBag.push(new Sword());
        DragonSlayerLeggings dr = new DragonSlayerLeggings();
        smallBag.push(dr);

        System.out.println(smallBag.toString());

        smallBag.pop(dr);
        System.out.println("Pop sur " + dr.toString() + "\n");

        System.out.println(smallBag.toString());

        System.out.println("Sac 1 :");
        System.out.println("Sac 2 :");
        System.out.println("Sac 2 après transfert :");
        System.out.println("Sac 1 après transfert :");

    }

}
