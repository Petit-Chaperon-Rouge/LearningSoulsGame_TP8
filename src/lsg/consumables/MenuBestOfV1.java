package lsg.consumables;

import lsg.characters.Hero;
import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

/**
 * Created by alecoeuc on 07/11/17.
 */
public class MenuBestOfV1 {

    private Consumable[] menu;


    // Constructeurs


    public MenuBestOfV1(){
        menu = new Consumable[]{new Hamburger(), new Wine(), new Americain(), new Coffee(), new Whisky()};
    }


    // Méthodes


    public String toString(){
        String toReturn = "MenuBestOfV1 :\n";
        for (int i = 0; i<menu.length; i++){
            toReturn += i+1 + " : " + this.menu[i].toString() + "\n";
        }
        return toReturn;
    }


    public static void main(String[] args) {
        MenuBestOfV1 menuV1 = new MenuBestOfV1();
        System.out.println(menuV1.toString());
    }

}
