package uni.mylibmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context)
    {
        super(context,"MyLibrary",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS BOOKS ("+
                "ID INTEGER PRIMARY KEY, " +
                "TITLE TEXT NOT NULL, " +
                "AUTHOR TEXT NOT NULL, "+
                "PUBLISHER TEXT NOT NULL, "+
                "PUBLISHER_EMAIL TEXT NOT NULL, "+
                "PUBLISHER_PHONE TEXT NOT NULL, "+
                "NUMBER_OF_PAGES INTEGER NOT NULL) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS BOOKS");
        onCreate(db);
    }

    public void addBook(Book book)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID",getLastID()+1);
        values.put("TITLE",book.getTitle());
        values.put("AUTHOR",book.getAuthor());
        values.put("PUBLISHER",book.getPublisher());
        values.put("PUBLISHER_EMAIL",book.publisherEmail);
        values.put("PUBLISHER_PHONE",book.publisherPhone);
        values.put("NUMBER_OF_PAGES",book.getNumberOfPages());

        db.insert("BOOKS",null,values);
    }
    public ArrayList<Book> getAllBooks()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM BOOKS",null);

        ArrayList<Book> books = new ArrayList<Book>();

        if(cursor.moveToFirst())
        {
            do{
                Book book = new Book();

                book.setId(cursor.getInt(0));

                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPublisher(cursor.getString(3));
                book.setPublisherEmail(cursor.getString(4));
                book.setPublisherPhone(cursor.getString(5));

                book.setNumberOfPages(cursor.getInt(6));

                books.add(book);
                Log.println(Log.ERROR,"ERROR", book.id+" "+ book.numberOfPages);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return books;
    }
    public void updateBook(int ID,Book book){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("TITLE",book.getTitle());
        values.put("AUTHOR",book.getAuthor());
        values.put("PUBLISHER",book.getPublisher());
        values.put("PUBLISHER_EMAIL",book.publisherEmail);
        values.put("PUBLISHER_PHONE",book.publisherPhone);
        values.put("NUMBER_OF_PAGES",book.getNumberOfPages());

        db.update("BOOKS",values,"ID=?",new String[]{ID+""});
    }
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("BOOKS","ID=?",new String[]{id+""});
    }
    public int getLastID(){
        ArrayList<Book> books= getAllBooks();
        if(books.isEmpty()) return -1;
        return books.get(books.size()-1).getId();
    }
}
