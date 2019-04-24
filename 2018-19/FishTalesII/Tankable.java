interface Tankable {
  void update();
  boolean hasCollision(Tankable t);
  boolean isDead();
  double getX();
  double getY();
  double getSize();
  void bounce();
  void closest(Tankable t);
  double d(Tankable t);
}
