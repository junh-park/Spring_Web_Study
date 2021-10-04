package spittr.domain.data;

import java.util.List;

import spittr.domain.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittle(long max, int count);

	Spittle findOne(long spittleId);

	Spittle save(Spittle spittle);
}
