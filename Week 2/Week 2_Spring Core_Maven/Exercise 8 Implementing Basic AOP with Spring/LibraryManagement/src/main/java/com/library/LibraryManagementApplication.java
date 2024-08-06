package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class LibraryManagementApplication {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bs = (BookService) context.getBean("bookService");

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a Book Name: ");
        String bookName=sc.nextLine();
        bs.add(bookName);
        bs.displayBooks();
    }
}
