package us.bojie.instantmessagebo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import us.bojie.common.app.ToolbarActivity;
import us.bojie.instantmessagebo.R;

public class SearchActivity extends ToolbarActivity {
    public static final String EXTRA_TYPE = "extra_type";
    public static final int TYPE_USER = 1;
    public static final int TYPE_GROUP = 2;

    private int type;

    /**
     * 显示搜索界面
     *
     * @param context 上下文
     * @param type    显示的类型，用户还是群
     */
    public static void show(Context context, int type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        type = bundle.getInt(EXTRA_TYPE);
        // 是搜索人或者搜索群
        return type == TYPE_USER || type == TYPE_GROUP;

    }
}
