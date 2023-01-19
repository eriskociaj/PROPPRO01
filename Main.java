
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String playerName;
            String playerMove;
            String playerTurn;

            int playerHealth = 100;

            int bossHealth;
            int playerDamage;
            int bossDamage;
            int round = 1;
            int wins = 0;

            boolean continueGame = true;

            System.out.println(
                    "【 Enter the realm of fists and fury, where only the strongest and most skilled survive. Welcome to the fighting game. 】");
            System.out.println(
                    "【 This fighting game consists of 2 strong AI enemies and one final boss. \nYou are allowed to use your fist or feet in the first two rounds. \nThen things will get serious. All ready? 】");
            playerName = input.nextLine();

            while (continueGame) {

                System.out.println("Round " + round);
                System.out.println(""+ playerName +" health: " + playerHealth);

                // Set the boss's health based on the current round
                switch (round) {
                    case 1:
                        bossHealth = 50;
                        System.out.println("AI health: " + bossHealth);
                        break;
                    case 2:
                        bossHealth = 75;
                        System.out.println("AO health: " + bossHealth);
                        break;
                    case 3:
                        bossHealth = 100;
                        System.out.println("Boss health: " + bossHealth);
                        break;
                    default:
                        System.out.println("No more to fight!");
                        continueGame = false;
                        break;
                }
                if (!continueGame)
                    break;

                System.out.println("Who will attack first? (player/boss)");
                playerTurn = input.nextLine();

                if (playerTurn.equalsIgnoreCase("player")) {
                    System.out.println("What move would you like to make? (attack/defend)");
                    playerMove = input.nextLine();
                    if (playerMove.equalsIgnoreCase("attack")) {
                        playerDamage = (int) (Math.random() * 20) + 1;
                        bossHealth -= playerDamage;
                        System.out.println("You dealt " + playerDamage + " damage to the boss.");
                    } else if (playerMove.equalsIgnoreCase("defend")) {
                        System.out.println("You defended and took no damage this round.");
                    } else {
                        System.out.println("Invalid move! You lost your turn.");
                    }

                    // Boss's turn
                    bossDamage = (int) (Math.random() * 20) + 1;
                    playerHealth -= bossDamage;
                    System.out.println("The boss dealt " + bossDamage + " damage to you.");

                    if (bossHealth <= 0) {
                        System.out.println("You have defeated the boss! Congratulations!");
                        round++;
                        if (round > 3)
                            continueGame = false;
                    } else if (playerHealth <= 0) {
                        System.out.println("You have been defeated. Game Over.");
                        continueGame = false;
                    } else {
                        System.out.println("Do you want to continue the game? (yes/no)");
                        String decision = input.nextLine();
                        if (decision.equalsIgnoreCase("no")) {
                            continueGame = false;
                        }
                    }
                }

                else if (playerTurn.equalsIgnoreCase("boss")) {
                    // Enemy's turn
                    bossDamage = (int) (Math.random() * 20) + 1;
                    playerHealth -= bossDamage;
                    System.out.println("The enemy dealt " + bossDamage + " damage to you.");

                    // Player's turn
                    System.out.println("What move would you like to make? (attack/defend)");
                    playerMove = input.nextLine();
                    if (playerMove.equalsIgnoreCase("attack")) {
                        playerDamage = (int) (Math.random() * 20) + 1;
                        bossHealth -= playerDamage;
                        System.out.println("You dealt " + playerDamage + " damage to the enemy.");
                    } else if (playerMove.equalsIgnoreCase("defend")) {
                        System.out.println("You defended and took no damage this round.");
                    } else {
                        System.out.println("Invalid move! You lost your turn.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter either 'player' or 'enemy'.");
                }

                if (bossHealth <= 0) {
                    System.out.println("You have defeated the enemy! Congratulations!");
                }
            }
        }
    }
}
