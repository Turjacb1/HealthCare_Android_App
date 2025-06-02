//package com.turja.healthcare;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class Database extends SQLiteOpenHelper {
//    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String qry1="create table users(username text,email text,password text)";
//        sqLiteDatabase.execSQL(qry1);
//
//        String qry2="create table users(username text,product text,price float,otype text)";
//        sqLiteDatabase.execSQL(qry2);
//
//    }
//
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public void register(String username,String email,String password)
//    {
//        ContentValues cv=new ContentValues();
//        cv.put("username",username);
//        cv.put("email",email);
//        cv.put("password",password);
//        SQLiteDatabase db=getWritableDatabase();
//        db.insert("users",null,cv);
//        db.close();
//    }
//
//    public int login(String username,String password)
//    {
//        int result=0;
//        String str[]=new  String[2];
//        str[0]=username;
//        str[1]=password;
//        SQLiteDatabase db=getReadableDatabase();
//        Cursor c=db.rawQuery("select * from users where username=? and password=?",str);
//        if(c.moveToFirst())
//        {
//            result=1;
//        }
//        return result;
//    }
//
//    public int addCart(String username,String product,float price,String otype)
//    {
//        ContentValues cv=new ContentValues();
//        cv.put("username",username);
//        cv.put("product",product);
//        cv.put("price",price);
//        cv.put("otype",otype);
//        SQLiteDatabase db=getReadableDatabase();
//        db.insert("cart",null,cv);
//      db.close();
//        return 0;
//    }
//
//
//    public int checkCart(String username,String product)
//    {
//        int result=0;
//        String str[]=new  String[2];
//        str[0]=username;
//        str[1]=product;
//        SQLiteDatabase db=getReadableDatabase();
//        Cursor c=db.rawQuery("select * from cart where username = ? and product = ?",str);
//        if(c.moveToFirst())
//        {
//            result=1;
//        }
//        return result;
//    }
//
//    public int removeCart(String username,String otype)
//    {
//
//        String str[]=new  String[2];
//        str[0]=username;
//        str[1]=otype;
//        SQLiteDatabase db=getReadableDatabase();
//        db.delete("cart","username=? and otype=?",str);
//        db.close();
//        return  0;
//    }
//}



//package com.turja.healthcare;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//public class Database extends SQLiteOpenHelper {
//    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        // Create users table
//        String qry1 = "CREATE TABLE users(username TEXT PRIMARY KEY, email TEXT, password TEXT)";
//        sqLiteDatabase.execSQL(qry1);
//
//        // Create cart table
//        String qry2 = "CREATE TABLE cart(username TEXT, product TEXT, price FLOAT, otype TEXT)";
//        sqLiteDatabase.execSQL(qry2);
//
//        // Create orderplace table (pincode as INTEGER to match addOrder method)
//        String qry3 = "CREATE TABLE orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INTEGER, date TEXT, time TEXT, amount FLOAT, otype TEXT)";
//        sqLiteDatabase.execSQL(qry3);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS users");
//        db.execSQL("DROP TABLE IF EXISTS cart");
//        db.execSQL("DROP TABLE IF EXISTS orderplace");
//        onCreate(db);
//    }
//
//    public void register(String username, String email, String password) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("email", email);
//        cv.put("password", password);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert("users", null, cv);
//        db.close();
//    }
//
//    public int login(String username, String password) {
//        int result = 0;
//        String[] str = new String[]{username, password};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
//        if (c.moveToFirst()) {
//            result = 1;
//        }
//        c.close();
//        db.close();
//        return result;
//    }
//
//    public int addCart(String username, String product, float price, String otype) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("product", product);
//        cv.put("price", price);
//        cv.put("otype", otype);
//        SQLiteDatabase db = getWritableDatabase();
//        long result = db.insert("cart", null, cv);
//        db.close();
//        return result == -1 ? 1 : 0; // Return 1 if insert fails
//    }
//
//    public int checkCart(String username, String product) {
//        int result = 0;
//        String[] str = new String[]{username, product};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND product=?", str);
//        if (c.moveToFirst()) {
//            result = 1;
//        }
//        c.close();
//        db.close();
//        return result;
//    }
//
//    public int removeCart(String username, String otype) {
//        String[] str = new String[]{username, otype};
//        SQLiteDatabase db = getWritableDatabase();
//        int result = db.delete("cart", "username=? AND otype=?", str);
//        db.close();
//        return result; // Return number of rows deleted
//    }
//
//    public ArrayList<String> getCartData(String username, String otype) {
//        ArrayList<String> arr = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String[] str = new String[]{username, otype};
//        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?", str);
//
//        if (c.moveToFirst()) {
//            do {
//                String product = c.getString(1);
//                String price = c.getString(2);
//                arr.add(product + "$" + price);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return arr;
//    }
//
//    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("fullname", fullname);
//        cv.put("address", address);
//        cv.put("contactno", contact);
//        cv.put("pincode", pincode);
//        cv.put("date", date);
//        cv.put("time", time);
//        cv.put("amount", price);
//        cv.put("otype", otype);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert("orderplace", null, cv);
//        db.close();
//    }
//
//    public ArrayList getOrderData(String username)
//    {
//        ArrayList<String> arr=new ArrayList<>();
//        SQLiteDatabase db=getReadableDatabase();
//        String str[]=new String[1];
//        str[0]=username;
//        Cursor c=db.rawQuery("select * from orderplace where username=?",str);
//        if(c.moveToFirst())
//        {
//            do
//                {
//                    arr.add(c.getString(1)+""+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
//                }while(c.moveToNext());
//
//        }
//        db.close();
//        return arr;
//    }
//
//}
//


