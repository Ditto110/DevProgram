package com.ditto.mode.factoryMethod;

public class VersionMergeFactory705 implements IMergerVersionfFactory{

	@Override
	public IVersion checkVersion() {
		
		return new Version705();
	}

}
