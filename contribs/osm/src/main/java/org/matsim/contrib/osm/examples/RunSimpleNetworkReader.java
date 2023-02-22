package org.matsim.contrib.osm.examples;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.contrib.osm.networkReader.SupersonicOsmNetworkReader;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;

public class RunSimpleNetworkReader {

	private static final String inputFile = "C:\\Users\\s210749\\Documents\\Modelling\\matsim-libs-master\\berkshire-latest.osm.pbf";
	private static final String outputFile = "C:\\Users\\s210749\\Documents\\Modelling\\matsim-libs-master\\berkshire.xml.gz";
	private static final CoordinateTransformation coordinateTransformation = TransformationFactory.getCoordinateTransformation(TransformationFactory.WGS84, "EPSG:25832");

	public static void main(String[] args) {

		Network network = new SupersonicOsmNetworkReader.Builder()
				.setCoordinateTransformation(coordinateTransformation)
				.build()
				.read(inputFile);

		new NetworkWriter(network).write(outputFile);
	}

}
