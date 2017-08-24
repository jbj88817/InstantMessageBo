package us.bojie.instantmessagebo.fragments.main;


import butterknife.BindView;
import us.bojie.common.app.Fragment;
import us.bojie.common.widget.GalleryView;
import us.bojie.instantmessagebo.R;


public class ActiveFragment extends Fragment {


    @BindView(R.id.galleryView)
    GalleryView mGallery;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initData() {
        super.initData();
        mGallery.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }
}
