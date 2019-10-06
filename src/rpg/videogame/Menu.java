package rpg.videogame;

import java.util.Scanner;

public class Menu {

    ScreenManagement SM = new ScreenManagement();

    public void displayMenu(){
        System.out.println("(1) Create Player\n");
        System.out.println("(2) Create Monster\n");
        System.out.println("(3) Battle\n");
        System.out.println("(4) Display Player Info\n");
        System.out.println("(5) Recover\n");
        System.out.println("(6) Exit\n");
        System.out.print("Enter an option (1-6): ");
    }

    public void displayMonsters(){
        System.out.println("Choose a default Monster");
        System.out.println("(1) Angry Pigeon\n");
        System.out.println("(2) Ravaging Ghoul\n");
        System.out.println("(3) Unkillable Behemoth\n");
        System.out.println("(4) Create Your Own Monster\n");
        System.out.print("Enter an option (1-4): ");
    }

    public void recover(){
        System.out.println("You stop by an inn to rest up for the night.\n");
        System.out.println("You wake up the next day feeling reinvigorated!\n");

    }

    public void startMenu() {
        int choice;
        String name;
        Monster monster = null;
        Player player = null;
        Scanner kb = new Scanner(System.in);
        SM.clearScreen();
        System.out.println("Welcome to our demo.");
        while (1 != 0) {
            displayMenu();
            choice = kb.nextInt();
            switch (choice) {
                case 1:
                    SM.clearScreen();
                    System.out.print("Please enter a name: ");
                    name = kb.next();
                    player = new Player(name);
                    player.displayInfo();
                    System.out.println("\n");
                    break;
                case 2:
                    SM.clearScreen();
                    displayMonsters();
                    choice = kb.nextInt();
                    SM.clearScreen();
                    while (choice < 1 || choice > 4) {
                        SM.clearScreen();
                        System.out.println("Invalid choice.");
                        displayMonsters();
                        choice = kb.nextInt();
                        SM.clearScreen();
                    }
                    switch (choice) {
                        case 1:
                            monster = new Monster(Type.NEUTRAL, "Angry Pigeon", 3, 30, 30, 7, 0, ID.MONSTER);
                            monster.displayInfo();
                            break;
                        case 2:
                            monster = new Monster(Type.UNDEAD, "Ravaging Ghoul", 10, 50, 50, 20, 0, ID.MONSTER);
                            monster.displayInfo();
                            break;
                        case 3:
                            monster = new Monster(Type.FIRE, "Unkillable Behemoth", 99, 999, 999, 100, 100, ID.MONSTER);
                            monster.displayInfo();
                            break;
                        case 4:
                            System.out.print("Enter Name for Monster: ");
                            String tempName = kb.next();

                            System.out.print("Enter Level for Monster: ");
                            int templvl = kb.nextInt();

                            System.out.print("Enter Max HP for Monster: ");
                            int temphp = kb.nextInt();

                            System.out.print("Enter Current HP for Monster: ");
                            int currhp = kb.nextInt();
                            System.out.print("Enter Attack Damage for Monster: ");
                            int tempAD = kb.nextInt();

                            System.out.print("Enter Defensive Stats for Monster: ");
                            int tempD = kb.nextInt();
                            SM.clearScreen();
                            monster = new Monster(Type.NEUTRAL, tempName, templvl, temphp, currhp, tempAD, tempD, ID.MONSTER);
                            monster.displayInfo();
                            //Just Neutral types for now
                            break;
                        default:
                            break;
                    }
                    break;

                case 3:
                    SM.clearScreen();
                    Battle battle = new Battle();
                    battle.startBattle(player, monster);
                    SM.clearScreen();
                    break;
                case 4:
                    SM.clearScreen();
                    player.displayInfo();
                    break;
                case 5:
                    recover();
                    player.setCurrentHealthPoints(player.getMaxHealthPoints());
                    break;
                case 6:
                    SM.clearScreen();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    SM.clearScreen();
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
