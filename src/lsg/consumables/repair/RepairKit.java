package lsg.consumables.repair;

import lsg.consumables.Consumable;
import static lsg.weapons.Weapon.DURABILITY_STAT_STRING;

/**
 * Created by alecoeuc on 09/11/17.
 */
public class RepairKit extends Consumable{

    public RepairKit(){
        super("Repair Kit", 10, DURABILITY_STAT_STRING);
    }

    @Override
    public int use() {
        int saveCapacity = 0;
        if (this.getCapacity()>0){
            this.setCapacity(this.getCapacity() - 1);
            saveCapacity = 1;
        }
        return saveCapacity;
    }
}
