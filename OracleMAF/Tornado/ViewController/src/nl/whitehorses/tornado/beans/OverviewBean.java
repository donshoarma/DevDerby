package nl.whitehorses.tornado.beans;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class OverviewBean {
    
    private String searchDate = "2015-02-01";
    
    public OverviewBean() {
        super();
    }
    
    public Object getConstructedGetMedewerkerRequestString() {
        //Get authentication ID
        String authenticationId = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.tornadoApplicationBean.authenticationId}");
        
        //Construct payload input
        AdfmfJavaUtilities.setELValue("#{bindings.id.inputValue}", authenticationId);

        //Construct payload
        return AdfmfJavaUtilities.getELValue("${bindings.payloadIterator.currentRow.dataProvider}");
    }
    
    public Object getConstructedGetUrenschrijvenUurDataRequestString() {
        //Get authentication ID
        String authenticationId = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.tornadoApplicationBean.authenticationId}");
        String days = (String) AdfmfJavaUtilities.getELValue("#{preferenceScope.application.tornadoPrefs.daysPref}");
        String date = searchDate;
        //Object resourceId = AdfmfJavaUtilities.getELValue("#{bindings.Project.inputValue}");
        
        //Construct payload input
        AdfmfJavaUtilities.setELValue("#{bindings.MEDEWERKERID.inputValue}", authenticationId);
        AdfmfJavaUtilities.setELValue("#{bindings.DAGEN.inputValue}", days);
        AdfmfJavaUtilities.setELValue("#{bindings.DATUM.inputValue}", date);
        AdfmfJavaUtilities.setELValue("#{bindings.RESOURCEID.inputValue}", 4224);
        
        //Construct payload
        return AdfmfJavaUtilities.getELValue("${bindings.payloadIterator1.currentRow.dataProvider}");
    }
    
    public Object getConstructedGetProjectenRequestString() {
        //Get authentication ID
        String authenticationId = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.tornadoApplicationBean.authenticationId}");
        String days = (String) AdfmfJavaUtilities.getELValue("#{preferenceScope.application.tornadoPrefs.daysPref}");
        String date = searchDate;
        
        //Construct payload input
        AdfmfJavaUtilities.setELValue("#{bindings.MEDEWERKERID1.inputValue}", authenticationId);
        AdfmfJavaUtilities.setELValue("#{bindings.DAGEN1.inputValue}", days);
        AdfmfJavaUtilities.setELValue("#{bindings.DATUM1.inputValue}", date);
        
        //Construct payload
        return AdfmfJavaUtilities.getELValue("${bindings.payloadIterator2.currentRow.dataProvider}");
    }
}
