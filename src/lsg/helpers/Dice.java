package lsg.helpers;

import java.util.Random;

/**
 * Created by alecoeuc on 12/10/17.
 */
public class Dice {

    private int faces;
    private Random random;


    // Constructeurs


    public Dice(int nbFaces){
        this.faces = nbFaces;
        random = new Random(5340);
    }


    // Méthodes


    /**
     * Lance un dé dont le nombre de face est prédéterminé
     * @return (int) Le résultat du lancé de dé
     */
    public int roll(){
        return random.nextInt(faces);
    }


    public static void main(String[] args) {

        Dice dice = new Dice(50);
        int max = 0;
        int min = 50;

        for (int i=0; i<500; i++) {
            int current = dice.roll();
            System.out.print(current + " ");

            if(current>max)
                max = current;
            if(current<min)
                min = current;
        }

        System.out.println("\nmin :" + min);
        System.out.println("max :" + max);

    }

}

