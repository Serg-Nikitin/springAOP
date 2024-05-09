package ru.nikitin.aop.springAOP.model;

public enum TypeExecution {
    SYNC("sync"),
    ASYNC("async");

    private final String typeName;

    TypeExecution(String typeName) {
        this.typeName = typeName;
    }

    public static TypeExecution getType(String type) {
        return SYNC.getTypeName().equals(type) ? SYNC : ASYNC;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
