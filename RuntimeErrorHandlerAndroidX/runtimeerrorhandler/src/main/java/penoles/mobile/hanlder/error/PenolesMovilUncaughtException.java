package penoles.mobile.hanlder.error;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import penoles.mobile.hanlder.error.utilities.Util;


public class PenolesMovilUncaughtException implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Class rootClass;

    public PenolesMovilUncaughtException(Context context, Class rootClass) {
        this.context = context;
        this.rootClass = rootClass;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Util.generateLog(context, rootClass, throwable);
        throwable.printStackTrace();
        showMessageDialog();
        Log.i("INFO", "Se ejecutó el handler desde la clase: " + rootClass.getSimpleName());
    }

    public void showMessageDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                builder.setTitle("¡Error!");
                builder.setMessage("Ha ocurrido un error durante la ejecución de la aplicación, la aplicación se cerrará.");
                builder.setNegativeButton("Entedido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ((Activity) context).finishAffinity();
                        }
                    }
                });
                builder.create().show();
                Looper.loop();
            }
        }.start();
    }
}
