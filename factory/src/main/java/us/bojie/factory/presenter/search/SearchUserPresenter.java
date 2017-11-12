package us.bojie.factory.presenter.search;

import us.bojie.factory.presenter.BasePresenter;

/**
 * Created by bojiejiang on 11/12/17.
 * 搜索人的实现
 */

public class SearchUserPresenter extends BasePresenter<SearchContract.UserView>
        implements SearchContract.Presenter {

    public SearchUserPresenter(SearchContract.UserView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
