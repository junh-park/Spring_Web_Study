package spittr.domain.data;

import spittr.domain.Spitter;

public interface SpitterRepository {

	Spitter save(Spitter unsaved);

	Spitter findByUsername(String username);

}
