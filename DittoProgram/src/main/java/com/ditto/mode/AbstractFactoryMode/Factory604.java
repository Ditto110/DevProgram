package com.ditto.mode.AbstractFactoryMode;

public class Factory604 implements SuperFactory{

	@Override
	public IVersion checkVersion() {

		return new Version604();
	}

	@Override
	public ISource checkSource() {

		return new Source604();
	}

}
