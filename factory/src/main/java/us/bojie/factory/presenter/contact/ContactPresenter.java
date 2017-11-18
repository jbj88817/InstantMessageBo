package us.bojie.factory.presenter.contact;

import us.bojie.factory.presenter.BasePresenter;

/**
 * Created by bojiejiang on 11/17/17.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {

    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();
        //TODO load data
    }
}
