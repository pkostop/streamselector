package org.kemea.isafeco.streamselector;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.kemea.isafeco.streamselector.mock.input.SessionSourceStreamInput;
import org.kemea.isafeco.streamselector.mock.output.SessionSourceStreamOutput;

public class StreamTransmitter {
	private final String FFMPEG_CMD = "cmd /c start cmd.exe /k ffmpeg -re -i test.mp4 -c:v libx264 -x264-params \"keyint=60:min-keyint=60:scenecut=0\" -f rtp rtp://#ipaddress#:#port# -sdp_file video.sdp";
	final String STREAM_SELECTOR_URL = "http://localhost:8080/streamselector/rest/session/%s";

	@Test
	public void testTransmit() throws Exception {
		init();

		SessionSourceStreamOutput sessionSourceStreamOutput = startStreamSelectorSession();

		String ffmpegCmd = FFMPEG_CMD.replace("#ipaddress#", sessionSourceStreamOutput.getSessionSourceServiceIp())
				.replace("#port#", String.valueOf(sessionSourceStreamOutput.getSessionSourceServicePort()));
		System.out.println(ffmpegCmd);
		Util.runCmd(ffmpegCmd, StreamTransmitter.class.getResource(".").getPath());
	}

	private void init() throws UnknownHostException, IOException, InterruptedException {
		if (!new File(StreamTransmitter.class.getResource("video.sdp").getPath()).exists()) {
			Long port = Math.round(Math.random() * 10000);
			String ffmpegCmd = FFMPEG_CMD.replace("#ipaddress#", InetAddress.getLocalHost().getHostAddress())
					.replace("#port#", String.valueOf(port));
			Util.runCmd(ffmpegCmd, StreamTransmitter.class.getResource(".").getPath());
		}
	}

	private SessionSourceStreamOutput startStreamSelectorSession() throws IOException, Exception {
		String sdp = new String(Files.readAllBytes(
				Path.of(new File(StreamTransmitter.class.getResource("video.sdp").getPath()).getAbsolutePath())));
		SessionSourceStreamInput sessionSourceStreamInput = new SessionSourceStreamInput();
		sessionSourceStreamInput.setApplicationId(1L);
		sessionSourceStreamInput.setSessionSdp(sdp);
		String json = Util.toJson(sessionSourceStreamInput);
		byte[] payload = Util.post(String.format(STREAM_SELECTOR_URL, "session-source-streams"), json,
				Collections.singletonMap("Content-Type", "application/json"), "UTF-8", 0, 0);

		SessionSourceStreamOutput sessionSourceStreamOutput = Util.fromJson(new String(payload),
				SessionSourceStreamOutput.class);
		return sessionSourceStreamOutput;
	}

}
