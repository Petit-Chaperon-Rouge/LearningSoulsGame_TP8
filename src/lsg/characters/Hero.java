package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.bags.Collectible;
import lsg.buffs.rings.Ring;

import static java.lang.String.format;

/**
 * Created by alecoeuc on 22/09/17.
 */
public class Hero extends Character {

    private ArmorItem[] armor;
    private Ring[] rings;

    private static int MAX_ARMOR_PIECES = 3;
    private static int MAX_RINGS = 2;


    // Getteurs & Setteurs


    /**
     * Equipe une armure à un slot du héro
     * @param armor L'armure à équiper
     * @param i le slot où doit être équipé l'armure (supérieur à 0)
     */
    public void setArmorItem(ArmorItem armor, int i){
        if (!(i<=0 || i>MAX_ARMOR_PIECES)){
            this.armor[i-1] = armor;
        }
    }

    /**
     * Retourne un tableau contenant les armures du héro
     * @return (ArmorItem[])
     */
    public ArmorItem[] getArmorItems(){
        ArmorItem[] temp;
        int j = 0;

        for(int i=0; i<MAX_ARMOR_PIECES; i++) {
            if (this.armor[i]!=null) {
                j++;
            }
        }
        temp = new ArmorItem[j];
        j = 0;
        for(int i=0; i<MAX_ARMOR_PIECES; i++) {
            if (this.armor[i]!=null) {
                temp[j] = this.armor[i];
                j++;
            }
        }

        return temp;
    }

    /**
     * Retourne la valeur de protection totale conférés des armures du héro
     * @return (float)
     */
    public float getTotalArmor(){
        float somme=0;

        for(int i=0; i<MAX_ARMOR_PIECES; i++) {
            if (this.armor[i]!=null)
                somme += this.armor[i].getArmorValue();
        }

        return somme;
    }


    /**
     * Equipe un anneau à un slot du héro
     * @param ring L'anneau à équiper
     * @param i le slot où doit être équipé l'anneau (supérieur à 0)
     */
    public void setRing(Ring ring, int i){
        if (!(i<=0 || i>MAX_RINGS)){
            this.rings[i-1] = ring;
            ring.setHero(this);
        }
    }

    /**
     * Retourne un tableau contenant les anneaux du héro
     * @return (Ring[])
     */
    public Ring[] getRings(){
        Ring[] temp;
        int j = 0;

        for(int i=0; i<MAX_RINGS; i++) {
            if (this.rings[i]!=null) {
                j++;
            }
        }
        temp = new Ring[j];
        j = 0;
        for(int i=0; i<MAX_RINGS; i++) {
            if (this.rings[i]!=null) {
                temp[j] = this.rings[i];
                j++;
            }
        }

        return temp;
    }

    /**
     * Retourne la valeur de buff totale conférés par les anneaux du héro
     * @return (float)
     */
    public float getTotalBuff(){
        float somme=0;

        for(int i=0; i<MAX_RINGS; i++) {
            if (this.rings[i]!=null)
                somme += this.rings[i].computeBuffValue();
        }

        return somme;
    }



    // Constructeurs



    /**
     * Crée un héro par défault nommé Gregooninator
     */
    public Hero(){
        this.setName("Gregooninator");
        this.setMaxLife(100);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(50);
        this.setStamina(this.getMaxStamina());
        this.armor = new ArmorItem[MAX_ARMOR_PIECES];
        this.rings = new Ring[MAX_RINGS];
    }

    /**
     * Crée un héro avec le nom choisi
     * @param name Le nom a donner au héro
     */
    public Hero(String name){
        super(name);
        this.setMaxLife(100);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(50);
        this.setStamina(this.getMaxStamina());
        this.armor = new ArmorItem[MAX_ARMOR_PIECES];
        this.rings = new Ring[MAX_RINGS];
    }


    // Méthodes


    /**
     * Surcharge de toString
     * @return Les statistiques de l'armure
     */
    public String armorToString() {
        String stringArmor = "ARMOR ";

        for (int i=0; i<MAX_ARMOR_PIECES; i++){
            if (this.armor[i]!=null)
                stringArmor += format(" %2d:%-30s", i+1, this.armor[i].toString());
                //stringArmor += i+1 + ":" + this.armor[i].toString() + "\t";
            else
                stringArmor += format(" %2d:%-30s", i+1, "empty");
        }

        stringArmor += "TOTAL:" + this.getTotalArmor();

        return stringArmor;
    }


    @Override
    protected float computeProtection() {
        return this.getTotalArmor();
    }

    @Override
    protected float computeBuff() {
        return this.getTotalBuff();
    }

    /**
     * Recherche l'armure passée en paramètre dans le sac et l'équipe sur le slot désigné (cela le retire du sac)
     * @param item L'armure à équiper
     * @param slot L'emplacement où l'équiper
     */
    public void equip(ArmorItem item, int slot) {
        for (Collectible c : this.getBagItems()) {
            if (c.equals(item)) {
                this.pullOut(c);
                System.out.println(" and equips it !");
                this.setArmorItem(item, slot);
            }
        }
    }

    /**
     * Recherche le ring passé en paramètre dans le sac et l'équipe sur le slot désigné (cela le retire du sac)
     * @param ring Le ring à équiper
     * @param slot L'emplacement où l'équiper
     */
    public void equip(Ring ring, int slot) {
        for (Collectible c : this.getBagItems()) {
            if (c.equals(ring)) {
                this.pullOut(c);
                System.out.println(" and equips it !");
                this.setRing(ring, slot);
            }
        }
    }


    public static void main(String[] args) {

        Hero hero = new Hero();
        ArmorItem armor = new BlackWitchVeil();
        ArmorItem armor2 = new RingedKnightArmor();

        hero.setArmorItem(armor, 1);
        hero.setArmorItem(armor2, 3);

        System.out.println(hero.armorToString());
    }

}