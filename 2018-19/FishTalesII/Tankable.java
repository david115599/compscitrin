interface Tankable {
  void update();
  boolean hasCollision(Tankable t);
  boolean isDead();
  boolean isEaten();
  void becomeeaten();
  double getX();
  double getY();
  double getSize();
  int getAge();
  void bounce();
  void closest(Tankable t);
  double d(Tankable t);
  double setAmmonia(double a);
  void tap();

}
