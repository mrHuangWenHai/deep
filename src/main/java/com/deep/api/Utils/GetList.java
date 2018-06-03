package com.deep.api.Utils;

import java.util.ArrayList;
import java.util.List;

public class GetList<Key extends Comparable<Key> > {

    public List<Key> getRangeList(List<Key> model, int start, Byte size) {
        List<Key> result = new ArrayList<>();
        if (model != null) {
            for (int i = start; i < model.size() && i < start + size; i++) {
                result.add(model.get(i));
            }
            return result;
        }
        return null;
    }
}
