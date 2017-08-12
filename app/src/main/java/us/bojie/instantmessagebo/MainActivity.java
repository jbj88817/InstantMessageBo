package us.bojie.instantmessagebo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import us.bojie.common.Common;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Common();
    }
}
