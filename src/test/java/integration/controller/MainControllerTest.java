package integration.controller;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import support.SpringMvcTestContainersSupport;

public class MainControllerTest extends SpringMvcTestContainersSupport {
    @Test
    public void shouldReturnSecurityRedirect() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/auth/signin"));
    }
    
    @Test
    public void shouldAuth() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.post("/auth/signin")
                                                   .param("email", "man4j@ya.ru")
                                                   .param("password", "123456"))
                                                   .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
    
    @Test
    public void shouldRememberMe() throws Exception {
        Cookie rememberMeCookie = getMockMvc().perform(MockMvcRequestBuilders.post("/auth/signin")
                                                                             .param("email", "man4j@ya.ru")
                                                                             .param("password", "123456")
                                                                             .param("rememberMe", "true"))
                                                                             .andReturn()
                                                                             .getResponse()
                                                                             .getCookie(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        
        Assert.assertNotNull(rememberMeCookie);

        getMockMvc().perform(MockMvcRequestBuilders.get("/").cookie(rememberMeCookie)).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
    
    @Test
    @WithMockUser
    public void shouldReturnMainView() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("/main"));
    }
}
    