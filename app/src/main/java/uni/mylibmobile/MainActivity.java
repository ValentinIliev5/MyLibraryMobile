package uni.mylibmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button addBookButton, openBooksListButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBookButton = findViewById(R.id.addBookButton);
        openBooksListButton = findViewById(R.id.booksListButton);

        addBookButton.setOnClickListener((arg0->{
            Intent i = new Intent(getApplicationContext(),AddBookActivity.class);
            startActivity(i);
        }));
        openBooksListButton.setOnClickListener((arg0->{
            Intent i = new Intent(getApplicationContext(), BookListActivity.class);
            startActivity(i);
        }));
    }
}