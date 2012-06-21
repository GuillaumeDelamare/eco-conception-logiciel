package org.oep.services.ecoserveur.impl2;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

public class XuggleServerImpl implements XuggleServer {
	private IStreamCoder audioCoder;
	private int audioStreamId;
	
	private IContainer container;
	
	private String videoPath;

	public void load() {
		container = IContainer.make();

		if (container.open(videoPath, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + videoPath);

		int numStreams = container.getNumStreams();

		audioStreamId = -1;
		audioCoder = null;
		for(int i = 0; i < numStreams; i++) {
			IStream stream = container.getStream(i);
			IStreamCoder coder = stream.getStreamCoder();

			if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
				audioStreamId = i;
				audioCoder = coder;
				break;
			}
		}
		if (audioStreamId == -1)
			throw new RuntimeException("could not find audio stream in container: "+videoPath);

	}
	
	@Override
	public IStreamCoder getVideoCoder() {
		return null;
	}
	@Override
	public IStreamCoder getAudioCoder() {
		return audioCoder;
	}
	@Override
	public IContainer getContainer() {
		return container;
	}
	@Override
	public int getAudioStreamId() {
		return audioStreamId;
	}
	@Override
	public int getVideoStreamId() {
		return -1;
	}

	@Override
	public void setVideo(String path) {
		videoPath = path;
		
		load();
	}
}
