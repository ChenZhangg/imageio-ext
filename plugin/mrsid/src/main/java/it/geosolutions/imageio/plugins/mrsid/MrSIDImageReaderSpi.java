package it.geosolutions.imageio.plugins.mrsid;

import it.geosolutions.imageio.gdalframework.GDALImageReaderSpi;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageReader;

/**
 * Service provider interface for MrSid image reader
 * 
 * @author Daniele Romagnoli
 * @author Simone Giannecchini
 * 
 */
public class MrSIDImageReaderSpi extends GDALImageReaderSpi {

	private static final Logger logger = Logger
			.getLogger(MrSIDImageReaderSpi.class.toString());

	static final String[] suffixes = { "sid", "sdw" };

	static final String[] formatNames = { "MrSID" };

	static final String[] MIMETypes = { "image/sid"};

	static final String version = "1.0";

	static final String readerCN = "it.geosolutions.imageio.plugins.mrsid.MrSIDImageReader";

	static final String vendorName = "GeoSolutions";

	// writerSpiNames
	static final String[] wSN = {/* "it.geosolutions.imageio.plugins.mrsid.MrSIDImageReaderSpi" */null };

	// StreamMetadataFormatNames and StreamMetadataFormatClassNames
	static final boolean supportsStandardStreamMetadataFormat = false;

	static final String nativeStreamMetadataFormatName = null;

	static final String nativeStreamMetadataFormatClassName = null;

	static final String[] extraStreamMetadataFormatNames = { null };

	static final String[] extraStreamMetadataFormatClassNames = { null };

	// ImageMetadataFormatNames and ImageMetadataFormatClassNames
	static final boolean supportsStandardImageMetadataFormat = false;

	static final String nativeImageMetadataFormatName = MrSIDIIOImageMetadata.mrsidImageMetadataName;

	static final String nativeImageMetadataFormatClassName = MrSIDIIOImageMetadataFormat.class.toString();

	static final String[] extraImageMetadataFormatNames = { null };

	static final String[] extraImageMetadataFormatClassNames = { null };

	public MrSIDImageReaderSpi() {
		super(
				vendorName,
				version,
				formatNames,
				suffixes,
				MIMETypes,
				readerCN, // readerClassName
				STANDARD_INPUT_TYPE,
				wSN, // writer Spi Names
				supportsStandardStreamMetadataFormat,
				nativeStreamMetadataFormatName,
				nativeStreamMetadataFormatClassName,
				extraStreamMetadataFormatNames,
				extraStreamMetadataFormatClassNames,
				supportsStandardImageMetadataFormat,
				nativeImageMetadataFormatName,
				nativeImageMetadataFormatClassName,
				extraImageMetadataFormatNames,
				extraImageMetadataFormatClassNames);
		if (logger.isLoggable(Level.FINE))
			logger.fine("MrSIDImageReaderSpi Constructor");

	}

	/**
	 * This method checks if the provided input can be decoded from this SPI
	 */
	public boolean canDecodeInput(Object input) throws IOException {
		return super.canDecodeInput(input);
	}

	/**
	 * Returns an instance of the MrSIDImageReader
	 * 
	 * @see javax.imageio.spi.ImageReaderSpi#createReaderInstance(java.lang.Object)
	 */
	public ImageReader createReaderInstance(Object source) throws IOException {
		return new MrSIDImageReader(this);
	}

	/**
	 * @see javax.imageio.spi.IIOServiceProvider#getDescription(java.util.Locale)
	 */
	public String getDescription(Locale locale) {
		return new StringBuffer("MrSID Image Reader, version ").append(version)
				.toString();
	}


	protected String getSupportedFormats() {
		return "MrSID";
	}


	
}
