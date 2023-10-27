package com.club.badminton;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.club.badminton.entity.Player;
import com.club.badminton.repository.MatchRepository;
import com.club.badminton.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BadmintonApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PlayerRepository player;
    
    @MockBean
    private MatchRepository match;
    
    @Test
    @WithMockUser(username = "user", password = "password")
    void authTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @WithMockUser(username = "user", password = "password",roles ="ADMIN")
    void getAllPlayerTest() throws Exception {
    	Player p = new Player();
    	p.setName("Mithun");
    	p.setPassword("abc");
    	p.setPhoneNumber(1234567890);
    	List<Player> l=new ArrayList<Player>();
    	l.add(p);
    	when(player.findAll()).thenReturn(l);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/details"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Mithun")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("abc")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber", Matchers.is(1234567890)));
    }
    
    @Test
    @WithMockUser(username = "user", password = "password",roles ="ADMIN")
    void testCreatePlayer() throws Exception {
        Player player = new Player();
        player.setName("John Doe");
        player.setPassword("secret");
        player.setPhoneNumber(1234567890);

        String json = new ObjectMapper().writeValueAsString(player);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/createplayer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Player created!"));
    }
    
    @Test
    @WithMockUser(username = "user", password = "password",roles ="USER")
    void authTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/details"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
    }  
    
    @Test
    @WithMockUser(username = "user", password = "password",roles ="ADMIN")
    void getPlayerByIdTest() throws Exception {
    	Player p = new Player();
        p.setName("John Doe");
        p.setPassword("secret");
        p.setPhoneNumber(1234567890);
        when(player.findById(1234567890l)).thenReturn(Optional.of(p));
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/getplayer")
        		.param("id", String.valueOf(1234567890L)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John Doe")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("secret")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is(1234567890)));
    }
}
