
package vn.ibss.common.utils.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ExceptionMessageBundle {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("ExceptionMessages", Locale.getDefault());

    private ExceptionMessageBundle() {

    }

    public static String getString(Integer key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(String.valueOf(key)), params);
        } catch (MissingResourceException e) {
            return '!' + String.valueOf(key) + '!';
        }
    }
}