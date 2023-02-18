import java.util.Scanner;
import java.util.Random;

public class fighting_game{
    // GAME VARIABLES
    static Scanner sc = new Scanner(System.in);
    static Random randomNum = new Random();
    
    static int customMode = 0;

    // PLAYER VARIABLES
    static int playerMove;
    static int playerWins;
    
    // ENEMY VARIABLES
    static int enemyWins;
    static int[] customEnemyHealth = new int[2];

    public static void main (String []args){
        gamemenu();
    }

    public static void gamemenu(){
        System.out.println(" Welcome to an ordinarie fight game. ");
        System.out.println("");
        while(true){
            //Display Mneu
            System.out.println("1. Fight");
            System.out.println("2. Show scoreboard");
            System.out.println("3. Enter custom enemy health");
            System.out.println("4. Exit");
            System.out.print("What would you like to do? ");
            /*
            parseint, menining it will trigger the try catch and in this way preventing
            miss inputs and crashes.
            If player enetrs antything but a number the system will crash because of the
            */ 
            try{
                String input = sc.nextLine();
                playerMove = Integer.parseInt(input);
            }catch(Exception e){
                System.out.println("\n\n\nInvalid choice, please try again.");
                continue;
            }
            /*
            Letting the player chosse his action with case switch and a try catch to make
            sure that the player doesnt enetr a faulty input and crashes th program.
             */
            switch(playerMove){
                case 1:
                //reseting the scores so that the tally doesnt go up infinitivly
                    fight();
                    break;
                case 2:
                    scoreboard();
                    break;
                case 3:
                    //if the player doesnt enetr the custom part than we will run the default health by making customMode = 1.
                    customMode = 1;
                    customHealth();
                    break;
                case 4:
                    System.out.println("Exiting game...");
                    System.exit(0);
                default:
                // This also corrects the user, so if they enetr a number but not the correct
                // one we tell them that aswell to keep t he system from not crahsing.
                System.out.println("\n\n\nInvalid choice, please try again.");
                continue;
            }
        }
    }

