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
/**
 * Created at 3:26:14 AM Jan 11, 2011
 */
package org.jbox2d.pooling.normal;

import java.util.HashMap;

import org.jbox2d.common.Settings;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.EdgeAndCircleContact;
import org.jbox2d.dynamics.contacts.EdgeAndPolygonContact;
import org.jbox2d.pooling.IDynamicStack;

/**
 * Provides object pooling for all objects used in the engine. Objects retrieved from here should
 * only be used temporarily, and then pushed back (with the exception of arrays).
 * 
 * @author Daniel Murphy
 */
public class DefaultWorldPool implements org.jbox2d.pooling.IWorldPool
{

  private final OrderedStack<Vec2> vecs;
  private final OrderedStack<org.jbox2d.common.Vec3> vec3s;
  private final OrderedStack<org.jbox2d.common.Mat22> mats;
  private final OrderedStack<org.jbox2d.common.Mat33> mat33s;
  private final OrderedStack<org.jbox2d.collision.AABB> aabbs;
  private final OrderedStack<org.jbox2d.common.Rot> rots;

  private final HashMap<Integer, float[]> afloats = new HashMap<Integer, float[]>();
  private final HashMap<Integer, int[]> aints = new HashMap<Integer, int[]>();
  private final HashMap<Integer, Vec2[]> avecs = new HashMap<Integer, Vec2[]>();

