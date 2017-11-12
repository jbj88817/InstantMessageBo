package us.bojie.factory.presenter.search;

import us.bojie.factory.presenter.BasePresenter;

/**
 * Created by bojiejiang on 11/12/17.
 * 搜索群的逻辑实现
 */

public class SearchGroupPresenter extends BasePresenter<SearchContract.GroupView>
        implements SearchContract.Presenter {

    public SearchGroupPresenter(SearchContract.GroupView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
