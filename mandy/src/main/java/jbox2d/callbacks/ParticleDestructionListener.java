package jbox2d.callbacks;

public interface ParticleDestructionListener {
  /**
   * Called when any particle group is about to be destroyed.
   */
  void sayGoodbye(jbox2d.particle.ParticleGroup group);

  /**
   * Called when a particle is about to be destroyed. The index can be used in conjunction with
   * {@link jbox2d.dynamics.World#getParticleUserDataBuffer} to determine which particle has been destroyed.
   * 
   * @param index
   */
  void sayGoodbye(int index);
}
