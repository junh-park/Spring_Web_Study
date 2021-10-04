package spittr.domain.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {
	private long spittleId;

	public SpittleNotFoundException(long spittleId) {
		this.spittleId = spittleId;
	}

	public long getSpittleId() {
		return spittleId;
	}

}
