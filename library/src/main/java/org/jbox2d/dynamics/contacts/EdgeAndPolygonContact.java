/*******************************************************************************
 * Copyright (c) 2013, Daniel Murphy
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 	* Redistributions of source code must retain the above copyright notice,
 * 	  this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright notice,
 * 	  this list of conditions and the following disclaimer in the documentation
 * 	  and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package org.jbox2d.dynamics.contacts;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.dynamics.Fixture;

public class EdgeAndPolygonContact extends Contact {

  public EdgeAndPolygonContact(org.jbox2d.pooling.IWorldPool argPool) {
    super(argPool);
  }

  @Override
  public void init(Fixture fA, int indexA, Fixture fB, int indexB) {
    super.init(fA, indexA, fB, indexB);
    assert (m_fixtureA.getType() == org.jbox2d.collision.shapes.ShapeType.EDGE);
    assert (m_fixtureB.getType() == org.jbox2d.collision.shapes.ShapeType.POLYGON);
  }

  @Override
  public void evaluate(org.jbox2d.collision.Manifold manifold, org.jbox2d.common.Transform xfA, org.jbox2d.common.Transform xfB) {
    pool.getCollision().collideEdgeAndPolygon(manifold, (EdgeShape) m_fixtureA.getShape(), xfA,
        (org.jbox2d.collision.shapes.PolygonShape) m_fixtureB.getShape(), xfB);
  }
}