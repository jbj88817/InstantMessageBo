package us.bojie.instantmessagebo.fragments.account;


import android.graphics.Bitmap;
import android.net.Uri;

import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import us.bojie.common.app.Fragment;
import us.bojie.common.app.MyApplication;
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
                        UCrop.Options options = new UCrop.Options();
                        // 设置图片处理的格式JPEG
                        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                        // 设置压缩后的图片精度
                        options.setCompressionQuality(96);
                        // 得到头像的缓存地址
                        File dPath = MyApplication.getPortraitTmpFile();

                        // 发起剪切
                        UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(520, 520)
                                .withOptions(options)
                                .start(getActivity());
                    }
                    // show 的时候建议使用getChildFragmentManager，
                    // tag GalleryFragment class 名
                }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }
}
