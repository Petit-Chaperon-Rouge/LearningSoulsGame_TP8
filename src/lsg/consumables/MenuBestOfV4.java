package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.RepairKit;

import java.util.LinkedHashSet;

/**
 * Created by alecoeuc on 09/11/17.
 */
public class MenuBestOfV4 extends LinkedHashSet<Consumable> {

    // Constructeurs


    public MenuBestOfV4(){
        this.add(new Hamburger());
        this.add(new Wine());
        this.add(new Americain());
        this.add(new Coffee());
        this.add(new Whisky());
        this.add(new RepairKit());
    }


    // Méthodes


    public String toString(){
        String toReturn = "MenuBestOfV4 :\n";
        int i = 1;
        for (Consumable consumable : this){
            toReturn += i + " : " + consumable + "\n";
            i++;
        }
        return toReturn;
    }


    public static void main(String[] args) {
        MenuBestOfV4 menuV4 = new MenuBestOfV4();
        System.out.println(menuV4.toString());
    }

}
