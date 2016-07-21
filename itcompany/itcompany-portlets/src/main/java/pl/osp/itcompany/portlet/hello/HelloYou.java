package pl.osp.itcompany.portlet.hello;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class HelloYou
 */
public class HelloYou extends MVCPortlet {

    protected String editJSP;
    protected String viewJSP;

    @Override
    public void init() throws PortletException {

        editJSP = "/html/helloyou/edit.jsp";
        viewJSP = "/html/helloyou/view.jsp";
    }

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        PortletPreferences prefs = renderRequest.getPreferences();
        String username = (String) prefs.getValue("name", "no");
        if (username.equalsIgnoreCase("no")) {
            username = "";
        }
        renderRequest.setAttribute("userName", username);
        include(viewJSP, renderRequest, renderResponse);
    }

    @Override
    public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        renderResponse.setContentType("text/html");
        //URL przekazany do portletu w stanie edit aby wskazać odpowiedni link do tego samego portletu
        PortletURL addNameURL = renderResponse.createActionURL();
        addNameURL.setParameter("addName", "addName");
        renderRequest.setAttribute("addNameURL", addNameURL.toString());
        include(editJSP, renderRequest, renderResponse);
    }

    @Override
    protected boolean callActionMethod(ActionRequest request, ActionResponse response) throws PortletException {
        System.out.println("call action");
        return super.callActionMethod(request, response);
    }

    //TODO odkomentować i poprawić jezeli ma dzialac podstawowa funkcjonalność portletu
    /*     @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
            throws IOException, PortletException {
        System.out.println("main process action");
        String addName = actionRequest.getParameter("addName");
        if (addName != null) {
            PortletPreferences prefs = actionRequest.getPreferences();
            prefs.setValue("name", actionRequest.getParameter("username"));
            prefs.store();
            actionResponse.setPortletMode(PortletMode.VIEW);
        }
    }*/

    ////////////////
    //to jest druga opcja - dla kazdego action jest oddzielna metoda
    @ProcessAction(name = "addSecondNameActionURL")
    public void addSecondNameActionURL(ActionRequest request, ActionResponse response)
            throws PortletException, java.io.IOException {
        System.out.println("second process action");
        response.setPortletMode(PortletMode.VIEW);
    }

    ////////////

    protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
        } else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }

}
