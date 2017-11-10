package lsg.buffs.rings;

import lsg.armor.ArmorItem;
import lsg.armor.DragonSlayerLeggings;

public class DragonSlayerRing extends Ring{
	
	public DragonSlayerRing() {
		super("Dragon Slayer Ring", 14) ;
	}


	/**
	 * Retourne un buff si le personnage est équipé d'une armure "Dragon Slayer"
	 * @return (float) La valeur du buff
	 */
	@Override
	public float computeBuffValue() {
		if(hero != null && hasDragonsSlayerItem()){
			return power ;
		}else return 0 ;
	}

	/**
	 * Retourne si le personnage est équipé d'une armure "Dragon Slayer"
	 * @return (boolean)
	 */
	private boolean hasDragonsSlayerItem(){
		ArmorItem[] items = hero.getArmorItems() ;
		for(ArmorItem item: items){
			if(item instanceof DragonSlayerLeggings) return true ; 
		}
		return false ;
	}
	
}
