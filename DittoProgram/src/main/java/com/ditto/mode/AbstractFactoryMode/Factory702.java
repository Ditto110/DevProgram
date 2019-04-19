package com.ditto.mode.AbstractFactoryMode;

public class Factory702 implements SuperFactory{

	@Override
	public IVersion checkVersion() {

		return new Version702();
	}

	@Override
	public ISource checkSource() {

		return new Source702();
	}

}
