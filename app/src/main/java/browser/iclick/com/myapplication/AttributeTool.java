package browser.iclick.com.myapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bym on 2018/3/8.
 */

public class AttributeTool {

    private Map<String, String> attributeMap;

    private AttributeTool() {
        attributeMap = new HashMap<>();
    }

    public static AttributeTool getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        static final AttributeTool instance = new AttributeTool();
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
    }
}
