package am.itspace.demo;

import am.itspace.demo.model.Role;
import am.itspace.demo.model.User;
import am.itspace.demo.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ListingAppApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper obj;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void adminGetAll() throws Exception {
        mvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void userGetAll() throws Exception {
        mvc.perform(get("/users")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void addUser() throws Exception {
        User user = User.builder()
                .id(15)
                .email("george@gmail.com")
                .name("george")
                .surname("smith")
                .role(Role.USER)
                .build();
        when(userServiceImpl.addUser(user)).thenReturn(user);
        String jsonObj = obj.writeValueAsString(user);
        mvc.perform(post("/users").content(jsonObj).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }



}
