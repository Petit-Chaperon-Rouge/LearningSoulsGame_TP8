package lsg.characters;

import lsg.bags.Bag;
import lsg.bags.Collectible;
import lsg.bags.SmallBag;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.Drink;
import lsg.consumables.food.Food;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.StaminaEmptyException;
import lsg.exceptions.WeaponBrokenException;
import lsg.exceptions.WeaponNullException;
import lsg.helpers.Dice;
import lsg.weapons.Weapon;

import java.util.Locale;

/**
 * Created by alecoeuc on 22/09/17.
 */
public abstract class Character {

    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private Dice dice;
    private Weapon weapon;
    private Consumable consumable;
    private Bag bag;

    public static final String LIFE_STAT_STRING = "life";
    public static final String STAM_STAT_STRING = "stamina";
    public static final String PROTECTION_STAT_STRING = "protection";
    public static final String BUFF_STAT_STRING = "buff";


    // Getters & Setters


    /**
     *
     * @return (String) Le nom
     */
    public String getName(){
        return this.name;
    }

    /**
     * Changer ou donner le nom
     * @param name Le nom
     */
    protected void setName(String name){
        this.name = name;
    }



    /**
     *
     * @return (int) La vie restante
     */
    public int getLife(){
        return this.life;
    }

    /**
     * Modifie la valuer de la vie restante
     * @param life La vie restante
     */
    protected void setLife(int life){
        this.life = life;
    }



    /**
     *
     * @return (int) La valeur maximale de vie
     */
    public int getMaxLife(){
        return this.maxLife;
    }

    /**
     * Modifie la valeur maximale de vie
     * @param maxLife La valeur maximale de vie
     */
    protected void setMaxLife(int maxLife){
        this.maxLife = maxLife;
    }



    /**
     *
     * @return (int) La force restante
     */
    public int getStamina(){
        return this.stamina;
    }

    /**
     * Modifie la force restante
     * @param stamina La force restante
     */
    protected void setStamina(int stamina){
        this.stamina = stamina;
    }



    /**
     *
     * @return (int) La valeur de force maximale
     */
    public int getMaxStamina(){
        return this.maxStamina;
    }

    /**
     * Modifie la valeur de force maximale du héro
     * @param maxStamina La valeur de force maximale du héro
     */
    protected void setMaxStamina(int maxStamina){
        this.maxStamina = maxStamina;
    }



    /**
     *
     * @return (Weapon) L'arme du personnage
     */
    public Weapon getWeapon(){
        return this.weapon;
    }

