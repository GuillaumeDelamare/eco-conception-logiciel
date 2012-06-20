package xuggle.test.server;

import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStreamCoder;

public interface Server {
	public IStreamCoder getVideoCoder();
	public IStreamCoder getAudioCoder();
	public IContainer getContainer();
	public int getVideoStreamId();
	public int getAudioStreamId();
}
