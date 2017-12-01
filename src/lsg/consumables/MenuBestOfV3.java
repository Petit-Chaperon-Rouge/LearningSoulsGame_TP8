package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

import java.util.HashSet;

/**
 * Created by alecoeuc on 07/11/17.
 */
public class MenuBestOfV3 extends HashSet<Consumable>{


    // Constructeurs


    public MenuBestOfV3(){
        this.add(new Hamburger());
        this.add(new Wine());
        this.add(new Americain());
        this.add(new Coffee());
        this.add(new Whisky());
    }


    // Méthodes


    public String toString(){
        String toReturn = "MenuBestOfV3 :\n";
        int i = 1;
        for (Consumable consumable : this){
            toReturn += i + " : " + consumable + "\n";
            i++;
        }
        return toReturn;
    }


    public static void main(String[] args) { // Le HashSet ne garanti pas la conservation de l'ordre
        MenuBestOfV3 menuV3 = new MenuBestOfV3();
        System.out.println(menuV3.toString());
    }

}