    static void fight(){
        int [] normalEnemyHealthList = {100, 150, 200};
        int [] enemyHealthList = new int[3];

        //Checking if the player has set his own enemy health if so we enter them in a new array to be used in the game. 
        if(customMode == 0){
            for (int i = 0; i < normalEnemyHealthList.length; i++) {
                enemyHealthList[i] = normalEnemyHealthList[i];
            }
        }else if(customMode == 1){
            for (int i = 0; i < customEnemyHealth.length; i++) {
                enemyHealthList[i] = customEnemyHealth[i];
            }
        }

        //Game variables 
        String [] enemyNameList = {"AI", "AO", "BOSS"};
        double [] enemyMultiplierList = {0.80, 1.1, 1.4};
        String enemyName = "lol";
        int enemyHealth = 0;
        double enemyAttackMultiplier = 0.0;
        int playerHealth = 150;
        int regainHealth;
        int currentRoundIndex = -1;
        
        boolean running = true;
        //Here is the porper game logic to run the attack algorithm
        while(running){
            currentRoundIndex++;
            for(; currentRoundIndex <= 2;){
                enemyName = enemyNameList[currentRoundIndex];
                enemyHealth = enemyHealthList[currentRoundIndex];
                enemyAttackMultiplier = enemyMultiplierList[currentRoundIndex];
                break;
            }
            if(currentRoundIndex == 3){
                //If the player has killed all of the enemys than we ask if they want to quit or restart the whole game to maybe customize the health.
                System.out.println("\n\n\n Congratulations you have deteated all three enemys!! ");
                System.out.println("!! You have completed the game !!");
                while (true) {
                    System.out.print("\nWould you like to play again?(Y/N):");
                    String playerMove = sc.next();
                    // we check if the player wants to continue playing.
                    if (playerMove.equalsIgnoreCase("n")) {
                        System.out.println("Exiting game...");
                        System.exit(0);
                    } else if (playerMove.equalsIgnoreCase("y")) {
                        playerWins = 0;
                        enemyWins = 0;
                            gamemenu();
                    } else {
                        System.out.println("\n\n\nInvalid choice, please try again.");
                        continue;
                    }
                }
            }
            System.out.println("\nYou are fighting a "+enemyName+"!!\n");
            //We set every variable to match with eachother by using currentRoundIndex, in this way every enemy will be the same and have those specific stats.
            while(true){
                /*
                We start of with players attack, and from it we return enemyHealth because its theirs that is changing.
                We also input some parameters so that the method can use our variables created in the fight method. 
                */
                enemyHealth = playerAttack(enemyHealth, enemyName);
                //We check if the enemys
                if(enemyHealth <=0){
                    System.out.println("You won the fight!");
                    playerWins++;
                    // We add this break so that when the enemy die it doesnt continue and let it attack again cause than it would be attacking as a dead character and we dont want that.
                    break;
                }
                
                /*We than do enemys attack 
                enemys attack also has parameters one extra aswell with the multiplier that gives the enemy a more balance and chalange for the game.
                */
                playerHealth = enemyAttack(playerHealth, enemyName, enemyAttackMultiplier);
                //We check if the player dies or not
                if(playerHealth <= 0){
                    System.out.println("You lost the fight. Game over.");
                    enemyWins++;
                    //The same reasn for having a break here.
                    break;
                }
            }
            //A simple if you want to continue question.
            while(true){
                System.out.println("\nWould you like to continue the game? (Y/N)");
                String playerMove = sc.nextLine();
                if (playerMove.equalsIgnoreCase("n")) {
                    //If players chosse no we simply shut down the whole program
                    System.out.println("Exiting game...");
                    System.exit(0);
                }else if(playerMove.equalsIgnoreCase("y")) {
                    /*
                     * Ofcourse if the player says yes we want to know if the player just died or to continue to the next enemy.
                     * We check this by viewing our players health to see if its dead or not.
                     */
                    //If our player wants to continue we give them back 100hp so they can continue fighting and than go back up to the loop to make us face the next enemy.
                    if(playerHealth > 0){
                        regainHealth = 100;
                        playerHealth += regainHealth;
                        break;
                    /*
                    If our player is already dead than they mean that they want to restart and do it all again. all of the varibales are in the top of this method 
                    meaning everything will reset as normal.
                     */
                    }else if(playerHealth <= 0){
                        gamemenu();
                    }
                }else {
                    System.out.println("\n\n\nInvalid choice, please try again.");
                    continue;
                }
            }
        }

    }

    static int playerAttack(int enemyHealth, String enemyName){
        //correct scope variables
        int playerAttackDamage = 0;
        int hitChance;
        int attackChoice;
        while(true){

            // player's turn
            System.out.println("Make your attack!");
            System.out.println("1. Punch (1-20 damage)");
            System.out.println("2. Kick (10-30 damage)");
            System.out.println("3. A Special move (25-50 damage)");
            System.out.print("Choose an attack: ");
            //checking if the player enterd the correct type of input.
            try{
                String input = sc.nextLine();
                attackChoice = Integer.parseInt(input);
            }catch(Exception e){
                System.out.println("\n\n\nInvalid choice, please try again.");
                continue;
            }
    
            //Player's attack damages
            if(attackChoice == 1){
                playerAttackDamage = randomNum .nextInt(20) + 1;
                System.out.println("\nYou chosse Punch attack");
                hitChance = 90;
            }else if(attackChoice == 2){
                playerAttackDamage = randomNum.nextInt(21) + 10;
                System.out.println("\nYou chosse Kick attack");
                hitChance = 75;
            }else if(attackChoice == 3){
                playerAttackDamage = randomNum.nextInt(26) + 25;
                System.out.println("\nYou chosse Special move attack");
                hitChance = 45;
            }else{
                System.out.println("\n\n\nInvalid choice, please try again.\n");    
                continue;
            }

            //Calculating hit chance
            
            if ( randomNum.nextInt(100) < hitChance) {
                enemyHealth -= playerAttackDamage;
                //Stopping from hp going below zero
                enemyHealth = Math.max(0, enemyHealth);
                System.out.println("You dealt " + playerAttackDamage + " damage to the " + enemyName + ".");
                System.out.println(enemyName + "'s health: " + enemyHealth);
            }else{
                System.out.println("You missed your attack!");
            }
            break;
        }
    return enemyHealth;
    }


