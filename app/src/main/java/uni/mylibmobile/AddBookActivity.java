package uni.mylibmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookActivity extends AppCompatActivity {

    EditText titleET,authorET,publisherET,publisherPhoneET,publisherEmailET,numberOfPagesET;
    Button createBookBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titleET = findViewById(R.id.bookTitleET);
        authorET = findViewById(R.id.authorET);
        publisherET = findViewById(R.id.publisherET);
        publisherPhoneET = findViewById(R.id.publisherPhoneET);
        publisherEmailET = findViewById(R.id.publisherEmailET);
        numberOfPagesET = findViewById(R.id.nopET);

        dbHandler = new DBHandler(AddBookActivity.this);

        createBookBtn = findViewById(R.id.createBookBtn);

        createBookBtn.setOnClickListener(view -> {
            Book book = new Book();
            book.setId(0);
            book.setTitle(titleET.getText().toString());
            book.setAuthor(authorET.getText().toString());
            book.setPublisher(publisherET.getText().toString());
            String publisherEmail = publisherEmailET.getText().toString();
            if(EmailValidated(publisherEmail))
            {
                book.setPublisherEmail(publisherEmail);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                return;
            }
            String numOfP = numberOfPagesET.getText().toString();
            if(isNumber(numOfP)) {
                book.setNumberOfPages(Integer.parseInt(numOfP));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid Number Of Pages",Toast.LENGTH_SHORT).show();
                return;
            }
            book.setPublisherPhone(publisherPhoneET.getText().toString());


            dbHandler.addBook(book);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }
    private boolean EmailValidated(String email){
        final String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isNumber(String text){
        final String NUMBER_REGEX =
                "^[0-9]+$";

        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }
}