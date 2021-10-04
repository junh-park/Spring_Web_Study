package spittr.domain.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.domain.Spittle;
import spittr.domain.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;
	private SpittleRepository spittleRepo;

	@Autowired
	public SpittleController(SpittleRepository spittleRepo) {
		this.spittleRepo = spittleRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepo.findSpittle(max, count);
	}

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		Spittle spittle = spittleRepo.findOne(spittleId);
		if (spittle == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittleRepo.findOne(spittleId));
		return "spittle";
	}
}
