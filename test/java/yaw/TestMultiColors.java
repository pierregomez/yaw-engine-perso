package yaw;

import org.joml.Vector3f;
import yaw.engine.UpdateCallback;
import yaw.engine.World;
import yaw.engine.items.Item;
import yaw.engine.items.ItemGroup;
import yaw.engine.items.ItemObject;
import yaw.engine.meshs.Mesh;
import yaw.engine.meshs.MeshBuilder;
import yaw.engine.meshs.Texture;

public class TestMultiColors implements UpdateCallback {
    private int nbUpdates = 0;
    private double totalDeltaTime = 0.0;
    private static long deltaRefreshMillis = 1000;
    private long prevDeltaRefreshMillis = 0;
    private Item cube ;

    public TestMultiColors(Item cube) {
        this.cube = cube;
    }

    public Item getItem() {
        return cube;
    }

    @Override
    public void update(double deltaTime) {
        nbUpdates++;
        totalDeltaTime += deltaTime;

        long currentMillis = System.currentTimeMillis();
        if (currentMillis - prevDeltaRefreshMillis > deltaRefreshMillis) {
            double avgDeltaTime = totalDeltaTime / (double) nbUpdates;
            //System.out.println("Average deltaTime = " + Double.toString(avgDeltaTime) +" s ("+nbUpdates+")");
            nbUpdates = 0;
            totalDeltaTime = 0.0;
            prevDeltaRefreshMillis = currentMillis;
        }
        //cube.rotateXYZ(0.05f, 0.2f, 0.f);


    }

    public static void main(String[] args) {
        World world = new World(0, 0, 700, 700);/* Create the world with its dimensions. */
        float[] vertices = new float[] {
                //Front face
                -1, 1, 1,   -1, -1, 1,   1, -1, 1,   1, 1, 1,
                //Top face
                -1, 1, -1,  -1, 1, 1,    1, 1, 1,    1, 1, -1,
                //Back face
                1, 1, -1,   1, -1, -1,   -1, -1, -1,   -1, 1, -1,
                //Bottom face
                1, -1, -1,  1, -1, 1,    -1, -1, 1,    -1, -1, -1,
                //Left face
                -1, 1, -1,  -1, -1, -1,    -1, -1, 1,    -1, 1, 1,
                //Right face
                1, 1, 1,     1, -1, 1,    1, -1, -1,    1, 1, -1,
        };


        float[] colours = new float[]{ 0.0f,1.0f,0.0f};


        int[] indices = new int[]{
                //Front face
                0, 1, 3, 1, 2, 3,
                //Top face
                7, 0, 3,7, 3, 4,







                //Back face
                8, 9, 10, 8, 10, 11,
                //Bottom Face
                12, 13, 14, 12, 14, 15,
                //Left face
                16, 17, 18, 16, 18, 19,
                //Right face
                20, 21, 22, 20, 22, 23
        };

        float[] normals = new float[]{
                //Front face
                0, 0, 1f, 0, 0, 1f, 0, 0, 1f, 0, 0, 1f,
                //Top face
                0, 1f, 0, 0, 1f, 0, 0, 1f, 0, 0, 1f, 0,
                //Back face
                0, 0, -1f, 0, 0, -1f, 0, 0, -1f, 0, 0, -1f,
                //Bottom face
                0, -1f, 0, 0, -1f, 0, 0, -1f, 0, 0, -1f, 0,
                //Left face
                -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
                //Right face
                1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0
        };

        float[] textCoord = new float[]{
              /*  0,0,
                0,1,
                1,1,
                1,0,

                0,0,
                0,1,
                1,1,
                1,0,

                0,0,
                0,1,
                1,1,
                1,0,

                0,0,
                0,1,
                1,1,
                1,0,

                0,0,
                0,1,
                1,1,
                1,0,

                0,0,
                0,1,
                1,1,
                1,0,

                */0.5f,0,
                0.5f,0.25f,
                0.75f,0.25f,
                0.75f,0,

                0.25f,0.25f,
                0.25f,0.5f,
                0.5f,0.5f,
                0.5f,0.25f,

                0.5f,0.25f,
                0.5f,0.5f,
                0.75f,0.5f,
                0.75f,0.25f,

                0.75f,0.25f,
                0.75f,0.5f,
                1,0.5f,
                1,0.25f,

                0.5f,0.5f,
                0.5f,0.75f,
                0.75f,0.75f,
                0.75f,0.5f,

                0.5f,0.75f,
                0.5f,1,
                0.75f,1,
                0.75f,0.75f
        };

        Mesh m1 = world.createMesh(
                vertices,
                textCoord,
                normals,
                indices,
                24,
                colours,
                "/ressources/dice.png"
        );

        /*

        ItemObject salut = world.createItemObject("cube", 0f, 0f, -2f, 1.0f, MeshBuilder.generateBlock(1, 1, 1));
        salut.getMesh().getMaterial().setTexture(new Texture("/ressources/diamond.png"));
        g.add("sixth",item6);
         */
        ItemObject item = world.createItemObject("item",0,0,-5,1.25f,m1);

        item.rotateXYZ(30,40, 0);

        TestMultiColors rGroup = new TestMultiColors(item);

        world.registerUpdateCallback(rGroup);
        world.launch();
        world.waitFortermination();
    }
}