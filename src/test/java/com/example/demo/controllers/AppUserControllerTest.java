package com.example.demo.controllers;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.InMemoryAppUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Hassan Ali
 * @since 24the December 2024
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppUserControllerTest {
    private MockMvc mockMvc;
    private static final InMemoryAppUserRepository inMemoryRepo = new InMemoryAppUserRepository();

    @BeforeEach
    void setUp() {
        AppUserController controller = new AppUserController();
        controller.appUserRepository = inMemoryRepo;
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



    @Test
    @Order(1)
    void testCreateUser() throws Exception {

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"email\": \"testuser@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));

    }

    @Test
    @Order(2)
    void testUpdateUser() throws Exception {
        // Assuming we have a user with ID 1 already created
        int userId = 1;
        String updatedUser = "{\"username\":\"testuser\", \"email\":\"testuser@example.com\"}";

        mockMvc.perform(put("/api/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    @Order(3)
    void testReturnAllUsers() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("John");
        user.setEmail("john@example.com");
        inMemoryRepo.save(user);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].username").value("John"))
                .andExpect(jsonPath("$[2].email").value("john@example.com"));
    }


    @Test
    @Order(4)
    void testGetUserById() throws Exception {

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }


    @Test
    @Order(5)
    void testDeleteUser() throws Exception {
        // Assuming we have a user with ID 1 that we want to delete
        int userId = 1;
        mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isOk());
    }

}