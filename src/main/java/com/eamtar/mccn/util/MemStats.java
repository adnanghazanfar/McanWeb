package com.eamtar.mccn.util;

/**
 * Java VM memory footprint
 * 
 * @see StatI
 * @see StatRunner
 * @author Adnan Ghazanfar
 */
public class MemStats {
	private Runtime rt;
	private long totalMemory, freeMemory, maxMemory;

	/**
	 * *
	 * 
	 * @param freq
	 *            The sample frequency in seconds
	 */
	public MemStats() {
		rt = Runtime.getRuntime();
		totalMemory = rt.totalMemory();
		freeMemory = rt.freeMemory();
		maxMemory = rt.maxMemory();
	}

	/**
	 * used by StatRunner to take samples
	 * 
	 */
	public void sample() {
		rt = Runtime.getRuntime();
		totalMemory = rt.totalMemory();
		freeMemory = rt.freeMemory();
		maxMemory = rt.maxMemory();
	}


	@Override
	public String toString() {
		return "MemStats [maxMemory=" + maxMemory + ", tmemstart=" + totalMemory
				+ ", fmemstart=" + freeMemory + "]";
	}

}
