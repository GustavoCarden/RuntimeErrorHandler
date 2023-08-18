package penoles.mobile.hanlder.error.abstract_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import penoles.mobile.hanlder.error.PenolesMovilUncaughtException;

public abstract class PenolesMovilFragmentHandler extends Fragment {

    public View inflateHandler(int pLayout, LayoutInflater inflater, @Nullable ViewGroup container) {
        setHandlerForUncaughtExceptions(this.getContext());
        return inflater.inflate(pLayout, container, false);
    }

    void setHandlerForUncaughtExceptions(Context pCallerClass) {
        Thread.setDefaultUncaughtExceptionHandler(new PenolesMovilUncaughtException(pCallerClass, this.getClass()));
    }

}
