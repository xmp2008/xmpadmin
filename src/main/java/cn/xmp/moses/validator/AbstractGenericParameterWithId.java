package cn.xmp.moses.validator;


import cn.xmp.moses.validator.annotations.NotNull;

public abstract class AbstractGenericParameterWithId<T> extends AbstractGenericParameter {
    @NotNull(
        when = {"update", "delete"}
    )
    private T id;

    public AbstractGenericParameterWithId() {
    }

    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String toString() {
        return "AbstractGenericParameterWithId(id=" + this.getId() + ")";
    }
}
