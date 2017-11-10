package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

import java.util.Iterator;

/**
 * Created by alecoeuc on 07/11/17.
 */
public class MenuBestOfV2 {

    private java.util.HashSet<Consumable> menu;


    // Constructeurs


    public MenuBestOfV2(){
        menu = new java.util.HashSet<Consumable>();
        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new Americain());
        menu.add(new Coffee());
        menu.add(new Whisky());
    }


    // Méthodes

    /**
     * Surcharge de toString
     * @return
     */
    public String toString(){
        String toReturn = "MenuBestOfV2 :\n";
        int i = 1;
        for (Consumable consumable : menu){
            toReturn += i + " : " + consumable + "\n";
            i++;
        }
        return toReturn;
    }


    public static void main(String[] args) {
        MenuBestOfV2 menuV2 = new MenuBestOfV2();
        System.out.println(menuV2.toString());
    }

}
