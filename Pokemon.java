public class Pokemon {
    private String name;
    private String type;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private String rarity;

    public Pokemon(String name, String type, int hp, int attack, int defense, String rarity) {
        this.name = name;
        this.type = type;
        this.maxHp = hp;
        this.currentHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.rarity = rarity;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void takeDamage(int damage) {
        currentHp = Math.max(0, currentHp - damage);
    }

    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
    }

    public void viewStats() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("HP: " + currentHp + "/" + maxHp);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Rarity: " + rarity);
    }

    public String getName() {
        return name;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getRarity() {
        return rarity;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
