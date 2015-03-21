package nl.whitehorses.tornado.beans;

import java.util.logging.Logger;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.model.AdfELContext;

public class AuthenticationBean {
    public AuthenticationBean() {
        super();
    }

    public void loginActionListener(ActionEvent actionEvent) {
        //Execute login method
        AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.process.execute}", Object.class, new Class[] {});
        me.invoke(adfELContext, new Object[] {});
        
        //Get authentication ID
        String authenticationId = (String) AdfmfJavaUtilities.getELValue("#{bindings.Return.inputValue}");
        
        //Set authentication ID
        AdfmfJavaUtilities.setELValue("#{applicationScope.tornadoApplicationBean.authenticationId}", authenticationId);
        
        //Navigate to overview feature
        AdfmfContainerUtilities.gotoFeature("nl.whitehorses.tornado.Overview");
    }
}
