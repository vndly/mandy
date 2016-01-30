package org.jbox2d.callbacks;

import org.jbox2d.collision.AABB;

/**
 * Callback class for AABB queries. See
 * {@link org.jbox2d.dynamics.World#queryAABB(QueryCallback, AABB)}.
 * 
 * @author dmurph
 * 
 */
public interface ParticleQueryCallback {
  /**
   * Called for each particle found in the query AABB.
   * 
   * @return false to terminate the query.
   */
  boolean reportParticle(int index);
}
