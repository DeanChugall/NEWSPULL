package club.androidx.mvc.model.utilities;

import android.util.Log;

public class FixUrlToImageExtension {

    public static String fixUrl(String path) {


        // String extensionString = path.substring(path.length()-4, path.length());
        int pathLL = path.length();
        if (!path.isEmpty() && path.toLowerCase().endsWith("jpg") && !path.substring(pathLL - 4, pathLL).equals(".")) {

            Log.e("OVDE", "OVDEEEEEEEE SAAAAAA: " + path.substring(pathLL - 3, pathLL));


            return insertAt(path, path.length()-3, ".");


        }

        return path;
    }
    private static String insertAt(final String target, final int position, final String insert) {
        final int targetLen = target.length();
        if (position < 0 || position > targetLen) {
            throw new IllegalArgumentException("position=" + position);
        }
        if (insert.isEmpty()) {
            return target;
        }
        if (position == 0) {
            return insert.concat(target);
        } else if (position == targetLen) {
            return target.concat(insert);
        }
        final int insertLen = insert.length();
        final char[] buffer = new char[targetLen + insertLen];
        target.getChars(0, position, buffer, 0);
        insert.getChars(0, insertLen, buffer, position);
        target.getChars(position, targetLen, buffer, position + insertLen);
        return new String(buffer);
    }


}
