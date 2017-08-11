package org.osgl.util;

import org.osgl.$;
import org.osgl.Lang;

class MapPropertyHandler extends PropertyHandlerBase {

    protected final Class<?> keyType;
    protected final Class<?> valType;

    public MapPropertyHandler(Class<?> keyType, Class<?> valType) {
        this.keyType = $.assertNotNull(keyType);
        this.valType = $.assertNotNull(valType);
    }

    public MapPropertyHandler(PropertyGetter.NullValuePolicy nullValuePolicy,
                              Class<?> keyType,
                              Class<?> valType) {
        super(nullValuePolicy);
        this.keyType = $.assertNotNull(keyType);
        this.valType = $.assertNotNull(valType);
    }

    public MapPropertyHandler(Lang.Function<Class<?>, Object> objectFactory,
                              Lang.Func2<String, Class<?>, ?> stringValueResolver,
                              Class<?> keyType,
                              Class<?> valType) {
        super(objectFactory, stringValueResolver);
        this.keyType = $.assertNotNull(keyType);
        this.valType = $.assertNotNull(valType);
    }

    public MapPropertyHandler(Lang.Function<Class<?>, Object> objectFactory,
                              Lang.Func2<String, Class<?>, ?> stringValueResolver,
                              PropertyGetter.NullValuePolicy nullValuePolicy,
                              Class<?> keyType,
                              Class<?> valType) {
        super(objectFactory, stringValueResolver, nullValuePolicy);
        this.keyType = $.assertNotNull(keyType);
        this.valType = $.assertNotNull(valType);
    }

    protected Object keyFrom(Object index) {
        if (keyType.isAssignableFrom(index.getClass())) {
            return index;
        } else {
            return stringValueResolver.apply(S.string(index), keyType);
        }
    }
}
