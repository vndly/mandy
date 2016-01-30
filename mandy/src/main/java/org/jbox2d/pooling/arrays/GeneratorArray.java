package org.jbox2d.pooling.arrays;

import java.util.HashMap;

import org.jbox2d.particle.VoronoiDiagram.Generator;

public class GeneratorArray {

  private final HashMap<Integer, Generator[]> map =
      new HashMap<Integer, Generator[]>();

  public Generator[] get(int length) {
    assert (length > 0);

    if (!map.containsKey(length)) {
      map.put(length, getInitializedArray(length));
    }

    assert (map.get(length).length == length) : "Array not built of correct length";
    return map.get(length);
  }

  protected Generator[] getInitializedArray(int length) {
    final Generator[] ray = new Generator[length];
    for (int i = 0; i < ray.length; i++) {
      ray[i] = new Generator();
    }
    return ray;
  }
}
