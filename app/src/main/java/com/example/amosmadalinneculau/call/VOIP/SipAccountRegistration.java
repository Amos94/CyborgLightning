package com.example.amosmadalinneculau.call.VOIP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.amosmadalinneculau.call.MainActivity;
import com.example.amosmadalinneculau.call.R;


/**
 * Created by Amos Madalin Neculau on 28/02/2016.
 */
public class SipAccountRegistration extends Activity{

    private WebView wv;
    private Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sip_account_registration);

        wv = (WebView) findViewById(R.id.webView);
        doneBtn = (Button) findViewById(R.id.doneBtn);

        wv.loadUrl("https://www.antisip.com/sip-antisip-com-register");
    }

    public void doneRegistration(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
