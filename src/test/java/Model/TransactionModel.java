package Model;

public class TransactionModel {
    private String from_account;
    private  String to_account;
    private  String amount;

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public TransactionModel(String from_account,String to_account,String amount){
        this.from_account=from_account;
        this.to_account=to_account;
        this.amount=amount;
    }
}
