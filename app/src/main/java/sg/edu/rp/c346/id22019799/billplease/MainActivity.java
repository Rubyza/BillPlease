package sg.edu.rp.c346.id22019799.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText pax;
    EditText discnt;
    ToggleButton svs;
    ToggleButton gst;
    RadioGroup paymthd;
    Button split;
    Button rset;
    TextView totalbill;
    TextView  eachpy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercisethebillcalculator);

        amount = findViewById(R.id.editAmount);
        pax = findViewById(R.id.editPax);
        svs = findViewById(R.id.toggleButtonNoSVS);
        gst = findViewById(R.id.toggleButtonGST);
        discnt = findViewById(R.id.editDiscount);
        paymthd = findViewById(R.id.paymentmethod);
        split = findViewById(R.id.buttonSplit);
        rset = findViewById(R.id.buttonReset);
        totalbill = findViewById(R.id.totalbill);
        eachpy = findViewById(R.id.paysplit);



        svs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action

                //get the state of the toggle button
                boolean isChecked = svs.isChecked();
                if (isChecked) {
                    svs.setEnabled(true);
                } else{
                    svs.setEnabled(false);
                }


            }
        });

        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action

                //get the state of the toggle button
                boolean isChecked = gst.isChecked();
                if (isChecked) {
                    gst.setEnabled(true);
                } else{
                    gst.setEnabled(false);
                }


            }
        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action

                //Amount
                String amtstring = amount.getText().toString();
                double amt = Double.parseDouble(amtstring);

                //Pax
                String Paxstring = pax.getText().toString();
                int Pax = Integer.parseInt(Paxstring);

                //Discount
                String discountstring = discnt.getText().toString();
                double discount = Double.parseDouble(discountstring);

                //Total
                double total = 0;


                if (svs.isChecked() && !gst.isChecked()) {
                    total = amt-(amt* (discount/ 100.0))*1.1;
                    totalbill.setText(""+total);
                }
                else if (svs.isChecked() && gst.isChecked()) {
                    total = amt-(amt* (discount/ 100.0))*1.1*0.08;
                    totalbill.setText(""+total);
                }
                else if (!svs.isChecked() && gst.isChecked()) {
                    total = amt-(amt*(discount/ 100.0))*0.08;
                    totalbill.setText(""+total);
                }
                else{
                    total = amt-(amt* (discount/ 100.0));
                    totalbill.setText(""+total);
                }



                int checkedRadioId = paymthd.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButtonPayNow){
                    double payamt=total/Pax;
                    eachpy.setText(payamt+" via PayNow to 912345678");

                }
                else{
                    double payamt=total/Pax;
                    eachpy.setText(payamt+" in cash");

                }

            }


        });

        rset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                //Amount
                amount.setText("");

                //Pax
                String Paxstring = pax.getText().toString();
                pax.setText("");

                //Discount
                String discountstring = discnt.getText().toString();
                discnt.setText("");

                svs.setEnabled(false);
                gst.setEnabled(false);


                totalbill.setText("");
                eachpy.setText("");
            }
        });


    }
}