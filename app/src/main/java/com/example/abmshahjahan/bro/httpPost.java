package com.example.abmshahjahan.bro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.abmshahjahan.bro.R;
import com.example.abmshahjahan.bro.appOpener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class httpPost extends Activity {

    TextView content;
    EditText input;
    String Input;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        content    =   (TextView)findViewById( R.id.content );
        input      =   (EditText)findViewById(R.id.input);



        Button saveme=(Button)findViewById(R.id.save);

        saveme.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{

                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {
                    content.setText(" url exeption! " );
                }
            }
        });
    }

    // Create GetText Method
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values

        Input         = input.getText().toString();
        


        // Create data variable for sent values to server

        String data = URLEncoder.encode("input", "UTF-8")
                + "=" + URLEncoder.encode(Input, "UTF-8");


        String text = "";
        BufferedReader reader=null;



        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://127.0.0.1:8000");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        content.setText( text  );

    }

}