import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String continueChoice;
            // enemies
            String[] enemies = { "Goblin", "Troll", "Dragon" };
            // enemy's health
            int[] enemyHealth = { 100, 150, 200 };
            // player's health
            int playerHealth = 100;
            // player's wins
            int playerWins = 0;
            // enemy's wins
            int enemyWins = 0;
            // game over flag
            boolean gameOver = false;
            // random number generator
            Random rand = new Random();
            try (// scanner for user input
            Scanner sc = new Scanner(System.in)) {
                while (!gameOver) {
                    // display menu
                    System.out.println("1. Fight");
                    System.out.println("2. Show scoreboard");
                    System.out.println("3. Exit");
                    System.out.print("What would you like to do? ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            // pick a random enemy
                            int enemyNum = rand.nextInt(enemies.length);
                            System.out.println("You are fighting a " + enemies[enemyNum] + "!");

                            while (playerHealth > 0 && enemyHealth[enemyNum] > 0) {
                                // player's turn
                                System.out.println("Your turn!");
                                System.out.println("1. Punch (1-20 damage)");
                                System.out.println("2. Kick (10-30 damage)");
                                System.out.println("3. Special move (25-50 damage)");
                                System.out.print("Choose an attack: ");
                                int attackChoice = sc.nextInt();
                                int playerDamage = 0;
                                if (attackChoice == 1) {
                                    playerDamage = rand.nextInt(20) + 1;
                                } else if (attackChoice == 2) {
                                    playerDamage = rand.nextInt(21) + 10;
                                } else if (attackChoice == 3) {
                                    playerDamage = rand.nextInt(26) + 25;
                                } else {
                                    System.out.println("Invalid choice, please try again.");
                                    continue;
                                }
                                // calculate hit chance based on player damage
                                int hitChance = 100 - playerDamage;
                                if (rand.nextInt(100) < hitChance) {
                                    System.out.println("You missed your attack!");
                                } else {
                                    enemyHealth[enemyNum] -= playerDamage;
                                    System.out
                                            .println("You dealt " + playerDamage + " damage to the " + enemies[enemyNum] + ".");
                                    System.out.println(enemies[enemyNum] + "'s health: " + enemyHealth[enemyNum]);
                                }

                                // enemy's turn
                                if (enemyHealth[enemyNum] > 0) {
                                    System.out.println(enemies[enemyNum] + "'s turn!");
                                    int missChance = rand.nextInt(100);
                                    if (missChance < hitChance) {
                                        int enemyAttackType = rand.nextInt(3) + 1; // 1 = punch, 2 = kick, 3 = special move
                                        int enemyAttackDamage = 0;
                                        if (enemyAttackType == 1) {
                                            enemyAttackDamage = rand.nextInt(5) + 15;
                                            System.out.println(
                                                    enemies[enemyNum] + " punched you for " + enemyAttackDamage + " damage.");
                                        } else if (enemyAttackType == 2) {
                                            enemyAttackDamage = rand.nextInt(10) + 20;
                                            System.out.println(
                                                    enemies[enemyNum] + " kicked you for " + enemyAttackDamage + " damage.");
                                        } else if (enemyAttackType == 3) {
                                            enemyAttackDamage = rand.nextInt(15) + 25;
                                            System.out.println(enemies[enemyNum] + " used a special move on you for "
                                                    + enemyAttackDamage + " damage.");
                                        }
                                        playerHealth -= enemyAttackDamage;
                                        System.out.println("Your health: " + playerHealth);
                                    } else {
                                        System.out.println(enemies[enemyNum] + " missed their attack.");
                                    }
                                }
                                if (playerHealth <= 0) {
                                    System.out.println("You lost the fight. Game over.");
                                    gameOver = true;
                                    enemyWins++;
                                } else if (enemyHealth[enemyNum] <= 0) {
                                    System.out.println("You won the fight!");
                                    playerWins++;

                                    System.out.println("Would you like to continue the game? (Y/N)");
                                    continueChoice = input.next();
                    
                   
                    if (continueChoice.equalsIgnoreCase("n")) {
                        System.out.println("Exiting game...");
                        System.exit(0);
                    } else if (continueChoice.equalsIgnoreCase("y")) {
                        // if player chooses 'Y' or 'y', reset the variables for next round
                        playerHealth = 100;
                        enemyHealth[enemyNum] = 100;
                        playerDamage = 0;
                        hitChance = 50;
                    }
                    break;
                                }
                            }
                        case 2:
                            System.out.println("Player wins: " + playerWins);
                            System.out.println("Enemy wins: " + enemyWins);
                            break;
                        case 3:
                            System.out.println("Exiting game...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
            }
        }
    }
}
