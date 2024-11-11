import java.util.Scanner;

class SingleDigitException extends Exception{
    public SingleDigitException(String message){
        super(message);
    }
}

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter a number: ");
        int number=sc.nextInt();

        try{
            checkSingleDigit(number);
        }catch(SingleDigitException e){
            System.out.println("Caught exception: " +e.getMessage());
        }
        sc.close();
        }

        public static void checkSingleDigit(int number)throws SingleDigitException{
            if (number<-9 || number>9){
                throw new SingleDigitException("The number " + number + " has more than one digit.");
        } else {
            System.out.println("The number " + number + " is a single-digit number.");
        }
    }
}
