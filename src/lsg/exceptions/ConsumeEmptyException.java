package lsg.exceptions;

import lsg.consumables.Consumable;

/**
 * Created by alecoeuc on 21/12/17.
 */
public class ConsumeEmptyException extends ConsumeException {

    public ConsumeEmptyException(Consumable consumable) {
        super("Consumable is empty", consumable);
    }

}
