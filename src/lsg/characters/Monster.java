package lsg.characters;

import lsg.buffs.talismans.Talisman;

/**
 * Created by alecoeuc on 22/09/17.
 */
public class Monster extends Character {


    private static int INSTANCES_COUNT = 0;

    private float skinThickness = 20;
    private Talisman talisman;


    // Getteurs & Setteurs

    /**
     * Retourne la valeur de protection du monstre
     * @return (float)
     */
    public float getSkinThickness() {
        return this.skinThickness;
    }

    /**
     * Modifie la valeur de protection du monstre
     * @param skinThickness (float) la nouvelle valeur de protection
     */
    protected void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    /**
     * Equipe un talisman à un slot du monstre
     * @param talisman Le talisman à équiper
     */
    public void setTalisman(Talisman talisman){
        this.talisman = talisman;
    }

    /**
     * Retourne le talisman du monstre
     * @return (Talisman)
     */
    public Talisman getTalisman(){
        return this.talisman;
    }



    // Constructeurs


    /**
     * Crée un monstre par défault nommé Monstre_N où N est le nombre de monstre déjà créé
     */
    public Monster(){
        INSTANCES_COUNT++;
        this.setName("Monster_" + INSTANCES_COUNT);
        this.setMaxLife(10);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(10);
        this.setStamina(this.getMaxStamina());
    }

    /**
     * Crée un monstre avec le nom choisi
     * @param name Le nom a donner au monstre
     */
    public Monster(String name){
        super(name);
        INSTANCES_COUNT++;
        this.setMaxLife(10);
        this.setLife(this.getMaxLife());
        this.setMaxStamina(10);
        this.setStamina(this.getMaxStamina());
    }


    // Méthodes


    @Override
    protected float computeProtection() {
        return this.getSkinThickness();
    }

    @Override
    protected float computeBuff() {
        if (talisman!=null)
            return this.talisman.computeBuffValue();
        else
            return 0;
    }
}