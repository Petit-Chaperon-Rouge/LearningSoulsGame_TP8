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
import lsg.exceptions.StaminaEmptyException;
import lsg.exceptions.WeaponBrokenException;
import lsg.exceptions.WeaponNullException;
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
        System.out.println(hero.armorToString());
        hero.printRings();
        hero.printConsumable();
        hero.printWeapon();
        hero.printBag();
        System.out.println();

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
                    try {
                        attack = hero.attack();
                    } catch (WeaponNullException e) {
                        attack = 0;
                        System.out.println("WARNING : no weapon has been equiped !!!");
                    } catch (WeaponBrokenException e) {
                        attack = 0;
                        System.out.println("WARNING : " + e.getMessage());
                    } catch (StaminaEmptyException e) {
                        attack = 0;
                        System.out.println("ACTION HAS NO EFFECT: no more stamina !!!");
                    }

                    damage = monster.getHitWith(attack);

                    System.out.println("\n" + hero.getName() + " attacks " + monster.getName() + " with " + hero.getWeapon() + " (ATTACK:" + attack + " | DMG : " + damage + ")\n");
                }
                else if (action == 2) {
                    hero.consume();
                }
                heroTurn = false;
            }
            else {
                try {
                    attack = monster.attack();
                } catch (WeaponNullException e) {
                    attack = 0;
                    System.out.println("WARNING : no weapon has been equiped !!!");
                } catch (WeaponBrokenException e) {
                    attack = 0;
                    System.out.println("WARNING : " + e.getMessage());
                } catch (StaminaEmptyException e) {
                    attack = 0;
                    System.out.println("ACTION HAS NO EFFECT: no more stamina !!!");
                }

                damage = hero.getHitWith(attack);

                System.out.println("\n" + monster.getName() + " attacks " + hero.getName() + " with " + monster.getWeapon() + " (ATTACK:" + attack + " | DMG : " + damage + ")\n");

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
        try {
            hero.attack();
        }catch (WeaponNullException e){
            e.printStackTrace();
        } catch (WeaponBrokenException e) {
            e.printStackTrace();
        } catch (StaminaEmptyException e) {
            e.printStackTrace();
        }

        hero.printStats();
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

    private void testExceptions() {
        hero.setWeapon(null);
        this.fight1v1();
    }


    public static void main(String[] args) {

        LearningSoulsGame game = new LearningSoulsGame();

        game.init();
        game.testExceptions();

    }
}