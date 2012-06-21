package org.oep.services.ecoserveur.api.xuggle;

import org.oep.services.ecoclient.api.xuggle.XuggleView;


public interface XuggleServer {
	public void setmScreen(XuggleView mScreen);
	public void setVideo(String path);
	public String getVideo();
	public void play();
	public void stop();
	public boolean isStarted();
	
}
