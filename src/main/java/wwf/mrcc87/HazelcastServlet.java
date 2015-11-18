package wwf.mrcc87;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hazelcast.core.IMap;
import com.igt.ssm.common.util.hz.HazelCastManager;

/**
 *@author Mircea Samuila
 * */


public class HazelcastServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    System.out.println("INSIDE THE SERVLET...");
    IMap<String, String> servletMap = HazelCastManager.getStringTest();
    System.out.println(servletMap.get("test1"));
    System.out.println(servletMap.get("test2"));
    request.getRequestDispatcher("/pages/success.html").forward(request, response);
}
	
}
