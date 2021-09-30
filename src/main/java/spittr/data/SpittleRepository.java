package spittr.data;

import java.util.List;

import spittr.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittle(long max, int count);

	Spittle findOne(long spittleId);
}
