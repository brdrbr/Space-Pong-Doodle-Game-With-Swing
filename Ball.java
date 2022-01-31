package canvas;

public class Ball {

    private float x;
    private float y;
    private float deltaX;
    private float deltaY;
    private float radius;


    public Ball(float x, float y, float deltaX, float deltaY, float radius) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.radius = radius;
    }

    public float getY() {
        return y;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void move(){
        // now we change delta X and Y.
        setX(getX() + deltaX);
        setDeltaY(getDeltaY() + (float)9.8/600);
        setY(getY() + deltaY);
    }

    public void reverseDeltaX(){
        setDeltaX(-getDeltaX());
    }

    public void reverseDeltaY(){
        setDeltaY(-getDeltaY());
    }
}