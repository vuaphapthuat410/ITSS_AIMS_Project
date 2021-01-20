package controllers.order;

public class CreditCardAPI {
    private String account = "123456abc";
    private String password = "123456";
    private int amount = 1000000;

    //tru tien
    public boolean charge(String account, String password, int amount){
        if (this. account == account && this.password == password){
            if (amount <= this.amount)
                this.amount = this.amount - amount;
            else
                return false;
            return true;
        }
        return false;
    }

    // hoan lai tien khi huy don hang
    public void refund(int amount){
        this.amount = this.amount + amount;
    }

    public int getAmount() {
        return amount;
    }
}
