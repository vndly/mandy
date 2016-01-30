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
package jbox2d.dynamics.contacts;

public class EdgeAndCircleContact extends Contact {

  public EdgeAndCircleContact(jbox2d.pooling.IWorldPool argPool) {
    super(argPool);
  }

  @Override
  public void init(jbox2d.dynamics.Fixture fA, int indexA, jbox2d.dynamics.Fixture fB, int indexB) {
    super.init(fA, indexA, fB, indexB);
    assert (m_fixtureA.getType() == jbox2d.collision.shapes.ShapeType.EDGE);
    assert (m_fixtureB.getType() == jbox2d.collision.shapes.ShapeType.CIRCLE);
  }

  @Override
  public void evaluate(jbox2d.collision.Manifold manifold, jbox2d.common.Transform xfA, jbox2d.common.Transform xfB) {
    pool.getCollision().collideEdgeAndCircle(manifold, (jbox2d.collision.shapes.EdgeShape) m_fixtureA.getShape(), xfA,
        (jbox2d.collision.shapes.CircleShape) m_fixtureB.getShape(), xfB);
  }
}
