package us.bojie.instantmessagebo.fragments.account;


import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.Fragment;
import us.bojie.common.widget.PortraitView;
import us.bojie.instantmessagebo.R;

/**
 * 用户更新信息的界面
 */
public class UpdateInfoFragment extends Fragment {

    @BindView(R.id.iv_portrait)
    PortraitView mPortrait;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.iv_portrait)
    public void onPortraitClicked() {
    }
}
