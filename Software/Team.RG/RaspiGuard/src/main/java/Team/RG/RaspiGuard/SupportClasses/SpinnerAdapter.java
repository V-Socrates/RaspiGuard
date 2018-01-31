package Team.RG.RaspiGuard.SupportClasses;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Vivek Socrates on 1/16/2018.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    int count;

    public SpinnerAdapter(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {

        count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
