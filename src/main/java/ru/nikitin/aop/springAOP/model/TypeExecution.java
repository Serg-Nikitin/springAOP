package ru.nikitin.aop.springAOP.model;

public enum TypeExecution {
    SYNC("sync", 1),
    ASYNC("async", 0);

    private final String typeName;

    TypeExecution(String typeName, int value) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
