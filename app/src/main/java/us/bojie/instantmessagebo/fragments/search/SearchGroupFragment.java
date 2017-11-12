package us.bojie.instantmessagebo.fragments.search;


import us.bojie.common.app.PresenterFragment;
import us.bojie.factory.presenter.BaseContract;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.activities.SearchActivity;

/**
 * 搜索群的界面实现
 */
public class SearchGroupFragment extends PresenterFragment
        implements SearchActivity.SearchFragment {


    public SearchGroupFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void search(String content) {

    }
}
