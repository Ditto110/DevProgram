package com.ditto.mode.AbstractFactoryMode;

public class Factory705 implements SuperFactory{

	@Override
	public IVersion checkVersion() {

		return new Version705();
	}

	@Override
	public ISource checkSource() {

		return new Source705();
	}

}
