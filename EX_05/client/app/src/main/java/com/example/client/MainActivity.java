package com.example.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static String TOKEN = "";
    private Button mFetchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getToken();

        mFetchButton = findViewById(R.id.buttonFetch);
        mFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
                fetchSharePrice(view);
            }
        });
    }
    public void fetchSharePrice(final View view) {
        final ShareFetcher fetcher = new ShareFetcher(view.getContext());
        final String shareName = ((EditText)findViewById(R.id.edit_share)).getText().toString();

        fetcher.dispatchRequest(shareName , TOKEN, new ShareFetcher.shareReasponseListener() {
            @Override
            public void onResponse(ShareFetcher.ShareResponse response) {

                if (response.isError) {
                    Toast.makeText(view.getContext(), "Error while fetching share price", Toast.LENGTH_LONG);
                    return;
                }
            }
        });
    }


    public void getToken()  {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        TOKEN = task.getResult().getToken();
                    }
                });
            }


    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.client.onMessageReceived");
        mReceiver receiver = new mReceiver();
        registerReceiver(receiver, intentFilter);
    }

    private class mReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            String name = extras.getString("name");
            String price = extras.getString("price");
            updateView(name, price);
        }

        private void updateView(String symbol, String price) {
            ((TextView)MainActivity.this.findViewById(R.id.text_name)).setText(symbol);
            ((TextView)MainActivity.this.findViewById(R.id.text_price)).setText("$" + price);
        }
    }
}


