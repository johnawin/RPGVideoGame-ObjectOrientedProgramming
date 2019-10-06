package rpg.videogame;

public class Weapon {

    private String weaponName;
    private int attackDamageStat, defenseStat, levelRequirement;

    public Weapon() {
        this.weaponName = "Bare Hands";
        this.attackDamageStat = 0;
        this.defenseStat = 0;
        this.levelRequirement = 0;
    }

    public Weapon(String weaponName, int attackDamage, int defense, int levelRequirement) {
        this.weaponName = weaponName;
        this.attackDamageStat = attackDamage;
        this.defenseStat = defense;
        this.levelRequirement = levelRequirement;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getAttackDamageStat() {
        return attackDamageStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public void setAttackDamageStat(int attackDamageStat) {
        this.attackDamageStat = attackDamageStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

}
