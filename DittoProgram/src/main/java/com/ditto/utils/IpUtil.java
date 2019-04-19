package com.ditto.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpUtil {
//	private static String ipSelf = null;
	
	public static String getIpSuffix() {
		String nodeDefaultName = null;
		try {
			nodeDefaultName = getIpSuffix2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (nodeDefaultName == null) {
			nodeDefaultName = "node1";
		}
		return nodeDefaultName;
	}
	
	// 获取本机ip最后一位(双网卡内网ip)
	public static String getIpSuffix2() {
		String ip = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress()
									.toString();
							if (!ipaddress.contains("::")
									&& !ipaddress.contains("0:0:")
									&& !ipaddress.contains("fe80")) {
								ip = ipaddress;
							}
						}
					}
				}
			}
		} catch (SocketException ex) {
			System.out.println("获取ip地址异常");
			ip = "127.0.0.1";
			ex.printStackTrace();
		}
		return ip;
	}
	
	public static void main(String[] args) {
		System.out.println(getIpSuffix());
	}
}
