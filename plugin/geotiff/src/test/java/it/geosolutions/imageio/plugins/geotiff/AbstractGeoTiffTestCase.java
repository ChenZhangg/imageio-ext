/*
* JImageIO-extension - OpenSource Java Image translation Library
* http://www.geo-solutions.it/
* (C) 2007, GeoSolutions
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation;
* version 2.1 of the License.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*/
package it.geosolutions.imageio.plugins.geotiff;

import javax.media.jai.JAI;

import junit.framework.TestCase;

public class AbstractGeoTiffTestCase extends TestCase {

	public AbstractGeoTiffTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// general settings
		JAI.getDefaultInstance().getTileScheduler().setParallelism(1);
		JAI.getDefaultInstance().getTileScheduler().setPriority(5);
		JAI.getDefaultInstance().getTileScheduler().setPrefetchPriority(5);
		JAI.getDefaultInstance().getTileScheduler().setPrefetchParallelism(1);
		JAI.getDefaultInstance().getTileCache().setMemoryCapacity(
				180 * 1024 * 1024);
		JAI.getDefaultInstance().getTileCache().setMemoryThreshold(1.0f);
	}
}
