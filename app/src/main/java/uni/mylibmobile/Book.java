package uni.mylibmobile;

public class Book {
    protected String title, author, publisher, publisherEmail, publisherPhone;
    protected int numberOfPages,id;

    public Book(){
        this.id = 0;
        this.title="";
        this.author = "";
        this.publisher = "";
        this.publisherEmail = "";
        this.publisherPhone = "";
        this.numberOfPages = 0;
    }

    public Book(int ID, String Title, String Author, int NumberOfPages, String Publisher, String PublisherEmail, String PublisherPhone){
        this.id = ID; this.title = Title; this.author = Author;  this.numberOfPages = NumberOfPages; this.publisher = Publisher;
        this.publisherEmail = PublisherEmail; this.publisherPhone = PublisherPhone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
