package rpg.videogame;

public class Player extends GameObject {

    private int currentEXP;
    private int stamina;
    private int toLevelUp;
    private Weapon equippedWeapon;

    //This constructor is called when using only a name to create a Player
    public Player(String name) {
        super(name);
        this.currentEXP = 0;
        this.stamina = 100;
        this.toLevelUp = 10;
        this.equippedWeapon = new Weapon();
    }

    public Player(String name, int exp, int stamina, int level, int maxHealthPoints, int currentHealthPoints, int attackDamageStat, int defenseStat, ID id) {
        super(name, level, maxHealthPoints, currentHealthPoints, attackDamageStat, defenseStat, id);
        this.currentEXP = exp;
        this.stamina = stamina;
        this.equippedWeapon = new Weapon();
    }

    public void levelUp() {
        //update the amount needed to level up next
        toLevelUp = getLevel() * 10;
        int currLevel = getLevel();
        //move onto next level
        this.setLevel(++currLevel);

        //increase player's stats for leveling up
        //+5 for HP
        setCurrentHealthPoints(getCurrentHealthPoints() + 5);
        setMaxHealthPoints(getMaxHealthPoints() + 5);

        //+2 for Attack
        setAttackDamageStat(getAttackDamageStat() + 2);

        //+1 for Defense
        setDefenseStat(getDefenseStat() + 1);

        System.out.println("Congratualtions " + this.getName() + ", You just leveled up to " + this.getLevel() + "!!");

        //new Weapon every 5 levels
        switch (this.getLevel()) {
            case 5: {
                Weapon newWeapon = new Weapon("Wooden Sword", 5, 5, 5);
                this.equipWeapon(newWeapon);
                break;
            }
            case 10: {
                Weapon newWeapon = new Weapon("Steel Blade", 30, 5, 10);
                this.equipWeapon(newWeapon);
                break;
            }
            case 15: {
                Weapon newWeapon = new Weapon("Excalibur", 150, 5, 15);
                this.equipWeapon(newWeapon);
                break;
            }
            default:
                break;
        }

    }

    public void gainxp(int monsterLevel, int damageDealt) {
        int tempEXP = currentEXP;
        int gainedEXP = monsterLevel * damageDealt;
        currentEXP += gainedEXP;
        System.out.println("Gained " + gainedEXP + " EXP ");
        System.out.println("Total EXP Now " + currentEXP + " EXP ");

        //Keep looping through progressive level as long as have enough exp
        while (gainedEXP >= toLevelUp) {
            gainedEXP -= toLevelUp;
            levelUp();
        }

        //Leftover exp counts towards next level
        toLevelUp -= gainedEXP;

    }

    public void staminaUp() {

    }

    public void equipWeapon(Weapon weapon) {
        if (this.getLevel() >= weapon.getLevelRequirement()) {
            this.unequipWeapon();
            System.out.println("You found and equipped a " + weapon.getWeaponName() + "!");
            this.equippedWeapon = weapon;
            this.setAttackDamageStat(this.getAttackDamageStat() + equippedWeapon.getAttackDamageStat());
            this.setDefenseStat(this.getDefenseStat() + equippedWeapon.getDefenseStat());
            System.out.println("Your attack is now: " + this.getAttackDamageStat() + "!");
            System.out.println("Your defense is now: " + this.getDefenseStat() + "!");
        } else {
            System.out.println("Not high enough level! ");
            System.out.println("\tCurrent level: " + this.getLevel());
            System.out.println("\tRequired level: " + this.equippedWeapon.getLevelRequirement());
        }
    }

    public void unequipWeapon() {
        this.equippedWeapon = new Weapon();
        this.setAttackDamageStat(this.getAttackDamageStat() - equippedWeapon.getAttackDamageStat());
        this.setDefenseStat(this.getAttackDamageStat() - equippedWeapon.getDefenseStat());
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Current EXP: " + this.getCurrentEXP());
        System.out.println("EXP to level up: " + this.getToLevelUp());
        System.out.println("----------------");
    }

    public int getCurrentEXP() {
        return currentEXP;
    }

    public int getStamina() {
        return stamina;
    }

    public int getToLevelUp() {
        return toLevelUp;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setCurrentEXP(int currentEXP) {
        this.currentEXP = currentEXP;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setToLevelUp(int toLevelUp) {
        this.toLevelUp = toLevelUp;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

}