//        package com.turja.healthcare;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//public class Database extends SQLiteOpenHelper {
//    private static final String TAG = "Database";
//
//    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        // Create users table
//        String qry1 = "CREATE TABLE users(username TEXT PRIMARY KEY, email TEXT, password TEXT)";
//        sqLiteDatabase.execSQL(qry1);
//
//        // Create cart table
//        String qry2 = "CREATE TABLE cart(username TEXT, product TEXT, price FLOAT, otype TEXT)";
//        sqLiteDatabase.execSQL(qry2);
//
//        // Create orderplace table
//        String qry3 = "CREATE TABLE orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INTEGER, date TEXT, time TEXT, amount FLOAT, otype TEXT)";
//        sqLiteDatabase.execSQL(qry3);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS users");
//        db.execSQL("DROP TABLE IF EXISTS cart");
//        db.execSQL("DROP TABLE IF EXISTS orderplace");
//        onCreate(db);
//    }
//
//    public void register(String username, String email, String password) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("email", email);
//        cv.put("password", password);
//        SQLiteDatabase db = getWritableDatabase();
//        long result = db.insert("users", null, cv);
//        if (result == -1) {
//            Log.e(TAG, "Failed to register user: " + username);
//        } else {
//            Log.d(TAG, "User registered: " + username);
//        }
//        db.close();
//    }
//
//    public int login(String username, String password) {
//        int result = 0;
//        String[] str = new String[]{username, password};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
//        if (c.moveToFirst()) {
//            result = 1;
//            Log.d(TAG, "Login successful for user: " + username);
//        } else {
//            Log.e(TAG, "Login failed for user: " + username);
//        }
//        c.close();
//        db.close();
//        return result;
//    }
//
//    public int addCart(String username, String product, float price, String otype) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("product", product);
//        cv.put("price", price);
//        cv.put("otype", otype);
//        SQLiteDatabase db = getWritableDatabase();
//        long result = db.insert("cart", null, cv);
//        if (result == -1) {
//            Log.e(TAG, "Failed to add to cart: " + product + " for user: " + username);
//        } else {
//            Log.d(TAG, "Added to cart: " + product + " for user: " + username);
//        }
//        db.close();
//        return result == -1 ? 1 : 0;
//    }
//
//    public int checkCart(String username, String product) {
//        int result = 0;
//        String[] str = new String[]{username, product};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND product=?", str);
//        if (c.moveToFirst()) {
//            result = 1;
//            Log.d(TAG, "Product found in cart: " + product + " for user: " + username);
//        }
//        c.close();
//        db.close();
//        return result;
//    }
//
//    public int removeCart(String username, String otype) {
//        String[] str = new String[]{username, otype};
//        SQLiteDatabase db = getWritableDatabase();
//        int result = db.delete("cart", "username=? AND otype=?", str);
//        Log.d(TAG, "Removed " + result + " items from cart for user: " + username + ", otype: " + otype);
//        db.close();
//        return result;
//    }
//
//    public ArrayList<String> getCartData(String username, String otype) {
//        ArrayList<String> arr = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String[] str = new String[]{username, otype};
//        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?", str);
//
//        if (c.moveToFirst()) {
//            do {
//                String product = c.getString(1);
//                String price = c.getString(2);
//                arr.add(product + "$" + price);
//                Log.d(TAG, "Cart data: " + product + "$" + price + " for user: " + username);
//            } while (c.moveToNext());
//        } else {
//            Log.d(TAG, "No cart data found for user: " + username + ", otype: " + otype);
//        }
//        c.close();
//        db.close();
//        return arr;
//    }
//
//    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("fullname", fullname);
//        cv.put("address", address);
//        cv.put("contactno", contact);
//        cv.put("pincode", pincode);
//        cv.put("date", date);
//        cv.put("time", time);
//        cv.put("amount", price);
//        cv.put("otype", otype);
//        SQLiteDatabase db = getWritableDatabase();
//        long result = db.insert("orderplace", null, cv);
//        if (result == -1) {
//            Log.e(TAG, "Failed to add order for user: " + username + ", fullname: " + fullname);
//        } else {
//            Log.d(TAG, "Order added for user: " + username + ", fullname: " + fullname);
//        }
//        db.close();
//    }
//
//    public ArrayList<String> getOrderData(String username) {
//        ArrayList<String> arr = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String[] str = new String[]{username};
//        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=?", str);
//        if (c.moveToFirst()) {
//            do {
//                String order = c.getString(1) + "$" + // fullname
//                        c.getString(2) + "$" + // address
//                        c.getString(3) + "$" + // contactno
//                        c.getInt(4) + "$" +    // pincode
//                        c.getString(5) + "$" + // date
//                        c.getString(6) + "$" + // time
//                        c.getFloat(7) + "$" +  // amount
//                        c.getString(8);        // otype
//                arr.add(order);
//                Log.d(TAG, "Order data retrieved: " + order + " for user: " + username);
//            } while (c.moveToNext());
//        } else {
//            Log.d(TAG, "No order data found for user: " + username);
//        }
//        c.close();
//        db.close();
//        return arr;
//    }
//
//    public void logOrders() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM orderplace", null);
//        if (cursor.moveToFirst()) {
//            do {
//                Log.d(TAG, "Order: username=" + cursor.getString(0) +
//                        ", fullname=" + cursor.getString(1) +
//                        ", address=" + cursor.getString(2) +
//                        ", contactno=" + cursor.getString(3) +
//                        ", pincode=" + cursor.getInt(4) +
//                        ", date=" + cursor.getString(5) +
//                        ", time=" + cursor.getString(6) +
//                        ", amount=" + cursor.getFloat(7) +
//                        ", otype=" + cursor.getString(8));
//            } while (cursor.moveToNext());
//        } else {
//            Log.d(TAG, "No orders found in orderplace table");
//        }
//        cursor.close();
//        db.close();
//    }
//
//
//    public int checkAppointmentExitsts(String username, String fullname, String address, String contact,  String date, String time) {
//        int result=0;
//        String str[]=new String[6];
//        str[0]=username;
//        str[1] =fullname;
//        str[2] =address;
//        str[3] =contact;
//        str[4] =date;
//        str[5]=time;
//        SQLiteDatabase db = getWritableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND fullname=? AND address=?  AND contact=?", str);
//        if (c.moveToFirst()) {
//            result=1;
//        } else {
//            Log.d(TAG, "Order added for user: " + username + ", fullname: " + fullname);
//        }
//
//        db.close();
//        return result;
//    }
//}


