package com.Udm1.LibraryManagement;

public class BookService {

    public Book[] filterBooksByAuthor(Author authorToFilter, Book[] books) {

        if (books == null || authorToFilter == null) {
            System.out.println("No books provided");
            return new Book[0];
        }
        Book[] booksFilteredByAuthor = new Book[books.length];
        int j = 0;

        for (int i = 0; i < books.length; i++ ) {

            authorCheck: for (Author bookAuthor : books[i].authors) {

                if (bookAuthor.equals(authorToFilter)
                        && j < booksFilteredByAuthor.length) {
                    booksFilteredByAuthor[j] = books[i];
                    j++;
                    break authorCheck;
                }
            }
        }
        return clearBookArray(booksFilteredByAuthor);
    }

    public Book[] filterBooksByPublisher(Publisher publisherToFilter, Book[] books) {

        if (books == null || publisherToFilter == null) {
            System.out.println("No books provided");
            return new Book[0];
        }
        Book[] booksFilteredByPublisher = new Book[books.length];
        int j = 0;


        for (Book book : books) {

            if (publisherToFilter.equals(book.publisher)) {
                booksFilteredByPublisher[j] = book;
                j++;
            }
        }
        return clearBookArray(booksFilteredByPublisher);
    }

    //method keeps books with publishing year inclusively
    public Book[] filterBooksAfterSpecifiedYear (int yearFromInclusively, Book[] books) {

        if (books == null || yearFromInclusively <= 0) {
            System.out.println("No books provided");
            return new Book[0];
        }
        Book[] booksFilteredAfterSpecifiedYear = new Book[books.length];
        int j = 0;

        for (Book book : books) {

            if (book.publishingYear >= yearFromInclusively) {
                booksFilteredAfterSpecifiedYear[j] = book;
                j++;
            }
        }
        return clearBookArray(booksFilteredAfterSpecifiedYear);
    }

    public Book[] clearBookArray(Book[] books) {
        int j = 0;
        Book[] clearedBooks;

        if (books == null){
            System.out.println("No books to clear");
            return new Book[0];
        }
        for (int i = 0; i < books.length; i++) {

            if (books[i] == null) {
                j++;
            }
        }
        clearedBooks = new Book[books.length-j];
        j = 0; //j reset
        for (int i = 0; i < books.length; i++) {

            if (books[i] != null) {
              clearedBooks[j] = books[i];
              j++;
            }
        }
        return clearedBooks;
    }

}
