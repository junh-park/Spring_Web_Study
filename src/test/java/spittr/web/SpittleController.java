package spittr.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spittr.domain.Spittle;
import spittr.domain.data.Error;
import spittr.domain.data.SpittleRepository;
import spittr.domain.web.SpittleNotFoundException;

@RestController
@RequestMapping("/spittles")
public class SpittleController {
	private static final String MAX_LONG_AS_STRING = "190231085408123123";
	private SpittleRepository spittleRepo;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepo = spittleRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepo.findSpittle(max, count);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
		Spittle savedSpittle = spittleRepo.save(spittle);
		
		HttpHeaders headers = new HttpHeaders();
		URI locationUri = ucb.path("/spittles/").path(String.valueOf(spittle.getId())).build().toUri();
		headers.setLocation(locationUri);
		
		ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(
				savedSpittle,  headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Spittle Spittle(@PathVariable long id) {
		Spittle spittle = spittleRepo.findOne(id);
		if (spittle == null) throw new SpittleNotFoundException(id);
		return spittle;
	}
	
	@ExceptionHandler(SpittleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error spittleNotFound(SpittleNotFoundException e) {
		long spittleId = e.getSpittleId();
		return new Error(4, "SSpittle [" + spittleId + "] not found");
	}
	
}
