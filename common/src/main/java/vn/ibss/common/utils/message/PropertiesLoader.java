
package vn.ibss.common.utils.message;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class PropertiesLoader implements MessagePatternLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    private String sourceFilePath;


    public PropertiesLoader(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public Map<String, String> load() {
        Map<String, String> data = new TreeMap<String, String>();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FilenameUtils.getBaseName(this.sourceFilePath));
            for (String key : resourceBundle.keySet()) {
                data.put(key, resourceBundle.getString(key));
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while trying to load data from source file: " + this.sourceFilePath, e);
        }

        return data;
    }
}
