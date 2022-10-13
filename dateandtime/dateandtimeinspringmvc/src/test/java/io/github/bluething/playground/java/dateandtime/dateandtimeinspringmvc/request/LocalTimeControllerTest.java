package io.github.bluething.playground.java.dateandtime.dateandtimeinspringmvc.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocalTimeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void localTimeWillReturnTimeStringSameWithTheRequest() throws Exception {
        String input = "14:00";
        MvcResult result = mockMvc.perform(get("/localtime/{time}", input))
                .andReturn();

        Assertions.assertEquals(input, result.getResponse().getContentAsString());
    }

    @Test
    public void localTimeWillReturnClientErrorWhenRequestInWrongFormat() throws Exception {
        String input = "14:00:01";
        mockMvc.perform(get("/localtime/{time}", input))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void integerTimeWillReturnTimeStringSameWithTheRequest() throws Exception {
        String input = "85455";
        MvcResult result = mockMvc.perform(get("/integerastime/{time}", input))
                .andReturn();

        Assertions.assertEquals("23:44", result.getResponse().getContentAsString());
    }
}
