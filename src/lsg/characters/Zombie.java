package lsg.characters;

import lsg.weapons.Hands;

/**
 * Created by alecoeuc on 09/01/18.
 */
public class Zombie extends Monster {

    public Zombie() {
        super("Zombie");
        this.setWeapon(new Hands());
        this.setSkinThickness(10f);
    }
}
