package com.xiaogua.better.apache;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientCode {
	private static HttpClient client = null;
	private static final int defaultTimeout = 1000 * 5;
	public static RequestConfig defaultConfig = RequestConfig.custom().setSocketTimeout(defaultTimeout)
			.setConnectTimeout(defaultTimeout).setConnectionRequestTimeout(defaultTimeout).build();

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
	}

	public static String get(String url, String charset, Integer connTimeout, Integer readTimeout) throws Exception {
		HttpClient client = null;
		HttpGet get = new HttpGet(url);
		HttpResponse res = null;
		String result = "";
		try {
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			get.setConfig(customReqConf.build());
			client = HttpClientCode.client;
			res = client.execute(get);
			result = IOUtils.toString(res.getEntity().getContent(), charset);
		} finally {
			get.releaseConnection();
		}
		return result;
	}

	public static String doGet(String url) throws Exception {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(defaultConfig);
		CloseableHttpResponse httpResp = httpclient.execute(httpGet);
		try {
			int statusCode = httpResp.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
			}
		} finally {
			httpResp.close();
			httpclient.close();
		}
		return result;
	}
}
