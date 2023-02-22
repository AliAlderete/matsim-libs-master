
/* *********************************************************************** *
 * project: org.matsim.*
 * TestDESStarter_EquilPopulationPlans1Modified1.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2019 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

 package org.matsim.core.mobsim.jdeqsim;

import org.junit.Test;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.mobsim.jdeqsim.scenarios.EquilPopulationPlans1Modified1;
import org.matsim.core.scenario.ScenarioUtils;

import static org.junit.Assert.assertEquals;

public class TestDESStarter_EquilPopulationPlans1Modified1 extends AbstractJDEQSimTest {

	@Test
	public void test_EquilPopulationPlans1Modified1_TestHandlerDetailedEventChecker() {
		Config config = ConfigUtils.loadConfig("test/scenarios/equil/config.xml");
		config.plans().setInputFile("plans1.xml");
		MatsimRandom.reset(config.global().getRandomSeed());
		Scenario scenario = ScenarioUtils.createScenario(config);
		ScenarioUtils.loadScenario(scenario);
		
		new EquilPopulationPlans1Modified1().modifyPopulation(scenario.getPopulation());
		this.runJDEQSim(scenario);
		
		assertEquals(scenario.getPopulation().getPersons().size(), super.eventsByPerson.size());
		super.checkAscendingTimeStamps();
		super.checkEventsCorrespondToPlans(scenario.getPopulation());
		super.compareToDEQSimTravelTimes(utils.getPackageInputDirectory() + "deq_events.txt", 1.0);
	}

}
