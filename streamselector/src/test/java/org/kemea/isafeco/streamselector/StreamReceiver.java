package org.kemea.isafeco.streamselector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.kemea.isafeco.streamselector.mock.input.SessionDestinationStreamInput;
import org.kemea.isafeco.streamselector.mock.output.SessionDestinationStreamOutput;

public class StreamReceiver {

	private final String FFPLAY_CMD = "cmd /c start cmd.exe /k ffplay -protocol_whitelist \"file,crypto,data,udp,rtp\" %s";
	final String STREAM_SELECTOR_URL = "http://localhost:8080/streamselector/rest/session/%s";
	final Integer SESSION_ID_TOPLAY = 889;

	@Test
	public void testReceive() throws IOException, Exception {
		SessionDestinationStreamOutput sessionDestinationStreamOutput = postSessionsDestinationStream();
		String sdp = sessionDestinationStreamOutput.getSessionSdp();
		String sdpFileName = String.format("video%s.sdp", Math.round(Math.random() * 1000));
		String workDir = StreamReceiver.class.getResource(".").getPath();
		String sdpPath = new File(String.format("%s%s", workDir, sdpFileName)).getAbsolutePath();
		Files.write(Path.of(sdpPath), sdp.getBytes());
		System.out.println(String.format("cmd: %s", String.format(FFPLAY_CMD, sdpFileName)));
		Util.runCmd(String.format(FFPLAY_CMD, sdpFileName), StreamTransmitter.class.getResource(".").getPath());
	}

	private SessionDestinationStreamOutput postSessionsDestinationStream() throws Exception {
		SessionDestinationStreamInput sessionDestinationStreamInput = new SessionDestinationStreamInput();
		sessionDestinationStreamInput.setSessionId(SESSION_ID_TOPLAY);
		byte[] payload = Util.post(String.format(STREAM_SELECTOR_URL, "session-destination-streams"),
				Util.toJson(sessionDestinationStreamInput),
				Collections.singletonMap("Content-Type", "application/json"), "UTF-8", 10000, 10000);
		return Util.fromJson(new String(payload), SessionDestinationStreamOutput.class);
	}

}
