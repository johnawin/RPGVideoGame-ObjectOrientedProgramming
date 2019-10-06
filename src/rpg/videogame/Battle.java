package rpg.videogame;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Battle {
    private int choice;
    private final Random rand = new Random();
    private final Scanner kb = new Scanner(System.in);
    private final ScreenManagement SM = new ScreenManagement();

    private int calculateDamage(GameObject obj1, GameObject obj2) {
        int totalDamage;
        int newCurrentHealth;
        if (obj1.getAttackDamageStat() <= obj2.getDefenseStat()) {
            totalDamage = 0;
            System.out.println(obj1.getName() + "'s strength is too weak!");
        } else {
            totalDamage = obj1.getAttackDamageStat() - obj2.getDefenseStat();
        }
        newCurrentHealth = obj2.getCurrentHealthPoints() - totalDamage;
        System.out.println(obj1.getName() + " struck " + obj2.getName() + " for " + totalDamage + " Damage!");
        return newCurrentHealth;
    }

    private int calculateDamage(GameObject obj1, GameObject obj2, int rng) {
        int totalDamage;
        int newCurrentHealth;
        if (obj1.getAttackDamageStat() <= obj2.getDefenseStat()) {
            totalDamage = 0;
            System.out.println(obj1.getName() + "'s strength is too weak!");
        } else {
            totalDamage = obj1.getAttackDamageStat() - obj2.getDefenseStat();
            totalDamage = totalDamage + (totalDamage / 4);
        }
        newCurrentHealth = obj2.getCurrentHealthPoints() - totalDamage;
        System.out.println(obj1.getName() + " struck " + obj2.getName() + " for " + totalDamage + " Damage!");
        return newCurrentHealth;
    }

    private void rngSkill(GameObject obj1, GameObject obj2){
        System.out.println("Action was a success!");
        obj2.setCurrentHealthPoints(calculateDamage(obj1, obj2, 1));

    }

    private int makeChoice(Player player, Monster monster) {
        System.out.println("What will you do?");
        System.out.println("(1) Attack\n");
        System.out.println("(2) RNG Skill\n");
        System.out.println("(3) Run Away\n");
        System.out.print("Enter an option (1-3):");
        choice = kb.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please enter a valid option.");
            makeChoice(player, monster);
            choice = kb.nextInt();
        }
        switch (choice) {
            case 1:
                monster.setCurrentHealthPoints(calculateDamage(player, monster));
                break;
            case 2:
                System.out.println("You attempted to channel a powerful hidden technique at the cost of missing...");
                if (rand.nextBoolean()) {
                    rngSkill(player, monster);
                } else {
                    System.out.println("Action failed!");
                }
                SM.waitForInput();
                break;
            case 3:
                System.out.println("Frightened for your life, you decided turn around and run as fast as you can!");
                SM.waitForInput();
                System.out.println("...");
                SM.waitForInput();
                System.out.println("You escaped!");
                SM.waitForInput();
                //resets the monster back to full hp
                monster.setCurrentHealthPoints(monster.getMaxHealthPoints());
                break;
            default:
                break;
        }
        return choice;
    }

    private void monsterChoice(Monster monster, Player player) {
        if (rand.nextBoolean()){
            player.setCurrentHealthPoints(calculateDamage(monster, player));
        } else {
            System.out.println(monster.getName() + " attempted to channel a powerfull hidden technique at the cost of missing...");
            if (rand.nextBoolean()){
                rngSkill(monster, player);
            } else {
                System.out.println("Action failed!");
            }
        }


    }

    protected void startBattle(Player player, Monster monster) {
        boolean battle = true;
        System.out.println("You sense a threatening presence drawing closer to you from up ahead.");
        SM.waitForInput();
        System.out.println("Unsure of what the shadowy figure is yet, you prepare your weapon in hand, knowing your life is in imminent danger.");
        SM.waitForInput();
        System.out.println("Look out! An " + monster.getName() + " is coming your way!");
        SM.waitForInput();
        while (battle) {
            player.displayInfo();
            monster.displayInfo();
            if (makeChoice(player, monster) != 3) {
                if (monster.isAlive(monster) == true){
                    monsterChoice(monster, player);
                }
                SM.waitForInput();
                if (player.isAlive(player) == false) {
                    System.out.println("Game Over. You died.");
                    SM.waitForInput();
                    battle = false;
                } else if (monster.isAlive(monster) == false) {
                    System.out.println("With one final swing of your weapon, you struck the killing blow to end the" + monster.getName() + "'s life.");
                    SM.waitForInput();
                    System.out.println("You feel a surge of energy passing through your entire body.");
                    SM.waitForInput();
                    player.gainxp(monster.getLevel(), monster.getMaxHealthPoints());
                    SM.waitForInput();
                    battle = false;
                    //resets the monster back to full hp
                    monster.setCurrentHealthPoints(monster.getMaxHealthPoints());
                }
            } else {
                battle = false;
            }
            SM.clearScreen();
        }
    }
}
