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

import jbox2d.collision.shapes.EdgeShape;
import jbox2d.common.Transform;
import jbox2d.dynamics.Fixture;

import jbox2d.pooling.IWorldPool;

public class ChainAndCircleContact extends Contact
{

  public ChainAndCircleContact(IWorldPool argPool) {
    super(argPool);
  }

  @Override
  public void init(Fixture fA, int indexA, Fixture fB, int indexB) {
    super.init(fA, indexA, fB, indexB);
    assert (m_fixtureA.getType() == jbox2d.collision.shapes.ShapeType.CHAIN);
    assert (m_fixtureB.getType() == jbox2d.collision.shapes.ShapeType.CIRCLE);
  }

  private final EdgeShape edge = new EdgeShape();

  @Override
  public void evaluate(jbox2d.collision.Manifold manifold, Transform xfA, Transform xfB) {
    jbox2d.collision.shapes.ChainShape chain = (jbox2d.collision.shapes.ChainShape) m_fixtureA.getShape();
    chain.getChildEdge(edge, m_indexA);
    pool.getCollision().collideEdgeAndCircle(manifold, edge, xfA,
        (jbox2d.collision.shapes.CircleShape) m_fixtureB.getShape(), xfB);
  }
}
