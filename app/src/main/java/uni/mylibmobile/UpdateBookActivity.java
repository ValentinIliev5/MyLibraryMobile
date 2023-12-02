package uni.mylibmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateBookActivity extends AppCompatActivity {



    EditText titleET,authorET,publisherET,publisherPhoneET,publisherEmailET,numberOfPagesET;
    Button updateBookBtn;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        titleET = findViewById(R.id.updateBookTitleET);
        authorET = findViewById(R.id.editBookAuthorET);
        publisherET = findViewById(R.id.editBookPublisherET);
        publisherPhoneET = findViewById(R.id.editBookPublisherPhoneET);
        publisherEmailET = findViewById(R.id.editBookPublisherEmailET);
        numberOfPagesET = findViewById(R.id.editBookNOPET);
        updateBookBtn = findViewById(R.id.editBookButton);

        Intent intent = getIntent();

        dbHandler = new DBHandler(UpdateBookActivity.this);


        Book book = dbHandler.getAllBooks().get(intent.getIntExtra("Book_id",0));

        titleET.setText(book.getTitle());
        authorET.setText(book.getAuthor());
        publisherET.setText(book.getPublisher());
        publisherPhoneET.setText(book.getPublisherPhone());
        publisherEmailET.setText(book.getPublisherEmail());
        numberOfPagesET.setText(book.getNumberOfPages()+"");

        updateBookBtn.setOnClickListener((arg0->{

            book.setTitle(titleET.getText().toString());
            book.setAuthor(authorET.getText().toString());
            book.setPublisher(publisherET.getText().toString());
            book.setPublisherPhone(publisherPhoneET.getText().toString());
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

            dbHandler.updateBook(book.id,book);
            Intent i = new Intent(getApplicationContext(), BookListActivity.class);
            startActivity(i);
        }));

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