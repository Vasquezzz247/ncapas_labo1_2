import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Book {
    private String id;
    private String title;
    private String author;
    private String type;
    private String state = "available";

    private ArrayList<Book> list = new ArrayList<>();

    public Book(){}

    public Book(String id, String title, String author, String type, String state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.state = state;
    }

    public void addItemToList(Book book){
        list.add(book);
    }

    public ArrayList<Book> getList(){
        return list;
    }

    public Optional<Book> getItemBook(String id) {
        return list.stream().filter(book -> book.id == id).findFirst();
    }

    public ArrayList<Book> findByState(String state) {
        return (ArrayList<Book>) this.list.stream()
                .filter(book -> book.state.equals(state))
                .collect(Collectors.toList());
    }

    public void lendBook(String id) {
        list.stream()
                .filter(book -> book.id == id)
                .findFirst()
                .ifPresent(book -> book.state = "borrowed");
    }

    public boolean checkBook(String id) {
        return this.list.stream().anyMatch(book -> book.id == id && book.state.equals("borrowed"));
    }
}
