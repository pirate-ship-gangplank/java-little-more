package digerking.ExceptionHandling;

class Amount {
    private String currency;
    private int amount;

    public Amount(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public void add(Amount that) throws CurrenciesDoNotMathException{

        if (!this.currency.equals(that.currency)) {
//            throw new Exception("Currencies Don't Match");
            throw new CurrenciesDoNotMathException("Message Test");
        }

        this.amount = this.amount + that.amount;
    }
}

class CurrenciesDoNotMathException extends Exception {
    public CurrenciesDoNotMathException(String message) {
        super(message);
    }
}



public class ThrowingException {
    public static void main(String[] args) throws CurrenciesDoNotMathException {
        Amount amount1 = new Amount("USD", 10);
        Amount amount2 = new Amount("EUR", 20);

        amount1.add(amount2);

        System.out.println(amount1);
    }
}
