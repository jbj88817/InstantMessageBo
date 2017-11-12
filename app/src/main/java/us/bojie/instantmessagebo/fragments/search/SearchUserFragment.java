package us.bojie.instantmessagebo.fragments.search;


import us.bojie.common.app.PresenterFragment;
import us.bojie.factory.presenter.BaseContract;
import us.bojie.instantmessagebo.R;
import us.bojie.instantmessagebo.activities.SearchActivity;

/**
 * 搜索人的界面实现
 */
public class SearchUserFragment extends PresenterFragment
        implements SearchActivity.SearchFragment {


    public SearchUserFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_user;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void search(String content) {

    }
}
