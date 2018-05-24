import java.util.Scanner;

public class Validator {

    /**
     * Prompts the user for a string entry and validates that the input is not empty. If an empty string is received, the
     * user will be prompted to enter a new string entry until valid input is received.
     *
     * @return the validated string
     */
    public static String stringValidator(){
        String validatedString = "";
        boolean isValid = false;
        Scanner input = new Scanner(System.in);
        while(!isValid) {
            validatedString = input.nextLine();
            if(validatedString.isEmpty() || validatedString.contains(" ")){
                System.out.printf("String cannot be empty. \nPlease try again : ");
            }
            if (!validatedString.isEmpty() && !validatedString.contains(" ")){
                isValid = true;
                return validatedString;
            }
        }
        return validatedString;
    }

    /**
     * Prompts the user for an integer value and validates that the integer is within the specified boundaries.  If the
     * input is outside the specified range, the user will be prompted for a new integer value until valid input is received.
     *
     * @param minimum the minimum integer value accepted for input
     *
     * @param maximum the maximum integer value accepted for input
     *
     * @return the validated integer value
     */
    public static int integerValidator(int minimum, int maximum){
        int validatedInteger = 0;
        boolean isValid = false;
        Scanner input = new Scanner(System.in);
        while(!isValid){
            if(input.hasNextInt()){
                validatedInteger = input.nextInt();
                if(validatedInteger >= minimum && validatedInteger <= maximum){
                    isValid = true;
                } else {
                    System.out.printf("Input must be a number between %d and %d", minimum, maximum);
                    System.out.printf("\nPlease try again : ");
                }
            } else if(input.hasNext()){
                input.nextLine();
                System.out.printf("Input must be a number between %d and %d", minimum, maximum);
                System.out.printf("\nPlease try again : ");
            } else {
                System.out.printf("Input must be a number between %d and %d", minimum, maximum);
                System.out.printf("\nPlease try again : ");
            }
        }
        return validatedInteger;
    }
}
