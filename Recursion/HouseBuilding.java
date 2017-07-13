public class HouseBuilding {
  // like simple for loop
  public void buildLayerTail(int height) {
    if( height == 0) return;

    System.out.println(height);

    buildLayerTail(height - 1);
  }

  // use operating system stack memory
  public void buildLayerHead(int height) {
    if( height == 0) return;

    buildLayerHead(height - 1);

    System.out.println(height);
  }

  public static void main(String[] args) {
    HouseBuilding house = new HouseBuilding();
    house.buildLayerHead(5); // 1 2 3 4 5
    house.buildLayerTail(5); // 5 4 3 2 1
  }
}
