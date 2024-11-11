class Bank{
    protected double transactionCharge=5.0;

    public double getTransactionCharge(){
        return transactionCharge;
    }
}

class ICICI extends Bank{
    public double calculateTransactionCharge(){
        return transactionCharge*1.10;
    }
}

class HDFC extends Bank{
    public double calculateTransactionCharge(){
        return transactionCharge*1.15;
    }
}


public class Main {
    public static void main(String[] args){
        ICICI iciciBank=new ICICI();
        HDFC hdfcBank=new HDFC();
         System.out.println("Default Bank Transaction Charge: " + iciciBank.getTransactionCharge());
        System.out.println("ICICI Transaction Charge: " + iciciBank.calculateTransactionCharge());
        System.out.println("HDFC Transaction Charge: " + hdfcBank.calculateTransactionCharge());
        
    }
}