  private final org.jbox2d.pooling.IWorldPool world = this;

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> pcstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new org.jbox2d.dynamics.contacts.PolygonContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new org.jbox2d.dynamics.contacts.PolygonContact[size]; }
  };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> ccstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new org.jbox2d.dynamics.contacts.CircleContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new org.jbox2d.dynamics.contacts.CircleContact[size]; }
    };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> cpstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new org.jbox2d.dynamics.contacts.PolygonAndCircleContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new org.jbox2d.dynamics.contacts.PolygonAndCircleContact[size]; }
    };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> ecstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new EdgeAndCircleContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new EdgeAndCircleContact[size]; }
    };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> epstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new EdgeAndPolygonContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new EdgeAndPolygonContact[size]; }
    };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> chcstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new org.jbox2d.dynamics.contacts.ChainAndCircleContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new org.jbox2d.dynamics.contacts.ChainAndCircleContact[size]; }
    };

  private final MutableStack<org.jbox2d.dynamics.contacts.Contact> chpstack =
    new MutableStack<org.jbox2d.dynamics.contacts.Contact>(Settings.CONTACT_STACK_INIT_SIZE) {
      protected org.jbox2d.dynamics.contacts.Contact newInstance () { return new org.jbox2d.dynamics.contacts.ChainAndPolygonContact(world); }
      protected org.jbox2d.dynamics.contacts.Contact[] newArray(int size) { return new org.jbox2d.dynamics.contacts.ChainAndPolygonContact[size]; }
    };

  private final org.jbox2d.collision.Collision collision;
  private final org.jbox2d.collision.TimeOfImpact toi;
  private final org.jbox2d.collision.Distance dist;

  public DefaultWorldPool(int argSize, int argContainerSize) {
    vecs = new OrderedStack<Vec2>(argSize, argContainerSize) {
      protected Vec2 newInstance() { return new Vec2(); }
    };
    vec3s = new OrderedStack<org.jbox2d.common.Vec3>(argSize, argContainerSize) {
      protected org.jbox2d.common.Vec3 newInstance() { return new org.jbox2d.common.Vec3(); }
    };
    mats = new OrderedStack<org.jbox2d.common.Mat22>(argSize, argContainerSize) {
      protected org.jbox2d.common.Mat22 newInstance() { return new org.jbox2d.common.Mat22(); }
    };
    aabbs = new OrderedStack<org.jbox2d.collision.AABB>(argSize, argContainerSize) {
      protected org.jbox2d.collision.AABB newInstance() { return new org.jbox2d.collision.AABB(); }
    };
    rots = new OrderedStack<org.jbox2d.common.Rot>(argSize, argContainerSize) {
      protected org.jbox2d.common.Rot newInstance() { return new org.jbox2d.common.Rot(); }
    };
    mat33s = new OrderedStack<org.jbox2d.common.Mat33>(argSize, argContainerSize) {
      protected org.jbox2d.common.Mat33 newInstance() { return new org.jbox2d.common.Mat33(); }
    };

    dist = new org.jbox2d.collision.Distance();
    collision = new org.jbox2d.collision.Collision(this);
    toi = new org.jbox2d.collision.TimeOfImpact(this);
  }

  public final IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getPolyContactStack() {
    return pcstack;
  }

  public final IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getCircleContactStack() {
    return ccstack;
  }

  public final IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getPolyCircleContactStack() {
    return cpstack;
  }

  @Override
  public IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getEdgeCircleContactStack() {
    return ecstack;
  }

  @Override
  public IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getEdgePolyContactStack() {
    return epstack;
  }

  @Override
  public IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getChainCircleContactStack() {
    return chcstack;
  }

  @Override
  public IDynamicStack<org.jbox2d.dynamics.contacts.Contact> getChainPolyContactStack() {
    return chpstack;
  }

  public final Vec2 popVec2() {
    return vecs.pop();
  }

  public final Vec2[] popVec2(int argNum) {
    return vecs.pop(argNum);
  }

  public final void pushVec2(int argNum) {
    vecs.push(argNum);
  }

  public final org.jbox2d.common.Vec3 popVec3() {
    return vec3s.pop();
  }

  public final org.jbox2d.common.Vec3[] popVec3(int argNum) {
    return vec3s.pop(argNum);
  }

  public final void pushVec3(int argNum) {
    vec3s.push(argNum);
  }

  public final org.jbox2d.common.Mat22 popMat22() {
    return mats.pop();
  }

  public final org.jbox2d.common.Mat22[] popMat22(int argNum) {
    return mats.pop(argNum);
  }

  public final void pushMat22(int argNum) {
    mats.push(argNum);
  }

  public final org.jbox2d.common.Mat33 popMat33() {
    return mat33s.pop();
  }

  public final void pushMat33(int argNum) {
    mat33s.push(argNum);
  }

  public final org.jbox2d.collision.AABB popAABB() {
    return aabbs.pop();
  }

  public final org.jbox2d.collision.AABB[] popAABB(int argNum) {
    return aabbs.pop(argNum);
  }

  public final void pushAABB(int argNum) {
    aabbs.push(argNum);
  }

  public final org.jbox2d.common.Rot popRot() {
    return rots.pop();
  }

  public final void pushRot(int num) {
    rots.push(num);
  }

  public final org.jbox2d.collision.Collision getCollision() {
    return collision;
  }

  public final org.jbox2d.collision.TimeOfImpact getTimeOfImpact() {
    return toi;
  }

  public final org.jbox2d.collision.Distance getDistance() {
    return dist;
  }

  public final float[] getFloatArray(int argLength) {
    if (!afloats.containsKey(argLength)) {
      afloats.put(argLength, new float[argLength]);
    }

    assert (afloats.get(argLength).length == argLength) : "Array not built with correct length";
    return afloats.get(argLength);
  }

  public final int[] getIntArray(int argLength) {
    if (!aints.containsKey(argLength)) {
      aints.put(argLength, new int[argLength]);
    }

    assert (aints.get(argLength).length == argLength) : "Array not built with correct length";
    return aints.get(argLength);
  }

  public final Vec2[] getVec2Array(int argLength) {
    if (!avecs.containsKey(argLength)) {
      Vec2[] ray = new Vec2[argLength];
      for (int i = 0; i < argLength; i++) {
        ray[i] = new Vec2();
      }
      avecs.put(argLength, ray);
    }

    assert (avecs.get(argLength).length == argLength) : "Array not built with correct length";
    return avecs.get(argLength);
  }
}
