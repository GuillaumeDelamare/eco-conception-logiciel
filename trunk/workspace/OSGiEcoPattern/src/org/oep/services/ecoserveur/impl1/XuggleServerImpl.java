package org.oep.services.ecoserveur.impl1;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoResampler;

public class XuggleServerImpl implements XuggleServer{
	private IStreamCoder videoCoder;
	private IStreamCoder audioCoder;
	private int videoStreamId;
	private int audioStreamId;
	
	private IContainer container;
	
	private String videoPath;
	
	
	public void load() {
		if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
			throw new RuntimeException("you must install the GPL version of Xuggler (with IVideoResampler support) for this demo to work");

		container = IContainer.make();

		if (container.open(videoPath, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + videoPath);

		int numStreams = container.getNumStreams();

		videoStreamId = -1;
		videoCoder = null;
		audioStreamId = -1;
		audioCoder = null;
		
		for(int i = 0; i < numStreams; i++) {
			IStream stream = container.getStream(i);
			IStreamCoder coder = stream.getStreamCoder();

			if (videoStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
				videoStreamId = i;
				videoCoder = coder;
			}
			else if (audioStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
				audioStreamId = i;
				audioCoder = coder;
			}
		}
		if (videoStreamId == -1 && audioStreamId == -1)
			throw new RuntimeException("could not find audio or video stream in container: "+videoPath);
	}
	
	@Override
	public IStreamCoder getVideoCoder() {
		return videoCoder;
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
		return videoStreamId;
	}
	@Override
	public void setVideo(String path) {
		videoPath = path;
		
		load();
	}
}
