package lsg.exceptions;

import lsg.consumables.Consumable;

/**
 * Created by alecoeuc on 21/12/17.
 */
public abstract class ConsumeException extends Exception {

    private Consumable consumable;

    public ConsumeException(String message, Consumable consumable){
        this.consumable = consumable;
    }

    public Consumable getConsumable(){
        return this.consumable;
    }

}
