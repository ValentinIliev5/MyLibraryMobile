package uni.mylibmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {


    ScrollView scrollView;
    LinearLayout booksGallery;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);



        dbHandler = new DBHandler(BookListActivity.this);

        ArrayList<Book> books = dbHandler.getAllBooks();

        scrollView = findViewById(R.id.booksWrapper);
        booksGallery = findViewById(R.id.booksGallery);

        LayoutInflater inflater = LayoutInflater.from(this);

        View view;

        for (Book book : books)
        {
                view = inflater.inflate(R.layout.book, booksGallery, false);

                TextView titleTV = view.findViewById(R.id.BookTitleTV);
                TextView authorTV = view.findViewById(R.id.bookAuthorTV);
                TextView pagesTV = view.findViewById(R.id.bookPagesTV);
                Button deleteBtn = view.findViewById(R.id.bookDeleteButton);

                deleteBtn.setOnClickListener(arg0 ->{
                    dbHandler.deleteItem(book.getId());
                    finish();
                    startActivity(getIntent());
                });

                titleTV.setText(book.getTitle());
                authorTV.setText(book.getAuthor());
                pagesTV.setText("Pages: " + book.getNumberOfPages());

                titleTV.setOnClickListener((arg0->{
                    Intent i = new Intent(getApplicationContext(),UpdateBookActivity.class);
                    i.putExtra("Book_id",book.id);
                    startActivity(i);
                }));

                booksGallery.addView(view);
        }
    }
}