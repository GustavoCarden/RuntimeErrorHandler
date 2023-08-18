package penoles.mobile.hanlder.error.abstract_classes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import penoles.mobile.hanlder.error.PenolesMovilUncaughtException;

public abstract class PenolesMovilActivityHandler extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHandlerForUncaughtExceptions(this);
    }

    void setHandlerForUncaughtExceptions(Context pCallerClass) {
        Thread.setDefaultUncaughtExceptionHandler(new PenolesMovilUncaughtException(pCallerClass, this.getClass()));
    }
}
