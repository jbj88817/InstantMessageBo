package us.bojie.instantmessagebo;

import android.widget.TextView;

import butterknife.BindView;
import us.bojie.common.app.Activity;

public class MainActivity extends Activity {
    @BindView(R.id.tv_test)
    TextView mTestText;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestText.setText("Hello, human!");
    }
}
