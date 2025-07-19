package helper;

import org.apache.catalina.connector.Request;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

public class SessionHelper {

    public static void removeMessage(){
        try{
            HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).  getRequest().getSession();
            session.removeAttribute("message");
        }
        catch (Exception e){
            System.out.println("SessionHelper.removeMessage() : " + e.getMessage());
        }
    }

}
