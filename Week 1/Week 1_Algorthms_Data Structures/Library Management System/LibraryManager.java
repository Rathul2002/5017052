import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String display() {
        return "BookID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

class LinearSearch {
    public Book linearSearch(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) 
                return book;
        }
        return null;
    }
}

class BinarySearch {
    public Book binarySearch(ArrayList<Book> books, String title) {
        int l=0,u=books.size()-1;
        while(l<=u){
            int mid=(u+l)/2;
            if (books.get(mid).getTitle().compareToIgnoreCase(title)==0)
                return books.get(mid);
            else if(books.get(mid).getTitle().compareToIgnoreCase(title)<0)
                l=mid+1;
            else if(books.get(mid).getTitle().compareToIgnoreCase(title)>0)
                u=mid-1;
        }
        return null;
    }
}

public class LibraryManager {
    public static void main(String[] args) {
        LinearSearch ls = new LinearSearch();
        BinarySearch bs = new BinarySearch();
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        // Adding some sample books and the question stated to assume the list is sorted
        books.add(new Book(4, "1984", "George Orwell"));
        books.add(new Book(1, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(2, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee"));

        System.out.println("Enter book title to search (linear search):");
        String title=sc.nextLine();
        Book foundBook=ls.linearSearch(books, title);
        if (foundBook!=null)
            System.out.println("Book found" );
        else
            System.out.println("Book not found");

        System.out.println("Enter book title to search (binary search):");
        title=sc.nextLine();
        foundBook=bs.binarySearch(books, title);
        if (foundBook!=null)
            System.out.println("Book found");
        else
            System.out.println("Book not found");

        sc.close();
    }
}
