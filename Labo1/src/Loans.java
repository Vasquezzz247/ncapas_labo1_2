import java.util.ArrayList;

public class Loans {
    private int idBook;
    private String startLoan;
    private String endLoan;
    private String personName;
    private String personDoc = "00000000-0";

    private ArrayList<Loans> list = new ArrayList<>();

    public Loans(int idBook, String startLoan, String endLoan, String personName, String personDoc) {
        this.idBook = idBook;
        this.startLoan = startLoan;
        this.endLoan = endLoan;
        this.personName = personName;
        this.personDoc = personDoc;
    }

    public void getItem(Loans loans) { list.add(loans); }

    public ArrayList<Loans> getItems() { return list; }
}




