package spittr.web;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.domain.Spittle;
import spittr.domain.data.SpittleRepository;
import spittr.domain.web.SpittleController;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
public class SpittrControllerTest {
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepo = mock(SpittleRepository.class);
		when(mockRepo.findSpittle(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();

		mockMvc.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(50);
		SpittleRepository mockRepo = mock(SpittleRepository.class);
		when(mockRepo.findSpittle(238900, 50)).thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		
		mockMvc.perform(get("/spittles?max=238900&count=50"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}
	
	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		SpittleRepository mockRepo = mock(SpittleRepository.class);
		when(mockRepo.findOne(12345)).thenReturn(expectedSpittle);
		
		SpittleController controller = new SpittleController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
	}
}