    /**
     * Modifie l'arme du personnage
     * @param weapon L'arme du personnage
     */
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }


    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Retourne la capacité totale du sac
     * @return La capacité totale du sac
     */
    public int getBagCapacity() {
        return this.bag.getCapacity();
    }

    /**
     * Retourne le poids utilisé du sac
     * @return Le poids utilisé du sac
     */
    public int getBagWeight() {
        return this.bag.getWeight();
    }

    /**
     * Retourne les items contenus dans le sac du personnage sous forme de tableau
     * @return Le tableau d'items contenus dans le sac
     */
    public Collectible[] getBagItems() {
        return this.bag.getItems();
    }

    /**
     * Remplace le sac du personnage par le nouveau sac passé en paramètre. Les items de l'anciens sac sont transférés dans le nouveau
     * dans la limite de sa capacité
     * @param bag Le nouveau sac
     * @return L'ancien sac qui contient éventuellement les items qui n'ont pas pu être transférés si le nouveau sac était trop petit
     */
    public Bag setBag(Bag bag) {
        System.out.println(this.getName() + " changes " + this.bag.getClass().getSimpleName() + " for " + bag.getClass().getSimpleName());

        this.bag.transfer(this.bag, bag);
        Bag oldBag = this.bag;
        this.bag = bag;

        return oldBag;
    }



    // Constructeurs



    /**
     * Crée un personnage par défault
     */
    public Character(){
        dice = new Dice(101);
        bag = new SmallBag();
    }

    /**
     * Crée un personnage avec le nom choisi
     * @param name Le nom a donner
     */
    public Character(String name){
        this.setName(name);
        dice = new Dice(101);
        bag = new SmallBag();
    }



    // Méthodes



    /**
     * Retourne les statistiques du personnage sur la console
     */
    public void printStats(){
        System.out.println(this.toString());
    }

    /**
     * Surcharge de toString
     * @return Les statistiques du personnage
     */
    public String toString() {
        String alive = (this.isAlive()) ? "ALIVE" : "DEAD" ;
        return (String.format(Locale.US,"%-20s %-20s %-4s:%-10s %-7s:%-10s PROTECTION:%-10s BUFF:%-10s",
                "[ " + getClass().getSimpleName() + " ]", getName(), LIFE_STAT_STRING.toUpperCase(),String.format("%5d", this.getLife()), STAM_STAT_STRING.toUpperCase() ,String.format("%5d", this.getStamina()), String.format(Locale.US,"%6.2f", this.computeProtection()), String.format(Locale.US, "%6.2f", this.computeBuff())) + "(" + alive + ")");
    }

    /**
     * L'etat du personnage
     * @return L'etat du personnage
     */
    public boolean isAlive(){ // Peut être mis en friendly
        return this.getLife()!=0;
    }

    /**
     * Le personnage attaque.
     * Réarrange les statistiques du personnage et de l'arme
     * @return (int) La valeur des dégâts
     * @throws WeaponNullException Si aucune arme n'est équipé
     */
    private int attackWith(Weapon weapon) throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
        int damage = 0;

        if (weapon == null)
            throw new WeaponNullException();

        weapon.use();

        if (!weapon.isBroken()) {
            int precision = this.dice.roll();
            damage = (int) (weapon.getMinDamage() + (((weapon.getMaxDamage()-weapon.getMinDamage()) * precision)/100));

        }
        else
            throw new WeaponBrokenException(weapon);

        if (this.getStamina() < weapon.getStamCost()){
            damage = damage/(int)(this.getStamina()-weapon.getStamCost())/100;
            this.setStamina(0);
            throw new StaminaEmptyException();
        }
        else {
            this.setStamina(this.getStamina() - weapon.getStamCost());
        }

        return damage;
    }

    /**
     * Action d'attaquer
     * @return (int) La valeur des dégâts
     * @throws WeaponNullException Si aucune arme n'est équipé
     */
    public int attack() throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
        return attackWith(this.getWeapon());
    }

    /**
     * Calcule le nombre de points de vie effectivement retirés au personnage
     * en fonction des dégâts reçu
     * @param value Les dégâts reçu
     * @return (int)
     */
    public int getHitWith(int value) {

        if (this.computeProtection()>=100)
            value = 0;
        else {
            value = value - Math.round(value*this.computeProtection()/100);
        }

        int damage = this.getLife()<value ? this.getLife() : value;
        // Si la vie restante < dégâts reçu -> dégats = la vie restante, sinon dégats = dégats

        this.setLife(this.getLife()-damage);

        return damage;
    }

    /**
     * Remonte la vie du personnage en utilisant une boisson
     * @param drink La boisson à utiliser
     */
    private void drink(Drink drink) {
        System.out.println(this.getName() + " drinks " + drink.toString());

        int newStamina = this.getStamina()+drink.use();
        if (newStamina > this.getMaxStamina()) {
            newStamina = this.getMaxStamina();
        }
        this.setStamina(newStamina);
    }

    /**
     * Remonte la vie du personnage en utilisant de la nourriture
     * @param food La nourriture à utiliser
     */
    private void eat(Food food) {
        System.out.println(this.getName() + " eats " + food.toString());

        int newLife = this.getLife()+food.use();
        if (newLife > this.getMaxLife()){
            newLife = this.getMaxLife();
        }
        this.setLife(newLife);
    }

    public void use(Consumable consumable) {
        if (consumable instanceof Drink){
            this.drink((Drink)consumable);
        }
        else if (consumable instanceof Food){
            this.eat((Food)consumable);
        }

        else if (consumable instanceof RepairKit){
            try {
                this.repairWeaponWith((RepairKit)consumable);
            } catch (WeaponNullException e) {
                e.printStackTrace();
            }
        }
    }

    private void repairWeaponWith(RepairKit kit) throws WeaponNullException {

        if (this.getWeapon()==null)
            throw new WeaponNullException();

        System.out.println(this.getName() + " repairs " + this.getWeapon() + " with " + kit.toString());
        this.getWeapon().repairWith(kit);
    }

    /**
     * Permet au personnage d'utiliser le consommable équipé
     */
    public void consume() {
        this.use(this.getConsumable());
    }

    /**
     * Ajoute un item dans le sac du personnage (ne fait rien si le sac est plein
     * @param item L'item à ajouter
     */
    public void pickUp(Collectible item) {
        if (this.bag.getCapacity() >= (item.getWeight() + this.bag.getWeight())) {
            this.bag.push(item);
            System.out.print(this.getName() + " picks up " + item.toString());
        }
    }

    /**
     *
     * @param item retire un item du sac du personnage, s'il s'y trouve
     * @return L'item retiré, ou null s'il n'était pas dans le sac
     */
    public Collectible pullOut(Collectible item) {
        if (this.bag.contains(item)) {
            Collectible remove = this.bag.pop(item);
            System.out.print(this.getName() + " pulls out " + item.toString());
            return remove;
        }
        else
            return null;
    }

    /**
     * Recherche l'arme passée en paramètre dans le sac et l'équipe (cela la retire du sac)
     * @param weapon L'arme à équiper
     */
    public void equip(Weapon weapon) {
        if (this.bag.contains(weapon)) {
            this.pullOut(weapon);
            System.out.println(" and equips it !");
            this.setWeapon(weapon);
        }
    }

    /**
     * Recherche le consommable passé en paramètre dans le sac et l'équipe (cela le retire du sac)
     * @param consumable Le consommable à équiper
     */
    public void equip(Consumable consumable) {
        if (this.bag.contains(consumable)) {
            this.pullOut(consumable);
            System.out.println(" and equips it !");
            this.setConsumable(consumable);
        }
    }

    /**
     * Fouille le sac à la recherche du premier consommable d'instance de la classe de type et le consomme
     * @param type Défini l'instance de la classe du consommable à récupérer
     */
    private Consumable fastUseFirst(Class<? extends Consumable> type) {
        Consumable toReturn = null;

        System.out.println(this.getName() + " eats FAST :");
        for (Collectible c : bag.getItems()) {
            if (type.isInstance(c)) {
                this.use((Consumable) c);
                if (((Consumable) c).getCapacity()<=0)
                    this.pullOut(c);
                toReturn = (Consumable) c;
            }
        }
        return toReturn;
    }

    /**
     * Consomme la 1ère boisson trouvée dans le sac
     * @return La boisson trouvée
     */
    public Drink fastDrink() {
        return (Drink)this.fastUseFirst(Drink.class);
    }

    /**
     * Consomme la 1ère nourriture trouvée dans le sac
     * @return La nourriture trouvée
     */
    public Food fastEat() {
        return (Food)this.fastUseFirst(Food.class);
    }

    /**
     * Consomme 1 charge du 1er kit trouvé dans le sac
     * @return Le kit trouvé
     */
    public RepairKit fastRepair() {
        return (RepairKit)this.fastUseFirst(RepairKit.class);
    }

    /**
     * Affiche le contenu du sac du personnage
     */
    public void printBag() {
        System.out.println("BAG : " + this.bag.toString());
    }

    /**
     * Affiche le consommable du personnage
     */
    public void printConsumable() {
        System.out.println("CONSUMABLE : " + this.getConsumable());
    }

    public void printWeapon() {
        System.out.println("WEAPON : " + this.getWeapon());
    }

    protected abstract float computeProtection();
    protected abstract float computeBuff();

}
