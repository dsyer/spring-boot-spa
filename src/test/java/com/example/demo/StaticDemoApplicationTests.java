package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StaticDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void missing() throws Throwable {
		mockMvc.perform(get("/missing")).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/"));
	}

	@Test
	public void home() throws Throwable {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

}
