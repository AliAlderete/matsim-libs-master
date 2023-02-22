package org.matsim.contrib.discrete_mode_choice.examples;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.junit.Test;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.contribs.discrete_mode_choice.components.estimators.AbstractTripRouterEstimator;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;
import org.matsim.contribs.discrete_mode_choice.model.trip_based.candidates.TripCandidate;
import org.matsim.contribs.discrete_mode_choice.modules.DiscreteModeChoiceConfigurator;
import org.matsim.contribs.discrete_mode_choice.modules.DiscreteModeChoiceModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.router.TripRouter;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.examples.ExamplesUtils;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.pt.config.TransitConfigGroup.TransitRoutingAlgorithmType;

public class MaaS_DCM_Estimator extends AbstractTripRouterEstimator {
        @Inject
        public MaaS_DCM_Estimator(TripRouter tripRouter, Network network, ActivityFacilities facilities) {
            super(tripRouter,facilities,null,null);
        }

        @Override
        protected double estimateTrip(Person person, String mode, DiscreteModeChoiceTrip trip,
                                      List<TripCandidate> previousTrips, List<? extends PlanElement> routedTrip) {
            // I) Calculate total travel time
            double totalTravelTime = 0.0;
            double totalTravelDistance = 0.0;

            for (PlanElement element : routedTrip) {
                if (element instanceof Leg) {
                    Leg leg = (Leg) element;
                    totalTravelTime = totalTravelTime + (leg.getTravelTime().seconds()/3600.0);//(leg.getTravelTime() / 3600.0;
                    totalTravelDistance += leg.getRoute().getDistance() * 1e-3;
                }
            }

            // II) Compute mode-specific utility
            double utility = 0;

            switch (mode) {
                case TransportMode.car:
                    utility = -0.5 - 0.025 * totalTravelDistance;
                    break;
                case TransportMode.pt:
                    utility = -0.15 - 0.15 * totalTravelTime;
                    break;
                case TransportMode.walk:
                    utility = -1.0 * totalTravelTime;
                    break;
            }

            return utility;
        }

}