package penoles.mobile.hanlder.error.utilities;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import penoles.mobile.hanlder.error.R;


public class Util {

    /*
     * Metodo que genera un log al fallar algun metodo de intercambio con el WS.
     * @param obj: Objeto que contiene la exepcion que se imprimira en el log.
     * */
    public static void generateLog(Context context, Class rootClass, Object obj) {
        FileWriter out = null;
        try {
            String externalStorage = Environment.getExternalStorageDirectory().getAbsolutePath();
            File outputDirectory = new File(externalStorage + File.separator + "Download" + File.separator + context.getResources().getString(R.string.app_name));
            if (!outputDirectory.exists()) {
                outputDirectory.mkdir();
            }

            StringBuilder fileName = new StringBuilder();
            fileName.append(ObjectUtil.currentDate());
            fileName.append(".log");

            File outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + fileName);

            StringBuilder builder = new StringBuilder();
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                out = new FileWriter(outputFile);
            } else {
                out = new FileWriter(outputFile, true);
                builder.append("\n\n");
            }

            builder.append("(");
            builder.append(ObjectUtil.currentTime());
            builder.append(")");

            builder.append(rootClass.getName());
            builder.append(": ");
            if (obj != null) {
                if (obj instanceof Throwable) {
                    builder.append(formatException((Throwable) obj));
                } else if (obj instanceof String) {
                    builder.append(obj.toString());
                }
            } else {
                builder.append("El objeto es nulo.");
            }
            out.write(builder.toString());
        } catch (Exception ex) {
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException ex) {
                generateLog(context,Util.class, ex);
            }
        }
    }

    /*
     * Metodo encargado de dar formato a un objeto throwable
     * @param pException: Objeto tipo Throwable que contiene la informacion a formatear.
     * */
    private static CharSequence formatException(Throwable pException) {
        StringBuilder buffer = new StringBuilder();
        if (pException != null) {
            buffer.append(pException.getLocalizedMessage());
            StackTraceElement steList[] = pException.getStackTrace();
            if (steList != null) {
                for (StackTraceElement ste : steList) {
                    buffer.append("\n\tat ");
                    buffer.append(ste.getClassName());
                    buffer.append(".");
                    buffer.append(ste.getMethodName());
                    buffer.append("(");
                    buffer.append(ste.getFileName());
                    buffer.append(":");
                    buffer.append(ste.getLineNumber());
                    buffer.append(")");
                }
            }
        }
        return buffer;
    }

}
