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
package it.geosolutions.imageio.plugins.jp2kakadu;

import it.geosolutions.imageio.stream.output.FileImageOutputStreamExtImpl;
import it.geosolutions.resources.TestData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.media.jai.JAI;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.RenderedOp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class JP2KJaiWriteTest extends AbstractJP2KTestCase {

	final static String testFileName = "CB_TM432.jp2";

	// final static String testFileName = "bogota.jp2";

	public JP2KJaiWriteTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// multithreading settings
		JP2GDALKakaduImageReaderSpi.setReadMultithreadingLevel(2);
		JP2GDALKakaduImageWriterSpi.setWriteMultithreadingLevel(2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Clevels" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Clevels() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Clevels.

		final int firstClevelsParam = 2;
		final int secondClevelsParam = 6;
		final String fileName1 = new StringBuffer("Clevels-").append(
				Integer.toString(firstClevelsParam)).append("-.jp2").toString();
		final String fileName2 = new StringBuffer("Clevels-").append(
				Integer.toString(secondClevelsParam)).append("-.jp2")
				.toString();
		final File outputFile1 = TestData.temp(this, fileName1, false);
		final File outputFile2 = TestData.temp(this, fileName2, false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		// Reading
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setClevels(firstClevelsParam);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setClevels(secondClevelsParam);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Clayers" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Clayers() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Clayers.

		final int firstClayersParam = 3;
		final int secondClayersParam = 20;
		final String fileName1 = new StringBuffer("Clayers-").append(
				Integer.toString(firstClayersParam)).append("-.jp2").toString();
		final String fileName2 = new StringBuffer("Clayers-").append(
				Integer.toString(secondClayersParam)).append("-.jp2")
				.toString();
		final File outputFile1 = TestData.temp(this, fileName1, false);
		final File outputFile2 = TestData.temp(this, fileName2, false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		// Reading
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setClayers(firstClayersParam);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setClayers(secondClayersParam);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Cprecincts" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Cprecincts() throws IOException,
			FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Cprecincts.
		final File outputFile1 = TestData.temp(this, "CprecintsA.jp2", false);
		final File outputFile2 = TestData.temp(this, "CprecintsB.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param)
				.setCprecincts("{256,256},{128,128}");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2)
				.setCprecincts("{512,512},{256,512},{128,512},{64,512},{32,512},{16,512},{8,512},{4,512},{2,512}");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Corder" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Corder() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Corder.
		final File outputFile1 = TestData.temp(this, "CorderPCRL.jp2", false);
		final File outputFile2 = TestData.temp(this, "CorderRPCL.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setCorder("PCRL");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setCorder("RPCL");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Cblk" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Cblk() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Cblk.
		final File outputFile1 = TestData.temp(this, "cblk16x16.jp2", false);
		outputFile1.deleteOnExit();
		final File outputFile2 = TestData.temp(this, "cblk64x64.jp2", false);
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setCblk("{16,16}");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setCblk("{64,64}");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "CModes" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////

	public void testWrite_Cmodes() throws IOException, FileNotFoundException {
		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");

		final ImageReadParam irp = new ImageReadParam();

		Integer xSubSampling = new Integer(32);
		Integer ySubSampling = new Integer(32);
		Integer xSubSamplingOffset = new Integer(0);
		Integer ySubSamplingOffset = new Integer(0);

		irp.setSourceSubsampling(xSubSampling.intValue(), ySubSampling
				.intValue(), xSubSamplingOffset.intValue(), ySubSamplingOffset
				.intValue());

		pbjImageRead.setParameter("Input", inputFile);
		pbjImageRead.setParameter("readParam", irp);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (i-TH version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		final String[] createVersions = { "BYPASS", "BYPASS|RESTART|CAUSAL",
				"RESTART|ERTERM", "RESET" };
		final String[] filenameVersions = { "BYPASS", "BYPASSRESTARTCAUSAL",
				"RESTARTERTERM", "RESET" };

		final int numberOfVersions = createVersions.length;
		for (int i = 0; i < numberOfVersions; i++) {

			// Output files resulting from different values of the same create
			// option. In this test, the create option is ORGtparts.
			final String filenameVersion = filenameVersions[i];
			final StringBuffer fileName = new StringBuffer("CModes").append(
					filenameVersion).append(".jp2");
			final File outputFile = TestData.temp(this, fileName.toString(),
					false);
			outputFile.deleteOnExit();

			// Setting output and writer
			final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
					"ImageWrite");
			pbjImageWrite.setParameter("Output",
					new FileImageOutputStreamExtImpl(outputFile));
			ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
					.createWriterInstance();
			pbjImageWrite.setParameter("Writer", writer);

			// Specifying image source to write
			pbjImageWrite.addSource(image);
			ImageWriteParam param = writer.getDefaultWriteParam();

			// Specifying the required create option
			((JP2GDALKakaduImageWriteParam) param).setCmodes(createVersions[i]);

			pbjImageWrite.setParameter("writeParam", param);

			// Writing
			final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);
		}
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Cycc" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Cycc() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is ORGgen_plt.
		final File outputFile1 = TestData.temp(this, "Cycc-Y.jp2", false);
		final File outputFile2 = TestData.temp(this, "Cycc-N.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setCycc("yes");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setCycc("no");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "GMLJp2" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_GMLJp2() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, "bogota.jp2");
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is GMLJp2.
		final File outputFile1 = TestData.temp(this, "GML.jp2", false);
		final File outputFile2 = TestData.temp(this, "NO-GML.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setGMLJp2("YES");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setGMLJp2("NO");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "GeoJp2" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_GeoJp2() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, "bogota.jp2");
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is GeoJp2.
		final File outputFile1 = TestData.temp(this, "GeoJp2.jp2", false);
		final File outputFile2 = TestData.temp(this, "NO-GeoJp2.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setGeoJp2("YES");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setGeoJp2("NO");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "ORGtparts" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////

	public void testWrite_ORGtparts() throws IOException, FileNotFoundException {
		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");

		final ImageReadParam irp = new ImageReadParam();

		Integer xSubSampling = new Integer(8);
		Integer ySubSampling = new Integer(8);
		Integer xSubSamplingOffset = new Integer(0);
		Integer ySubSamplingOffset = new Integer(0);

		irp.setSourceSubsampling(xSubSampling.intValue(), ySubSampling
				.intValue(), xSubSamplingOffset.intValue(), ySubSamplingOffset
				.intValue());

		pbjImageRead.setParameter("Input", inputFile);
		pbjImageRead.setParameter("readParam", irp);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (i-TH version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		final String[] createVersions = { "R", "C", "L", "R|C", "R|L", "L|C",
				"R|L|C" };
		final String[] filenameVersions = { "R", "C", "L", "RC", "RL", "LC",
				"RLC" };

		final int numberOfVersions = createVersions.length;
		for (int i = 0; i < numberOfVersions; i++) {

			// Output files resulting from different values of the same create
			// option. In this test, the create option is ORGtparts.
			final String filenameVersion = filenameVersions[i];
			final StringBuffer fileName = new StringBuffer("ORGtparts").append(
					filenameVersion).append(".jp2");
			final File outputFile = TestData.temp(this, fileName.toString(),
					false);
			outputFile.deleteOnExit();

			// Setting output and writer
			final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
					"ImageWrite");
			pbjImageWrite.setParameter("Output",
					new FileImageOutputStreamExtImpl(outputFile));
			ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
					.createWriterInstance();
			pbjImageWrite.setParameter("Writer", writer);

			// Specifying image source to write
			pbjImageWrite.addSource(image);
			ImageWriteParam param = writer.getDefaultWriteParam();

			// Specifying the required create option
			((JP2GDALKakaduImageWriteParam) param).setORGgen_plt("yes");
			((JP2GDALKakaduImageWriteParam) param).setLayers(10);
			((JP2GDALKakaduImageWriteParam) param).setCorder("LRCP");
			((JP2GDALKakaduImageWriteParam) param).setTiling(1024, 1024);
			((JP2GDALKakaduImageWriteParam) param)
					.setORGtparts(createVersions[i]);

			pbjImageWrite.setParameter("writeParam", param);

			// Writing
			final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);
		}
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "ORGgen_plt" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_ORGgen_plt() throws IOException,
			FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is ORGgen_plt.
		final File outputFile1 = TestData.temp(this, "ORGgen_plt-Y.jp2", false);
		final File outputFile2 = TestData.temp(this, "ORGgen_plt-N.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setORGgen_plt("yes");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setORGgen_plt("no");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "ORGgen_tlm" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////

	// Setting this parameter requires a bit of attention. Here below, there is
	// the kakadu documentation related to this paramater:
	// ------------------------------------------------------------------------
	// Requests the insertion of TLM (tile-part-length) marker segments in the
	// main header, to facilitate random access to the code-stream. This
	// attribute takes a single integer-valued parameter, which identifies the
	// maximum number of tile-parts which will be written to the code-stream for
	// each tile. The reason for including this parameter is that space for the
	// TLM information must be reserved ahead of time; once the entire
	// code-stream has been written the generation machinery goes back and
	// overwrites this reserved space with actual TLM data. If the actual number
	// of tile-parts which are generated is less than the value supplied here,
	// empty tile-parts will be inserted into the code-stream so as to use up
	// all of the reserved TLM space. For this reason, you should try to
	// estimate the maximum number of tile-parts you will need as accurately as
	// possible, noting that the actual value may be hard to determine ahead of
	// time if incremental flushing features are to be employed. An error will
	// be generated at run-time if the number of declared maximum number of
	// tile-parts turns out to be insufficient.
	// ------------------------------------------------------------------------
	public void testWrite_ORGgen_tlm() throws IOException,
			FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		final File outputFile1 = TestData.temp(this, "ORGgen_tlm0-.jp2", false);
		outputFile1.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setORGgen_tlm(0);
		pbjImageWrite.setParameter("writeParam", param);

	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "COMSEG" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_COMSEG() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is COMSEG.
		final File outputFile1 = TestData.temp(this, "COMSEG.jp2", false);
		final File outputFile2 = TestData.temp(this, "NO-COMSEG.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setComseg("YES");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setComseg("NO");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "SProfile" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_SProfile() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is SProfile.
		final File outputFile1 = TestData.temp(this, "SProfile1.jp2", false);
		outputFile1.deleteOnExit();
		final File outputFile2 = TestData.temp(this, "SProfile2.jp2", false);
		outputFile2.deleteOnExit();
		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setSProfile(1);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setSProfile("PROFILE2");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Tiling" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Tiling() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the tiling.
		final int firstTilingParam = 128;
		final int secondTilingParam = 256;
		final String fileName1 = new StringBuffer("Tiled-").append(
				Integer.toString(firstTilingParam)).append(".jp2").toString();
		final String fileName2 = new StringBuffer("Tiled-").append(
				Integer.toString(secondTilingParam)).append(".jp2").toString();

		final File outputFile1 = TestData.temp(this, fileName1, false);
		outputFile1.deleteOnExit();
		final File outputFile2 = TestData.temp(this, fileName2, false);
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		// Reading
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setTiling(firstTilingParam,
				firstTilingParam);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setTiling(secondTilingParam,
				secondTilingParam);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "ROI" Create Options and related options.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_ROI() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is SProfile.
		final File outputFile1 = TestData.temp(this, "ROI-NO_ROI.jp2", false);
		final File outputFile2 = TestData.temp(this, "ROI-parametrized-.jp2",
				false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");

		final ImageReadParam irp = new ImageReadParam();

		Integer xSubSampling = new Integer(1);
		Integer ySubSampling = new Integer(1);
		Integer xSubSamplingOffset = new Integer(0);
		Integer ySubSamplingOffset = new Integer(0);

		irp.setSourceSubsampling(xSubSampling.intValue(), ySubSampling
				.intValue(), xSubSamplingOffset.intValue(), ySubSamplingOffset
				.intValue());

		pbjImageRead.setParameter("readParam", irp);
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////

		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setClayers(20);
		((JP2GDALKakaduImageWriteParam) param2).setROI("0,0,100,100");
		((JP2GDALKakaduImageWriteParam) param2).setRweight(64000f);
		((JP2GDALKakaduImageWriteParam) param2)
				.setCprecincts("{256,256},{128,128},{64,64},{32,32}");

		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Qguard" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Qguard() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Qguard.
		final File outputFile1 = TestData.temp(this, "Qguard1.jp2", false);
		final File outputFile2 = TestData.temp(this, "Qguard2.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setQguard(1);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setQguard(2);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Qstep" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Qstep() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 3 different values of the same create
		// option. In this test, the create option is Qstep.
		final float firstQstepParam = 0.2f;
		final float secondQstepParam = 1.7f;
		final String fileName1 = new StringBuffer("Qstep-").append(
				Float.toString(firstQstepParam)).append("f-.jp2").toString();
		final String fileName2 = new StringBuffer("Qstep-").append(
				Float.toString(secondQstepParam)).append("f-.jp2").toString();
		final String fileName3 = "Qstep-Default-.jp2";
		final File outputFile1 = TestData.temp(this, fileName1, false);
		final File outputFile2 = TestData.temp(this, fileName2, false);
		final File outputFile3 = TestData.temp(this, fileName3, false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();
		outputFile3.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setQstep(firstQstepParam);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setQstep(secondQstepParam);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (3ND version of the create option - DEFAULT)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite3 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite3.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile3));
		ImageWriter writer3 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite3.setParameter("Writer", writer3);

		// Specifying image source to write
		pbjImageWrite3.addSource(image);
		ImageWriteParam param3 = writer3.getDefaultWriteParam();

		// Specifying the required create option
		pbjImageWrite3.setParameter("writeParam", param3);

		// Writing
		final RenderedOp op3 = JAI.create("ImageWrite", pbjImageWrite3);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "Quality" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	// TODO: Investigate the strange displaying of a 100% quality factor write
	// of an input UINT16 jpeg2000 image.
	public void testWrite_Quality() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is Qstep.
		final float firstQualityParam = 99.2f;
		final float secondQualityParam = 1f;
		final String fileName1 = new StringBuffer("Quality-").append(
				Float.toString(firstQualityParam)).append("f-.jp2").toString();
		final String fileName2 = new StringBuffer("Quality-").append(
				Float.toString(secondQualityParam)).append("f-.jp2").toString();
		final File outputFile1 = TestData.temp(this, fileName1, false);
		final File outputFile2 = TestData.temp(this, fileName2, false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setQuality(firstQualityParam);
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setQuality(secondQualityParam);
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing "FLUSH" Create Option.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_FLUSH() throws IOException, FileNotFoundException {

		// //
		// Preparing input/output files
		// //
		final File inputFile = TestData.file(this, testFileName);
		assertTrue(inputFile.exists());

		// Output files resulting from 2 different values of the same create
		// option. In this test, the create option is FLUSH.
		final File outputFile1 = TestData.temp(this, "FLUSH.jp2", false);
		final File outputFile2 = TestData.temp(this, "NO-FLUSH.jp2", false);
		outputFile1.deleteOnExit();
		outputFile2.deleteOnExit();

		// //
		// Preparing to read
		// //
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (1ST version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile1));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param).setFlush("YES");
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write (2ND version of the create option test)
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite2 = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite2.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile2));
		ImageWriter writer2 = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite2.setParameter("Writer", writer2);

		// Specifying image source to write
		pbjImageWrite2.addSource(image);
		ImageWriteParam param2 = writer2.getDefaultWriteParam();

		// Specifying the required create option
		((JP2GDALKakaduImageWriteParam) param2).setFlush("NO");
		pbjImageWrite2.setParameter("writeParam", param2);

		// Writing
		final RenderedOp op2 = JAI.create("ImageWrite", pbjImageWrite2);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Testing multithreading capabilities.
	//
	// ////////////////////////////////////////////////////////////////////////
	public void testWrite_Multithreading() throws IOException,
			FileNotFoundException {

		// Preparing input/output files
		final File inputFile = TestData.file(this, testFileName);
		final File outputFile = TestData.temp(this, "multithreadingwrite.jp2",
				false);
		outputFile.deleteOnExit();
		assertTrue(inputFile.exists());

		// Setting read-multithreading capabilities
		JP2GDALKakaduImageReaderSpi.setReadMultithreadingLevel(5);
		// Setting write-multithreading capabilities
		JP2GDALKakaduImageWriterSpi.setWriteMultithreadingLevel(5);

		// Reading
		final ParameterBlockJAI pbjImageRead = new ParameterBlockJAI(
				"ImageRead");
		pbjImageRead.setParameter("Input", inputFile);
		RenderedOp image = JAI.create("ImageRead", pbjImageRead);

		// ////////////////////////////////////////////////////////////////////
		//
		// preparing to write
		//
		// ////////////////////////////////////////////////////////////////////
		// Setting output and writer
		final ParameterBlockJAI pbjImageWrite = new ParameterBlockJAI(
				"ImageWrite");
		pbjImageWrite.setParameter("Output", new FileImageOutputStreamExtImpl(
				outputFile));
		ImageWriter writer = new JP2GDALKakaduImageWriterSpi()
				.createWriterInstance();
		pbjImageWrite.setParameter("Writer", writer);

		// Specifying image source to write
		pbjImageWrite.addSource(image);
		ImageWriteParam param = writer.getDefaultWriteParam();
		pbjImageWrite.setParameter("writeParam", param);

		// Writing
		final RenderedOp op = JAI.create("ImageWrite", pbjImageWrite);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();

		// Testing "Clevels" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Clevels"));

		// Testing "Clayers" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Clayers"));

		// Testing "Cprecincts" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Cprecincts"));

		// Testing "Corder" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Corder"));

		// Testing "Cblk" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Cblk"));

		// Testing "Cycc" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Cycc"));

		// Testing "Cmodes" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Cmodes"));

		// Testing "SProfile" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_SProfile"));

		// Testing "Quality" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Quality"));

		// Testing "ORGtparts" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_ORGtparts"));

		// Testing "ORGgen_plt" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_ORGgen_plt"));

		// Testing "ORGgen_tlm" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_ORGgen_tlm"));

		// Testing "GMLJp2" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_GMLJp2"));

		// Testing "GeoJp2" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_GeoJp2"));

		// Testing "ROI" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_ROI"));

		// Testing "Qguard" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Qguard"));

		// Testing "Qstep" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Qstep"));

		// Testing "COMSEG" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_COMSEG"));

		// Testing "FLUSH" Create Option.
		suite.addTest(new JP2KJaiWriteTest("testWrite_FLUSH"));

		// Testing multithreading capabilities.
		suite.addTest(new JP2KJaiWriteTest("testWrite_Multithreading"));

		// Testing "Tiling"
		suite.addTest(new JP2KJaiWriteTest("testWrite_Tiling"));

		return suite;
	}

	public static void main(java.lang.String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
