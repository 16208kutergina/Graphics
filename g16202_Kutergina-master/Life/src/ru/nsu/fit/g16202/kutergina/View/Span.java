package ru.nsu.fit.g16202.kutergina.View;

import java.awt.*;
import java.util.Objects;

public class Span {
    private Point left;
    private Point right;

    public Point getLeft() {
        return left;
    }

    public Point getRight() {
        return right;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Span span = (Span) o;
        return left.equals(span.left) &&
                right.equals(span.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public Span(Point left, Point right) {
        this.left = left;
        this.right = right;
    }

}
