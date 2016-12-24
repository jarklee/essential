/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.ref;

public class ObjectRef<T> {

    private T obj;

    public ObjectRef() {

    }

    public ObjectRef(T obj) {
        this.obj = obj;
    }

    public T getValue() {
        return obj;
    }

    public boolean hasValue() {
        return obj != null;
    }
}
