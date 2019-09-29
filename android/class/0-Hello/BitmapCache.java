package com.kyle.hello.common.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class BitmapCache {
	static private BitmapCache cache = new BitmapCache();

	private Hashtable<String, BtimapRef> bitmapRefs;

	private ReferenceQueue<Bitmap> q;

	private class BtimapRef extends SoftReference<Bitmap> {
		private String _key = null;

		public BtimapRef(String key, Bitmap bmp, ReferenceQueue<Bitmap> q) {
			super(bmp, q);
			_key = key;
		}
	}

	private BitmapCache() {
		bitmapRefs = new Hashtable<String, BtimapRef>();
		q = new ReferenceQueue<Bitmap>();

	}

	public static BitmapCache getInstance() {
		return cache;
	}

	private void addCacheBitmap(String key, Bitmap bmp) {
		this.clearUselessCache();
		BtimapRef ref = new BtimapRef(key, bmp, q);
		bitmapRefs.put(key, ref);
	}

	public Bitmap getBitmap(Context context, int resId, int reqWidth, int reqHeight) {
		Bitmap bmp = null;
		String key = resId+"_"+reqWidth+"_"+reqHeight;
		if (bitmapRefs.containsKey(key)) {
			BtimapRef ref = (BtimapRef) bitmapRefs.get(key);
			bmp = (Bitmap) ref.get();
			if(bmp != null){
				Log.d("got it from cache!", key);
			}
		}
		if (bmp == null) {
			// coded by kyle, it's always good to scale the image to size you
			// want, instead of getting the original one
			bmp = BitmapUtil.decodeSampledBitmapFromResource(context.getResources(), resId, reqWidth, reqHeight);
			this.addCacheBitmap(key, bmp);
		}
		return bmp;
	}

	/*
		if you want to mark a bitmap as ready to be recycled, do below things
		if (bitmap != null && b!itmap.isRecycled()){
		   bitmap.recycle();
		   bitmap = null;
		}
	*/
	private void clearUselessCache() {
		BtimapRef ref = null;
		// this means the bitmap is already marked as ready to be recycled
		while ((ref = (BtimapRef) q.poll()) != null) {
			bitmapRefs.remove(ref._key);
		}
	}

	public void clearAllCache() {
		bitmapRefs.clear();
		System.gc();
		System.runFinalization();
	}

}
