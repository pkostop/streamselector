package org.kemea.isafeco.streamselector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.GsonBuilder;

public class Util {
	public static <T> String toJson(T entity) {
		return new GsonBuilder().create().toJson(entity);
	}

	public static <T> T fromJson(String json, Class<T> _class) {
		return new GsonBuilder().create().fromJson(json, _class);
	}

	public static byte[] post(String _url, String message, Map<String, String> headers, String charset, int readTimeout,
			int connectTimeout) throws Exception {
		URL url = new URL(_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("charset", charset);
		conn.setRequestProperty("Content-Length", Integer.toString(message.length()));
		for (Iterator<String> iterator = headers.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			conn.setRequestProperty(key, (String) headers.get(key));
		}
		conn.setUseCaches(false);
		conn.getOutputStream().write(message.getBytes("UTF-8"));
		conn.connect();
		byte[] buffer = parseInputStream(conn.getInputStream());
		return buffer;
	}

	public static byte[] get(String url, int connectTimeout, int readTimeout) throws Exception {
		URL _url = new URL(url);
		URLConnection urlConnection = _url.openConnection();
		urlConnection.setConnectTimeout(connectTimeout);
		urlConnection.setReadTimeout(readTimeout);
		Object _obj = urlConnection.getContent();
		byte[] buffer = parseInputStream((InputStream) _obj);
		return buffer;
	}

	public static byte[] parseInputStream(InputStream is) throws Exception {
		if (is == null)
			return null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;

		try {
			bis = new BufferedInputStream(is);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[16384];
			int bytesRead = 0;
			while ((bytesRead = bis.read(buffer)) > -1)
				baos.write(buffer, 0, bytesRead);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (baos != null)
				try {
					baos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			bis.close();
		}
	}

	public static void runCmd(String cmd, String dir) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
		pb.directory(new File(dir));
		Process p = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		// Wait for the process to finish
		int exitCode = p.waitFor();
		System.out.println(exitCode);

	}

}