    static int enemyAttack(int playerHealth, String enemyName, double enemyAttackMultiplier){
        int enemyAttackDamage = 0;
        int hitChance = 0;
        while(true){
            //Enemy's turn
            System.out.println("\n"+enemyName+"'s turn!");
            int attackChoice = randomNum.nextInt(3) +1; // 1 = punch, 2 = kick, 3 = special move
            //taking the random number it created and doing its action.
            if(attackChoice == 1){
                enemyAttackDamage = (int)Math.round(enemyAttackMultiplier*(randomNum.nextInt(20) + 1));
                System.out.println(enemyName + " punched you for " + enemyAttackDamage+ " damage.");
                hitChance = 80;
    
            }else if(attackChoice == 2){
                enemyAttackDamage = (int)Math.round(enemyAttackMultiplier*(randomNum.nextInt(21) + 10));
                System.out.println(enemyName + " kicked you for " + enemyAttackDamage + " damage.");
                hitChance = 65;
                
            }else if(attackChoice == 3){
                enemyAttackDamage = (int)Math.round(enemyAttackMultiplier*(randomNum.nextInt(26) + 25   ));
                System.out.println(enemyName+ " used a special move on you for "+ enemyAttackDamage + " damage.");
                hitChance = 35;
            }
            if ( randomNum.nextInt(100) < hitChance) {
                playerHealth -= enemyAttackDamage;
                playerHealth = Math.max(0,playerHealth );
                System.out.println("Your health: " + playerHealth+"\n");
                
            }else{
                System.out.println("You missed your attack!");
            }
            break;
        }
        return playerHealth;
    }

    static void scoreboard(){
        // A very simple scoreboard where the scores are simply just tracked of the game.
        System.out.println("\n\tSCOREBOARD");
        System.out.println("Player Wins: "+playerWins);
        System.out.println("Enemy Wins: "+enemyWins);
        System.out.print("\nPress enter to return");
        sc.nextLine();
        System.out.println("\n\n\n"); 
    }
    static void customHealth(){
        // This custom enemy health is a very nice way to let the player have more of a custmizable feeling to the game.
        System.out.println("\n\n\nEnter 3 enemy health");
        System.out.println("You will face them from weakest to strongest.");
        System.out.print("Enter the health's [# # #]: ");
        while(true){
            try{
                /*
                 * Inside this little try we are deconstructing the players input to extract the numbers they have given us
                 * its done by using indexOf that uses a start place than it searches for the position of the index where it has a space. it takes teh number inbetween the start index and end idex
                 * to tha substring it to another variable where it than gets parseit than in to a array. It continues doing to until every number has been extracted. 
                 * I made it a try ctach so that if the player inputed too many numbers or a string than the system will crash than it will do what is says in cathc and restrat it.
                 */
                String input = sc.nextLine();
                int startIndex = 0;
                for (int i = 0; i < 2; i++) {
                    int endIndex = input.indexOf(" ", startIndex);
                    if (endIndex == -1) {
                        endIndex = input.length();
                    }
                    String numberString = input.substring(startIndex, endIndex);
                    customEnemyHealth[i] = Integer.parseInt(numberString);
                    startIndex = endIndex + 1;
                }
                break;
            }catch (Exception e){
                System.out.println("\n\n\nplease input in the right format");
                System.out.print("Enter the health's [# # #]: ");
                continue;
            }
        }
        /*We than use a bubble sort algorithm and in this way sorting the array with the players given enemy health and make them from smallest to biggest.
        *Its done by ,comparing the two numbers with echother and if its bigger than we will put it in a temporary variable where it than will compare again. 
        it will atlast make the sallest number go to the index 0 and the biggest to the last index.
        */
        for (int i = 0; i < customEnemyHealth.length - 1; i++) {
            for (int j = 0; j < customEnemyHealth.length - i - 1; j++) {
                if (customEnemyHealth[j] > customEnemyHealth[j + 1]) {
                    int temp = customEnemyHealth[j];
                    customEnemyHealth[j] = customEnemyHealth[j + 1];
                    customEnemyHealth[j + 1] = temp;
                }
            }
        }
        System.out.println("\n\n\n");
    }
}

