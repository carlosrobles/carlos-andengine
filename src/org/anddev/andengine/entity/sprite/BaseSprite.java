package org.anddev.andengine.entity.sprite;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.primitive.BaseRectangle;
import org.anddev.andengine.opengl.texture.region.BaseTextureRegion;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

/**
 * @author Nicolas Gramlich
 * @since 11:38:53 - 08.03.2010
 */
public abstract class BaseSprite extends BaseRectangle {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final BaseTextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseSprite(final float pX, final float pY, final float pWidth, final float pHeight, final BaseTextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight);

		this.mTextureRegion = pTextureRegion;
		this.initBlendFunction();
	}

	public BaseSprite(final float pX, final float pY, final float pWidth, final float pHeight, final BaseTextureRegion pTextureRegion, final RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pWidth, pHeight, pRectangleVertexBuffer);

		this.mTextureRegion = pTextureRegion;
		this.initBlendFunction();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public BaseTextureRegion getTextureRegion() {
		return this.mTextureRegion;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		super.reset();

		this.initBlendFunction();
	}

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.enableTextures(pGL);
		GLHelper.enableTexCoordArray(pGL);
	}

	@Override
	protected void onApplyTransformations(final GL10 pGL) {
		super.onApplyTransformations(pGL);

		this.mTextureRegion.onApply(pGL);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void initBlendFunction() {
		if(this.mTextureRegion.getTexture().getTextureOptions().mPreMultipyAlpha) {
			this.setBlendFunction(BLENDFUNCTION_SOURCE_PREMULTIPLYALPHA_DEFAULT, BLENDFUNCTION_DESTINATION_PREMULTIPLYALPHA_DEFAULT);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
