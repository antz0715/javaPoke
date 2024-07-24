import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Pokemon> pokemon;
    private int potions;
    private int pokeBalls;

    public Player(String name) {
        this.name = name;
        this.pokemon = new ArrayList<>();
        this.potions = 5;
        this.pokeBalls = 3;
    }

    public void addPokemon(Pokemon p) {
        pokemon.add(p);
    }

    public boolean useItem(String item, Pokemon p) {
        switch (item) {
            case "Potion":
                if (potions > 0) {
                    p.heal(20);
                    potions--;
                    System.out.println("Used Potion on " + p.getName() + ". " + p.getName() + " now has " + p.getCurrentHp() + " HP.");
                    return true;
                } else {
                    System.out.println("No Potions left!");
                    return false;
                }
            case "Poke Ball":
                if (pokeBalls > 0) {
                    pokeBalls--;
                    boolean captured = tryCapture(p);
                    if (captured) {
                        System.out.println("Captured " + p.getName() + "!");
                    } else {
                        System.out.println("Failed to capture " + p.getName() + ".");
                    }
                    return captured;
                } else {
                    System.out.println("No Poke Balls left!");
                    return false;
                }
            default:
                System.out.println("Invalid item!");
                return false;
        }
    }

    public boolean tryCapture(Pokemon wildPokemon) {
        Random random = new Random();
        double captureChance = random.nextDouble();
        double captureRate = wildPokemon.getRarity().equals("legendary") ? 0.1 : 0.5;
        if (captureChance <= captureRate) {
            addPokemon(wildPokemon);
            return true;
        }
        return false;
    }

    public Pokemon choosePokemon() {
        System.out.println("Choose a Pokemon:");
        for (int i = 0; i < pokemon.size(); i++) {
            Pokemon p = pokemon.get(i);
            System.out.println(i + ": " + p.getName() + " (HP: " + p.getCurrentHp() + "/" + p.getMaxHp() + ")");
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice >= 0 && choice < pokemon.size()) {
            return pokemon.get(choice);
        } else {
            System.out.println("Invalid choice. Defaulting to first Pokemon.");
            return pokemon.get(0);
        }
    }

    public List<Pokemon> getPokemonList() {
        return pokemon;
    }
}
