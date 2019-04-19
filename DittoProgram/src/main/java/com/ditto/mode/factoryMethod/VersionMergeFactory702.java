package com.ditto.mode.factoryMethod;

public class VersionMergeFactory702 implements IMergerVersionfFactory{

	@Override
	public IVersion checkVersion() {
		
		return new Version702();
	}

}
