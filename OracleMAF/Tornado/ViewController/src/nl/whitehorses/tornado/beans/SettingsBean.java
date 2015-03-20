package nl.whitehorses.tornado.beans;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;

public class SettingsBean {
    
    private static final String BINDING_METHOD_STORE_OVERVIEW_TYPE = "storeOverviewTypeInDb";
    private static final String BINDING_ATTR_GET_OVERVIEW_TYPE = "overviewTypeFromDb";
    private static final String BINDING_DC_SETTINGS = "SettingsDC";
    
    private String overviewType;

    public SettingsBean() {
    }

    public void setOverviewType(String overviewType) {
        this.overviewType = overviewType;
    }

    public String getOverviewType() {
        if(overviewType == null) {
            //Get overview type from DB
            AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
            String overviewTypeFromDb = (String) (AdfmfJavaUtilities.getValueExpression("#{bindings." + BINDING_ATTR_GET_OVERVIEW_TYPE + ".inputValue}", String.class).getValue(adfELContext));
            overviewType = overviewTypeFromDb;
        }
        
        return overviewType;
    }

    public void overviewTypeChangeListener(ValueChangeEvent valueChangeEvent) {
        String newVal = (String) valueChangeEvent.getNewValue();
        boolean altered = !newVal.equals(valueChangeEvent.getOldValue());
        
        if(altered) {
            //Store new value in DB
            AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
            
            List paramNames = new ArrayList();
            paramNames.add("overviewType");
            
            List params = new ArrayList();
            params.add(newVal);
            
            List paramTypes = new ArrayList();
            paramTypes.add(String.class);

            try {
                AdfmfJavaUtilities.invokeDataControlMethod(BINDING_DC_SETTINGS, null,
                                                           BINDING_METHOD_STORE_OVERVIEW_TYPE, paramNames, params, paramTypes);
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                           "navigator.notification.alert",
                            new Object[] {"Stored: " + newVal,"", "Info", "Ok"});
            } catch (AdfInvocationException e) {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                           "navigator.notification.alert",
                            new Object[] {"Error!","", "Error", "Ok"});
            }
        }
    }
}
