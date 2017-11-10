package lsg;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.buffs.rings.DragonSlayerRing;
import lsg.buffs.rings.RingOfDeath;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
import lsg.consumables.Consumable;
import lsg.consumables.MenuBestOfV4;
import lsg.consumables.food.Hamburger;
import lsg.weapons.Claw;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.Scanner;

/**
 * Created by alecoeuc on 22/09/17.
 */
public class LearningSoulsGame {

    private Hero hero;
    private Monster monster;
    private Scanner scanner = new Scanner(System.in);

    public static final String BULLET_POINT = "\u2219 ";


    // Méthodes


    private void title() {
        System.out.println("#############################");
        System.out.println("#  THE LEARNING SOULS GAME  #");
        System.out.println("#############################\n");
    }


    private void refresh() {
        hero.printStats();
        System.out.println(BULLET_POINT + hero.getWeapon().toString());
        System.out.println(BULLET_POINT + hero.getConsumable().toString());
        monster.printStats();

    }


    private void fight1v1() {
        title();
        refresh();

        boolean heroTurn = true;
        int damage;
        int attack;

        while (hero.isAlive() && monster.isAlive()) {
            if (heroTurn) {
                System.out.println("\nHero's action for next move : (1) attack | (2) consume > ");
                int action = scanner.nextInt();
                if (action == 1) {
                    attack = hero.attack();
                    damage = monster.getHitWith(attack);

                    System.out.println("\n" + hero.getName() + " attacks " + monster.getName() + " with " + hero.getWeapon().getName() + " (ATTACK:" + attack + " | DMG : " + damage + ")\n");
                }
                else if (action == 2) {
                    hero.consume();
                }
                heroTurn = false;
            }
            else {
                attack = monster.attack();
                damage = hero.getHitWith(attack);

                System.out.println("\n" + monster.getName() + " attacks " + hero.getName() + " with " + monster.getWeapon().getName() + " (ATTACK:" + attack + " | DMG : " + damage + ")\n");

                heroTurn = true;
            }

            refresh();
        }

        if (hero.isAlive()) {
            System.out.println("\n--- " + hero.getName() + " WINS !!! ---");
        }
        else {
            System.out.println("\n--- " + hero.getName() + " LOSE... ---");
        }

    }


    private void init() {
        Sword sword = new Sword();
        Claw claw = new Claw();

        hero = new Hero();
        monster = new Monster();

        hero.setWeapon(sword);
        hero.setConsumable(new Hamburger());
        monster.setWeapon(claw);
    }

    private void createExhaustedHero() {
        hero = new Hero();
        hero.getHitWith(99);
        hero.setWeapon(new Weapon("Grosse Arme", 0,0,1000,100));
        hero.attack();
        hero.printStats();
    }

    private void aTable() {
        MenuBestOfV4 menu = new MenuBestOfV4();
        for (Consumable consumable : menu){
            hero.use(consumable);
            hero.printStats();
            System.out.println("Apres utilisation : " + consumable.toString());
            System.out.println(hero.getWeapon().toString());
        }
    }

    private void play_v1() {
        init();
        fight1v1();
    }

    private void play_v2() {
        init();
        hero.setArmorItem(new DragonSlayerLeggings(), 1);

        fight1v1();
    }

    private void play_v3() {
        init();
        hero.setArmorItem(new DragonSlayerLeggings(), 1);
        monster = new Lycanthrope();
        hero.setRing(new RingOfDeath(), 1);
        hero.setRing(new DragonSlayerRing(), 2);
        fight1v1();
    }


    public static void main(String[] args) {

        LearningSoulsGame game = new LearningSoulsGame();

        //game.play_v1();
        //game.play_v2();
        game.play_v3();
        //game.createExhaustedHero();
        //game.aTable();
    }
}