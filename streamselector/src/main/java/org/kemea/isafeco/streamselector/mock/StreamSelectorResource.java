package org.kemea.isafeco.streamselector.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kemea.isafeco.streamselector.mock.input.SessionDestinationStreamInput;
import org.kemea.isafeco.streamselector.mock.input.SessionSourceStreamInput;
import org.kemea.isafeco.streamselector.mock.output.GetSessionsOutput;
import org.kemea.isafeco.streamselector.mock.output.SessionDestinationStreamOutput;
import org.kemea.isafeco.streamselector.mock.output.SessionInfo;
import org.kemea.isafeco.streamselector.mock.output.SessionSourceStreamOutput;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/session")
public class StreamSelectorResource {
	private static Map<Integer, SessionSourceStreamOutput> sessions = new HashMap<Integer, SessionSourceStreamOutput>();
	private static int PORT = 5000;

	@Path("/session-destination-streams")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postSessionsSessionDestinationStreams(SessionDestinationStreamInput input) {
		SessionSourceStreamOutput _session = sessions.get(input.getSessionId());
		SessionDestinationStreamOutput output = new SessionDestinationStreamOutput();
		output.setSessionDestinationServiceIp(_session.getSessionSourceServiceIp());
		output.setSessionDestinationServicePort(_session.getSessionSourceServicePort());
		output.setSessionDestinationServiceProtocol("UDP");
		output.setSessionSdp(_session.getSessionSDP());
		return Response.ok(output).build();
	}

	@Path("/session-source-streams")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postSessionsSessionSourceStreams(SessionSourceStreamInput input) {
		SessionSourceStreamOutput sessionSourceStreamOutput = new SessionSourceStreamOutput();
		Long sessionId = Math.round(Math.random() * 1000);
		sessions.put(sessionId.intValue(), sessionSourceStreamOutput);
		sessionSourceStreamOutput.setSessionId(sessionId.intValue());
		sessionSourceStreamOutput.setSessionSourceServiceIp(stripConnectionIpFromSDPFile(input.getSessionSdp()));
		sessionSourceStreamOutput
				.setSessionSourceServicePort(Integer.parseInt(stripConnectionPortFromSDPFile(input.getSessionSdp())));
		sessionSourceStreamOutput.setSessionSourceServiceProtocol("UDP");
		sessionSourceStreamOutput.setSessionSDP(input.getSessionSdp());
		return Response.ok(sessionSourceStreamOutput).build();
	}

	@Path("/sessions")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSessions(@QueryParam(value = "limit") String limit, @QueryParam(value = "offset") String offset,
			@QueryParam(value = "sort") String sort, @QueryParam(value = "cluster_id") String cluster_id,
			@QueryParam(value = "session_id") String session_id, @QueryParam(value = "contract_id") String contract_id,
			@QueryParam(value = "status") String status

	) {
		List<GetSessionsOutput> getSessionsOutputs = new ArrayList<GetSessionsOutput>();
		for (SessionSourceStreamOutput sessionSourceStreamOutput : sessions.values()) {
			GetSessionsOutput getSessionsOutput = new GetSessionsOutput();
			getSessionsOutputs.add(getSessionsOutput);
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setId(sessionSourceStreamOutput.getSessionId());
			sessionInfo.setSdp(sessionSourceStreamOutput.getSessionSDP());
			getSessionsOutput.setSessionInfo(sessionInfo);
		}
		return Response.ok(getSessionsOutputs.toArray(new GetSessionsOutput[] {})).build();
	}

	private String stripConnectionIpFromSDPFile(String sessionSdp) {
		String[] rows = sessionSdp.split("\n");
		for (String row : rows) {
			if (row.startsWith("c")) {
				String[] tokens = row.split(" ");
				String ip = tokens[tokens.length - 1];
				ip = ip.replace(" ", "").replace("\n", "").replace("\r", "");
				return ip;
			}
		}
		return null;
	}

	private String stripConnectionPortFromSDPFile(String sessionSdp) {
		String[] rows = sessionSdp.split("\n");
		for (String row : rows) {
			if (row.startsWith("m")) {
				String[] tokens = row.split(" ");
				String ip = tokens[1];
				ip = ip.replace(" ", "").replace("\n", "").replace("\r", "");
				return ip;
			}
		}
		return null;
	}
}