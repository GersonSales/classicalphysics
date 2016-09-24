package javafx.physics;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Texture;

public class NegativePlate implements PlateCharge {

	private AssetManager assetManager;

	public NegativePlate() {
		this.assetManager = new AssetManager();
	}

	@Override
	public Texture getTexture() {
		try {
			return assetManager.loadTexture("inferiorPlate.png");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Texture getField() {
		try {
			return assetManager.loadTexture("negativeArrow.png");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
