package us.bojie.instantmessagebo.fragments.account;


import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.Fragment;
import us.bojie.common.widget.PortraitView;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.fragments.media.GalleryFragment;

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
        new GalleryFragment()
                .setListener(new GalleryFragment.OnSelectedListener() {
                    @Override
                    public void onSelectedImage(String path) {

                    }
                    // show 的时候建议使用getChildFragmentManager，
                    // tag GalleryFragment class 名
                }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }
}
