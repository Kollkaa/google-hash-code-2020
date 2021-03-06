package com.google.fantasticgeneration.hashcode_2020.logic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.Status;

public class PickBasedOnScore extends PickNextLibrary {

	final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public Library pickNext(Status status, int curTime) {
		final List<Library> available = status.getLibraries().stream().filter(l -> l.getSignupDay() == -1)
				.collect(Collectors.toList());
		int bestScore = 0;
		Library bestLibrary = null;
		for (final Library one : available) {
			final int curScore = computeScore(one, status.getDeliveredBooks(), curTime, status.getMaxDays());
			if (curScore > bestScore) {
				bestScore = curScore;
				bestLibrary = one;
			} else if ((curScore > 0) && (curScore == bestScore)) {
				if (one.getSignupTime() < bestLibrary.getSignupTime()) {
					bestLibrary = one;
				}
			}
		}
		LOG.info("At time '{}' we picked library '{}' with score '{}'", curTime, bestLibrary, bestScore);
		return bestLibrary;
	}

}
