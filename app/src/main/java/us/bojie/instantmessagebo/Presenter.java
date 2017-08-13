package us.bojie.instantmessagebo;

import android.text.TextUtils;

/**
 * Created by bojiejiang on 8/12/17.
 */

public class Presenter implements IPresenter {
    private IView mView;

    public Presenter(IView view) {
        mView = view;
    }

    @Override
    public void search() {
        // TODO show loading
        String inputString = mView.getInputString();
        if (TextUtils.isEmpty(inputString)) {
            return;
        }

        int hashCode = inputString.hashCode();
        IUserService service = new UserService();
        String serviceResult = service.search(hashCode);
        String result = "Result: " + inputString + " - " + serviceResult;

        // TODO close loading

        mView.setResultString(result);
    }
}
