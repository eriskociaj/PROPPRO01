import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args)
    {
        int dinHälsa = 100;

        int aiHälsa = 100;
        
        boolean game = true;

        while(game)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                int num1 =sc.nextInt();

                if(num1 == 1)
                {
                    System.out.println("Du slog din fienden!");
                    System.out.println(" ");

                    Random skada = new Random();

                    int attack;

                    attack = skada.nextInt(5);
                    
                }
            }

            catch (InputMismatchException e)
            {

            }
        }
    }
}