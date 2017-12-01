package lsg.exceptions;

/**
 * Created by alecoeuc on 01/12/17.
 */
public class WeaponNullException extends Exception {

    /**
     * Lorsque aucune arme n'est équipé
     */
    public WeaponNullException() {
        super("No Weapon !");
    }

}
