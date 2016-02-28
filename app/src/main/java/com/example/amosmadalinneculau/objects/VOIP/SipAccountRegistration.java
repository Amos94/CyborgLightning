package com.example.amosmadalinneculau.objects.VOIP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.amosmadalinneculau.objects.MainActivity;
import com.example.amosmadalinneculau.objects.R;

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

        wv.loadUrl("https://mdns.sipthor.net/register_sip_account.phtml");
    }

    public void doneRegistration(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
