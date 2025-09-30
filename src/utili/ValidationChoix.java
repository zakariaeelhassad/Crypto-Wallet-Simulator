package utili;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ValidationChoix {


    private Scanner sc = new Scanner(System.in);

    public int validationChoix() {
        int choix = -1;
        boolean valide = false ;

        while(!valide){
            try {
                choix = sc.nextInt();
                sc.nextLine();
                valide = true ;
            }catch(InputMismatchException e) {
                System.out.println("°-° Entrée invalide -_- Veuillez saisir un nombre °-°");
                sc.nextLine();
                System.out.print("choix :");
            }
        }
        return choix ;
    }
}

