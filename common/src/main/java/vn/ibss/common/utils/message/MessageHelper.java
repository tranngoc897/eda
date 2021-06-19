
package vn.ibss.common.utils.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;
import java.util.Map;
import java.util.NoSuchElementException;

public class MessageHelper {

    /**
     * The class level logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHelper.class);

    /**
     * The loader for loading data from source file. Use properties file importer as default.
     */
    private static MessagePatternLoader loader = new PropertiesLoader("ExceptionMessages");

    /**
     * The map (key-value) of message patterns.
     */
    private static Map<String, String> messagePatterns;

    /**
     * Should use static methods, not call methods by using instance of class.
     */
    private MessageHelper() {

    }

    /**
     * Choose the loader which will be used to load data (data will be reloaded automatically).
     *
     * @param messagePatternLoader the message pattern importer.
     */
    public static void useLoader(MessagePatternLoader messagePatternLoader) {
        if (messagePatternLoader != null) {
            loader = messagePatternLoader;
            messagePatterns = loader.load();
        }
    }

    /**
     * Get message pattern by error code.
     *
     * @param errorCode the message error code (it is usually a number).
     * @return the message pattern which was defined in source file.
     */
    private static String getMessagePattern(int errorCode) {
        if (messagePatterns == null || messagePatterns.isEmpty()) {
            messagePatterns = loader.load();
        }
        String pattern = messagePatterns.get(String.valueOf(errorCode));
        if (pattern == null) {
            LOGGER.error("Message pattern for error code '" + errorCode + "' is undefined.");
            throw new NoSuchElementException("Message pattern for error code '" + errorCode + "' is undefined.");
        }

        return pattern;
    }

    /**
     * Format message pattern which associated with the given error code, use variable arguments (varargs).
     *
     * @param errorCode the message error code (it is usually a number).
     * @param arguments variable arguments (varargs).
     * @return a formatted message as string.
     */
    public static String format(int errorCode, Object... arguments) {
        return MessageFormat.format(getMessagePattern(errorCode), arguments);
    }

    /**
     * Format message pattern which associated with the given error code, use map of parameters.
     *
     * @param errorCode the message error code (it is usually a number).
     * @param arguments map of parameters (should use {@link ParameterBuilder} for building map).
     * @return a formatted message as string.
     */
    public static String format(int errorCode, Map<String, Object> arguments) {
        return MessageFormat.format(getMessagePattern(errorCode), arguments);
    }

    public static String format(String messagePattern, Object... arguments) {
        return MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
    }
}
