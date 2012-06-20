/*******************************************************************************
 * Copyright (c) 2008, 2010 Xuggle Inc.  All rights reserved.
 *  
 * This file is part of Xuggle-Xuggler-Main.
 *
 * Xuggle-Xuggler-Main is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Xuggle-Xuggler-Main is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Xuggle-Xuggler-Main.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package xuggle.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import xuggle.test.client.Client;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;

/**
 * Using {@link IMediaReader}, takes a media container, finds the first video stream,
 * decodes that stream, and then plays the audio and video.
 *
 * @author aclarke
 * @author trebor
 */

public class DecodeAndPlayAudioAndVideo extends MediaListenerAdapter implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		new Client().foo();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}