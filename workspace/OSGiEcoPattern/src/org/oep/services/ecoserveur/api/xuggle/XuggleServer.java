package org.oep.services.ecoserveur.api.xuggle;

import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStreamCoder;

public interface XuggleServer {
	public IStreamCoder getVideoCoder();
	public IStreamCoder getAudioCoder();
	public IContainer getContainer();
	public int getVideoStreamId();
	public int getAudioStreamId();
	public void setVideo(String path);
}