package com.turja.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "Database";

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create users table
        String qry1 = "CREATE TABLE users(username TEXT PRIMARY KEY, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(qry1);

        // Create cart table
        String qry2 = "CREATE TABLE cart(username TEXT, product TEXT, price FLOAT, otype TEXT)";
        sqLiteDatabase.execSQL(qry2);

        // Create orderplace table
        String qry3 = "CREATE TABLE orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INTEGER, date TEXT, time TEXT, amount FLOAT, otype TEXT)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orderplace");
        onCreate(db);
    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("users", null, cv);
        if (result == -1) {
            Log.e(TAG, "Failed to register user: " + username);
        } else {
            Log.d(TAG, "User registered: " + username);
        }
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[]{username, password};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1;
            Log.d(TAG, "Login successful for user: " + username);
        } else {
            Log.e(TAG, "Login failed for user: " + username);
        }
        c.close();
        db.close();
        return result;
    }

    public int addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("cart", null, cv);
        if (result == -1) {
            Log.e(TAG, "Failed to add to cart: " + product + " for user: " + username);
        } else {
            Log.d(TAG, "Added to cart: " + product + " for user: " + username);
        }
        db.close();
        return result == -1 ? 1 : 0;
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String[] str = new String[]{username, product};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND product=?", str);
        if (c.moveToFirst()) {
            result = 1;
            Log.d(TAG, "Product found in cart: " + product + " for user: " + username);
        }
        c.close();
        db.close();
        return result;
    }

    public int removeCart(String username, String otype) {
        String[] str = new String[]{username, otype};
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete("cart", "username=? AND otype=?", str);
        Log.d(TAG, "Removed " + result + " items from cart for user: " + username + ", otype: " + otype);
        db.close();
        return result;
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[]{username, otype};
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?", str);

        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
                Log.d(TAG, "Cart data: " + product + "$" + price + " for user: " + username);
            } while (c.moveToNext());
        } else {
            Log.d(TAG, "No cart data found for user: " + username + ", otype: " + otype);
        }
        c.close();
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("orderplace", null, cv);
        if (result == -1) {
            Log.e(TAG, "Failed to add order for user: " + username + ", fullname: " + fullname);
        } else {
            Log.d(TAG, "Order added for user: " + username + ", fullname: " + fullname);
        }
        db.close();
    }

    public ArrayList<String> getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[]{username};
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=?", str);
        if (c.moveToFirst()) {
            do {
                String order = c.getString(1) + "$" + // fullname
                        c.getString(2) + "$" + // address
                        c.getString(3) + "$" + // contactno
                        c.getInt(4) + "$" +    // pincode
                        c.getString(5) + "$" + // date
                        c.getString(6) + "$" + // time
                        c.getFloat(7) + "$" +  // amount
                        c.getString(8);        // otype
                arr.add(order);
                Log.d(TAG, "Order data retrieved: " + order + " for user: " + username);
            } while (c.moveToNext());
        } else {
            Log.d(TAG, "No order data found for user: " + username);
        }
        c.close();
        db.close();
        return arr;
    }

    public void logOrders() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orderplace", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "Order: username=" + cursor.getString(0) +
                        ", fullname=" + cursor.getString(1) +
                        ", address=" + cursor.getString(2) +
                        ", contactno=" + cursor.getString(3) +
                        ", pincode=" + cursor.getInt(4) +
                        ", date=" + cursor.getString(5) +
                        ", time=" + cursor.getString(6) +
                        ", amount=" + cursor.getFloat(7) +
                        ", otype=" + cursor.getString(8));
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "No orders found in orderplace table");
        }
        cursor.close();
        db.close();
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;
        String[] str = new String[]{username, fullname, address, contact, date, time};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=? AND fullname=? AND address=? AND contactno=? AND date=? AND time=?", str);
        if (c.moveToFirst()) {
            result = 1;
            Log.d(TAG, "Appointment exists for user: " + username + ", fullname: " + fullname + ", date: " + date + ", time: " + time);
        } else {
            Log.d(TAG, "No appointment found for user: " + username + ", fullname: " + fullname + ", date: " + date + ", time: " + time);
        }
        c.close();
        db.close();
        return result;
    }
}