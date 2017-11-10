package lsg.characters;

import lsg.weapons.Claw;

/**
 * Created by alecoeuc on 20/10/17.
 */
public class Lycanthrope extends Monster {

    public Lycanthrope() {
        super("Lycanthrope");
        this.setWeapon(new Claw());
        this.setSkinThickness(30f);
    }

}
