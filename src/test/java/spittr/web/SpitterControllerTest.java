package spittr.web;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {
	
	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterController controller = new SpitterController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spitter/register"))
			.andExpect(view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepo = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jpark", "24hours", "Jun", "Park");
		Spitter saved = new Spitter(24L, "jpark", "24hours", "Jun", "Park");
		when(mockRepo.save(unsaved)).thenReturn(saved);
		
		SpitterController controller = new SpitterController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(post("/spitter/register")
				.param("firstName", "Jun")
				.param("lastName", "Park")
				.param("username", "jpark")
				.param("password", "24hours"))
				.andExpect(redirectedUrl("/spitter/jpark"));
		
		verify(mockRepo, atLeastOnce()).save(unsaved);
	}
}
