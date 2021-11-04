package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {300, 240, 250, 300, 700};
    public static int[] heroesDamage = {20, 15, 25, 0, 5};
    public static String[] heroesAttackType = {"Рыцарь",
            "Магнеса", "ПСИХ", "Медик", "Голем"};
    public static int round_number = 0;
    public static Random random = new Random();

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // на всякий случай
            bossHits();
        }
        mazohistGolem();
        heroesHit();
        medikHill();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); //0,1,2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;

                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }

    }

    public static void medikHill() {
        /*int randomHero = random.nextInt(heroesDamage.length);
        if (randomHero == 3) {
            medikHill();
        } else {
            int hp = random.nextInt(20) + 20;

            if (heroesHealth[3] > 0) {
                if (heroesHealth[randomHero] < 1000 && heroesHealth[randomHero] > 0) {
                    heroesHealth[randomHero] = heroesHealth[randomHero] + hp;
                    System.out.println("медик вылечил " + heroesAttackType[randomHero] + hp);
                }
            }
        }*/

        for (int i = 0; i < heroesAttackType.length; i++) {
            if (i == 3) {   //вот над этим решением потратил наверное 5-6 часов
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += 35;
                System.out.println("### Medic zahilil - " + heroesAttackType[i] + " ###");
                break;

            }
        }


    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________");
    }

    public static void mazohistGolem() {
        int uronOtBosa = bossDamage / 5;
        int jivHeroes = 0;

        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0) {
                    jivHeroes++;
                    heroesHealth[i] += uronOtBosa;
                }
            }
            heroesHealth[4] -= uronOtBosa * jivHeroes;
            System.out.println("урон голему " + uronOtBosa * jivHeroes + "+" + bossDamage);
        }

    }
}
