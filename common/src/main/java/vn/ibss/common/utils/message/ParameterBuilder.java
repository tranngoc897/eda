
package vn.ibss.common.utils.message;

import java.util.HashMap;
import java.util.Map;

public class ParameterBuilder {

    /** The map of parameters. */
    private Map<String, Object> params;

    /**
     * Should use method {@link ParameterBuilder#newBuilder()} to create new instance of this class.
     */
    private ParameterBuilder() {
        params = new HashMap<String, Object>();
    }

    /**
     * Create new parameter builder.
     *
     * @return the new instance of this class.
     */
    public static ParameterBuilder newBuilder() {
        return new ParameterBuilder();
    }

    /**
     * Add a pair of parameter name & parameter value (key-value) to the map.
     *
     * @param parameterName
     *            the parameter name (key).
     * @param parameterValue
     *            the parameter value.
     * @return the current instance of this class.
     */
    public ParameterBuilder add(String parameterName, Object parameterValue) {
        this.params.put(parameterName, parameterValue);
        return this;
    }

    /**
     * Build the map of parameters.
     * 
     * @return the map of parameters.
     */
    public Map<String, Object> build() {
        return this.params;
    }
}
