package other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ParticleEffect {
	
	private ParticleSystem					pSys;
	private ArrayList<ConfigurableEmitter>	emitters;	
	private boolean							isEnabled;
	File									xmlFile;
	
	public ParticleEffect(String type) {
		try {
			//load the test particle and 
			Image image = new Image("res/effects/" + type + "_particle.png", false);
			pSys = new ParticleSystem(image, 1500);
			xmlFile = new File("res/effects/" + type + "_emitter.xml");
			emitters = new ArrayList<ConfigurableEmitter>();
			this.emitters.add(ParticleIO.loadEmitter(xmlFile));
			this.emitters.get(this.emitters.size() - 1).setPosition(1000,1000);
			pSys.addEmitter(this.emitters.get(this.emitters.size() - 1));
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		pSys.setBlendingMode(ParticleSystem.BLEND_COMBINE);
	}
	
	public void doEffect(int x, int y) {
		try {
			this.emitters.add(ParticleIO.loadEmitter(xmlFile));
			this.emitters.get(this.emitters.size() - 1).setPosition(x, y);
			//this.emitters.get(this.emitters.size() - 1).;
			pSys.addEmitter(this.emitters.get(this.emitters.size() - 1));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pSys.getEmitter(0).resetState();
	}
	
	public void render() {
		pSys.render();
	}
	
	public void update(int delta) {
		pSys.update(delta);
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean isDisabled() {
		return !isEnabled;
	}
	
	public void enable() {
		this.isEnabled = true;
	}
	
	public void disable() {
		this.isEnabled = false;
	}
}
