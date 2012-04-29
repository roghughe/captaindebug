package com.captaindebug.statemachine.unpackxml;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.statemachine.StateMachine;

public class XMLParserTest {

	public static final String INPUT = "../state-machine/src/test/resources/compressed.xml";
	public static final String OUTDIR = "../state-machine/target/out";
	public static final String OUTPUT = OUTDIR + "/uncompressed.xml";

	@Before
	public void setUp() {

		File file = new File(OUTPUT);
		file.delete();

		file = new File(OUTDIR);
		file.mkdir();
	}

	/**
	 * Method - used to demonstrate the state machine. In this demo, the big
	 * idea is to read from an XML file that contains a bunch of compressed data
	 * somewhere in it's middle. The compressed data is surrounded by the
	 * <CompressedPart> tag - so we need to find that tag and weed out the
	 * compressed data. All the data is sent to the output file.
	 * 
	 * This demo reads from the compressed.XML file and writes to the
	 * uncompressed.xml file
	 */
	@Test
	public void demoXMLStateMachine() throws IOException {

		FileInputStream fis = null;
		OutputStream bos = null;

		try {
			fis = new FileInputStream(INPUT);
			bos = new FileOutputStream(OUTPUT);

			StateMachine<XMLState> machine = new StateMachine<XMLState>(XMLState.DEFAULT);

			// Add some actions to the statemachine

			// Add the default action
			machine.addAction(XMLState.DEFAULT, new DefaultAction(bos));

			// Add the uncompressor
			Uncompress uncompress = new Uncompress(bos);

			// Add the action to weed out the start tag.
			// The next action to call - Chain of Responsibility
			CaptureStartTag startTag = new CaptureStartTag(bos);

			machine.addAction(XMLState.CAPTURE_START_TAG, startTag);
			machine.addAction(XMLState.UNCOMPRESS, uncompress);
			machine.addAction(XMLState.DEFAULT, new DefaultAction(bos));

			// process the input stream
			machine.processStream(fis);

		} finally {
			if (fis != null)
				fis.close();

			if (bos != null)
				bos.close();
		}

		// This is NOT a good test - the code should really parse the XML, and
		// verify that the field has been unpacked correctly
		// Don't do this is REAL code.
		File file = new File(OUTPUT);
		assertTrue(file.exists());
	}
}
