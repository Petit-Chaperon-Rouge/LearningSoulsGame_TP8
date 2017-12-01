package lsg.exceptions;

import lsg.weapons.Weapon;

/**
 * Created by alecoeuc on 01/12/17.
 */
public class WeaponBrokenException extends Exception {

    private Weapon brokenWeapon;

    public WeaponBrokenException(Weapon weapon) {
        super(weapon.getName() + " is broken !");
        this.brokenWeapon = weapon;
    }

    public Weapon getBrokenWeapon () {
        return this.brokenWeapon;
    }

}
