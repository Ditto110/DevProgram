package com.ditto.mode.factoryMethod;

public class VersionMergeFactory604 implements IMergerVersionfFactory{

	@Override
	public IVersion checkVersion() {
		
		return new Version604();
	}

}
