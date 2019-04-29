interface Tankable {
  void update();
  boolean hasCollision(Tankable t);
  boolean isDead();
  boolean isEaten();
  double getX();
  double getY();
  double getSize();
  void bounce();
  void closest(Tankable t);
  double d(Tankable t);
}