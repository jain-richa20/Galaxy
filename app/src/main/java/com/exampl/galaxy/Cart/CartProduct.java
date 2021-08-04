package com.exampl.galaxy.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exampl.galaxy.Homepage.MenuPage.DescriptionPage;
import com.exampl.galaxy.MainActivity;
import com.exampl.galaxy.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class CartProduct extends AppCompatActivity implements PaymentResultListener {

    Toolbar carttoolbar;
    Button add, sub, placeorder;
    TextView product_name, product_price, quantity, totalmrp, discount, total, conv, free;
    int product_quantity = 1;
    int price = 529;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product);
        Checkout.preload(getApplicationContext());
        carttoolbar = findViewById(R.id.carttoolbar);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        quantity = findViewById(R.id.quantity);
        totalmrp = findViewById(R.id.totalmrp);
        placeorder = findViewById(R.id.placeorder);
        discount = findViewById(R.id.discount);
        total = findViewById(R.id.total);
        free = findViewById(R.id.free);
        conv = findViewById(R.id.conv);
        product_name.setText("HIGHLANDER Men Blue Slim Fit Checked Casual Shirt");
        product_price.setText("₹529");
        discount.setText("");
        int value = Integer.parseInt(totalmrp.getText().toString());
        int c = value;
        discount.setText(discount.getText().toString() + ((product_quantity * value) - (product_quantity * price)));
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        carttoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartProduct.this, DescriptionPage.class));
            }
        });
        if (Integer.parseInt(conv.getText().toString()) < 1000) {
            conv.setText("70");
            total.setText("");
            total.setText(total.getText().toString() + ((product_quantity * price) + 70));

        } else {
            free.setText("");
            conv.setText("FREE");

        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_quantity++;
                quantity.setText(product_quantity + "");
                total.setText("");
                product_price.setText("");
                discount.setText("");
                total.setText(total.getText().toString() + (product_quantity * price));
                product_price.setText(product_price.getText().toString() + (product_quantity * price));

                int value = Integer.parseInt(totalmrp.getText().toString());
                totalmrp.setText("");
                totalmrp.setText(totalmrp.getText().toString() + (product_quantity * value));
                discount.setText("");
                discount.setText(discount.getText().toString() + ((product_quantity * value) - (product_quantity * price)));

                if ((product_quantity * price) < 1000) {
                    conv.setText("70");
                    total.setText("");
                    total.setText(total.getText().toString() + ((product_quantity * price) + 70));

                } else {
                    free.setText("");
                    conv.setText("FREE");

                }

            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_quantity--;

                if (product_quantity >= 1) {
                    quantity.setText(product_quantity + "");
                    total.setText("");
                    product_price.setText("");
                    total.setText(total.getText().toString() + (product_quantity * price));
                    product_price.setText(product_price.getText().toString() + (product_quantity * price));

                    int value = Integer.parseInt(totalmrp.getText().toString());
                    totalmrp.setText("");
                    totalmrp.setText(totalmrp.getText().toString() + ((product_quantity * value) - (c)));

                    discount.setText("");
                    discount.setText(discount.getText().toString() + ((product_quantity * value) - (product_quantity * price) - (c)));

                    if ((product_quantity * price) < 1000) {
                        conv.setText("70");
                        total.setText("");
                        total.setText(total.getText().toString() + ((product_quantity * price) + 70));

                    } else {
                        free.setText("");
                        conv.setText("FREE");

                    }
                } else {
                    product_quantity = 1;
                    Toast.makeText(CartProduct.this, "At least 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_7LDvv3LusX2Gcu");
        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        //final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();
            int tot=Integer.parseInt(total.getText().toString())*100;
            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #1456");
            options.put("theme.color", "#80CBC4");
            options.put("currency", "INR");
            options.put("amount", tot);//pass amount in currency subunits
            options.put("prefill.email", "richa.jain@example.com");
            options.put("prefill.contact","9988776655");
            checkout.open(CartProduct.this, options);

        } catch(Exception e) {
            Log.e("Error", e+"");
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Payment Id");
        builder.setTitle(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }
}