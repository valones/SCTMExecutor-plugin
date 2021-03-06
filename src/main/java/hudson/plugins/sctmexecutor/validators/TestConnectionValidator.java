package hudson.plugins.sctmexecutor.validators;

import hudson.util.FormValidation;

import java.net.URL;
import java.rmi.RemoteException;

import com.borland.sctm.ws.logon.SystemService;
import com.borland.sctm.ws.logon.SystemServiceServiceLocator;

public class TestConnectionValidator {
  public FormValidation check(String serviceURL, String user, String password) {
    try {
      SystemService systemService = new SystemServiceServiceLocator().getsccsystem(new URL(serviceURL + "/sccsystem?wsdl")); //$NON-NLS-1$
      try {
        systemService.logonUser(user, password);
        return FormValidation.ok(Messages.getString("TestConnectionValidator.msg.connectionSuccessful")); //$NON-NLS-1$
      } catch (RemoteException e) {
        return FormValidation.error(Messages.getString("TestConnectionValidator.msg.wrongLoginData")); //$NON-NLS-1$
      }
    } catch (Exception e) {
      return FormValidation.error(Messages.getString("TestConnectionValidator.msg.connectionFailed")); //$NON-NLS-1$
    }
  }

}
