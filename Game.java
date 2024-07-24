import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void combat(Player player, Pokemon wildPokemon) {
        Scanner scanner = new Scanner(System.in);
        Pokemon activePokemon = player.choosePokemon();
        System.out.println("A wild " + wildPokemon.getName() + " appeared!");

        while (activePokemon.isAlive() && wildPokemon.isAlive()) {
            System.out.println("\n" + activePokemon.getName() + " (HP: " + activePokemon.getCurrentHp() + ") vs " + wildPokemon.getName() + " (HP: " + wildPokemon.getCurrentHp() + ")");
            System.out.print("Choose action: (A)ttack, (I)tem, (S)wap, (V)iew Stats: ");
            String action = scanner.next().toLowerCase();

            switch (action) {
                case "a":
                    int damage = Math.max(1, activePokemon.getAttack() - wildPokemon.getDefense());
                    wildPokemon.takeDamage(damage);
                    System.out.println(activePokemon.getName() + " attacked " + wildPokemon.getName() + " for " + damage + " damage.");
                    break;
                case "i":
                    System.out.print("Choose item: (P)otion, (B)all: ");
                    String item = scanner.next().toLowerCase();
                    if (item.equals("p")) {
                        player.useItem("Potion", activePokemon);
                    } else if (item.equals("b")) {
                        if (player.useItem("Poke Ball", wildPokemon)) {
                            return;
                        }
                    }
                    break;
                case "s":
                    activePokemon = player.choosePokemon();
                    System.out.println("Swapped to " + activePokemon.getName() + ".");
                    continue;
                case "v":
                    activePokemon.viewStats();
                    continue;
                default:
                    System.out.println("Invalid action.");
            }

            if (wildPokemon.isAlive()) {
                int wildDamage = Math.max(1, wildPokemon.getAttack() - activePokemon.getDefense());
                activePokemon.takeDamage(wildDamage);
                System.out.println(wildPokemon.getName() + " attacked " + activePokemon.getName() + " for " + wildDamage + " damage.");
            }
        }

        if (!activePokemon.isAlive()) {
            System.out.println(activePokemon.getName() + " fainted!");
        }
        if (!wildPokemon.isAlive()) {
            System.out.println(wildPokemon.getName() + " fainted! Battle over.");
        }
    }

    public static Pokemon randomEncounter() {
        Pokemon[] pokemonList = {
            new Pokemon("Charmander", "Fire", 39, 52, 43, "common"),
            new Pokemon("Bulbasaur", "Grass", 45, 49, 49, "common"),
            new Pokemon("Squirtle", "Water", 44, 48, 65, "common"),
            new Pokemon("Mewtwo", "Psychic", 106, 110, 90, "legendary")
        };
        Random random = new Random();
        return pokemonList[random.nextInt(pokemonList.length)];
    }

    public static void main(String[] args) {
        Player player = new Player("Ash");
        Pokemon pikachu = new Pokemon("Pikachu", "Electric", 35, 55, 40, "common");
        Pokemon jigglypuff = new Pokemon("Jigglypuff", "Fairy", 35, 55, 40, "common");
        player.addPokemon(pikachu);
        player.addPokemon(jigglypuff);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            Pokemon wildPokemon = randomEncounter();
            combat(player, wildPokemon);
            System.out.print("Continue with another encounter? (y/n): ");
            if (!scanner.next().equalsIgnoreCase("y")) {
                break;
            }
        }
    }
}
