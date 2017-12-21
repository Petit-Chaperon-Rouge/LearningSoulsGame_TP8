package lsg.exceptions;

import lsg.consumables.Consumable;

/**
 * Created by alecoeuc on 21/12/17.
 */
public class ConsumeRepairNullWeaponException extends ConsumeException {

    public ConsumeRepairNullWeaponException(Consumable consumable) {
        super("Trying to repair null weapon !", consumable);
    }

}
