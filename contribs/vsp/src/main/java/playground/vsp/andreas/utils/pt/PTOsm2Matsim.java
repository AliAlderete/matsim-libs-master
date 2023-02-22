package playground.vsp.andreas.utils.pt;

import java.util.Set;
import java.util.TreeSet;

import org.matsim.core.utils.geometry.transformations.TransformationFactory;

import playground.vsp.andreas.mzilske.osm.OsmPrepare;
import playground.vsp.andreas.osmBB.OsmTransitMain;

public class PTOsm2Matsim {
	
	public static void main(String[] args) {
		String osmRepository = "C:\\Users\\s210749\\Documents\\Modelling\\matsim-libs-master\\contribs\\vsp\\target\\";
		String osmFile = "staffordshire-latest.osm";

		String outDir = "C:\\Users\\s210749\\Documents\\Modelling\\matsim-libs-master\\contribs\\vsp\\target\\";
		String outName = "staffordshire-latest";
		
		String filteredOsmFile = outDir + outName + ".osm";
		
//		String[] streetFilter = new String[]{"motorway","motorway_link","trunk","trunk_link","primary","primary_link","secondary","tertiary","minor","unclassified","residential","living_street"};
		String[] streetFilter = new String[]{"motorway","motorway_link","trunk","trunk_link","primary","primary_link","secondary","tertiary"};
		
//		String[] transitFilter = new String[]{"ferry", "subway", "light_rail", "tram", "train", "bus", "trolleybus"};
		String[] transitFilter = new String[]{"fdsf"};
		
		OsmPrepare osmPrepare = new OsmPrepare(osmRepository + osmFile, filteredOsmFile, streetFilter, transitFilter);
		osmPrepare.prepareOsm();
				
		OsmTransitMain osmTransitMain = new OsmTransitMain(filteredOsmFile, TransformationFactory.WGS84, TransformationFactory.DHDN_GK4, outDir + outName + "_network.xml", outDir + outName + "_schedule.xml", "vehiclesOutFile");
		osmTransitMain.convertOsm2Matsim(transitFilter);
		
		PTNetworkSimplifier ptNetSimplifier = new PTNetworkSimplifier(outDir + outName + "_network.xml", outDir + outName + "_schedule.xml", outDir + outName + "_network_merged.xml", outDir + outName + "_schedule_merged.xml");
		Set<Integer> nodeTypesToMerge = new TreeSet<Integer>();
		nodeTypesToMerge.add(new Integer(4));
		nodeTypesToMerge.add(new Integer(5));
		ptNetSimplifier.setNodesToMerge(nodeTypesToMerge);
		ptNetSimplifier.setMergeLinkStats(false);
		ptNetSimplifier.simplifyPTNetwork();
		
	}

}
