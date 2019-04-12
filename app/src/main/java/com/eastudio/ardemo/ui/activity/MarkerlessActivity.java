package com.eastudio.ardemo.ui.activity;

/*

 In this demo we demonstrate one particular implementation of markerless tracking, which we refer to as "Floor Tracking".
 The floor aspect refers to the fact that we add the target node as a child of the gyroplacemanager's world.

 This world is placed on a plane representing the floor and so can be used for tracking AR content that remains fixed relative to a point on the floor

 */

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

import com.eastudio.ardemo.R;
import eu.kudan.kudan.ARAPIKey;
import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARArbiTrack;
import eu.kudan.kudan.ARGyroPlaceManager;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARNode;


@Route(path = "/com/ardemo/markerless")
public class MarkerlessActivity extends ARActivity implements
        GestureDetector.OnGestureListener{

    private GestureDetectorCompat gestureDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Create gesture recogniser to start and stop arbitrack
        gestureDetect = new GestureDetectorCompat(this,this);

        // Add the API Key, which can be found at https://wiki.kudan.eu/Development_License_Keys
        // This key will only work when using the com.eastudio.ardemo application Id
        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey("sVmoznmKZ+4nFEHD6HoslwpC26PNuBZGHrikUwyon2BKSvza1yu2CqbSrae+pHPr1NHjhsf5pHQOZn8IEqXlqXFodGsrOJhxJANbMOdvnRLUi9/QWGqyRL9FViDmyohw6e5R7U4Ex8H7d7spLLvhfp5HFv56DgLr8c8sC2ipDtv9g1IjOTaY7UGxata3eulG2A/UkIdRv2NcotZXqan01xQUWFAislEwlGguParEYiwu11T4mqtU3dQBbfxpvxbczjdYz493YG3rAO2RHgT+5M5TJShJsz2irkNo71JD2Fzqf4AR2b4+7t1c55zKjegXzGS6Xa/rpNn9yiXUn7rUYIHNvN3cEQa9HsZiVxAV4vJgxFS+T/AxfWqKrEg1uj6xF5MsodZ2EkZ8mqliYIsxZqnFz+Re2HeWG8wvrEob0ZwRIO0TxppAemZc3HChTAPLcNt5gzeBk0oRP4wnrFAFFBDi8XjDocwTSVw++hWZb1qNHzt6bKLsMDRT057UVuuZB6M8f7EOQD79Oah0Vrx/3DUK6e9BEV8oGFNHtk1wyYEkg0i6RLhVSokGx//Qj36A4gCz3h1OjtfB0OuukbNq7xI1L/FcNQLmGYNGZwszARjGr9ESw1gVAkbQMxaV27uo/KoIq4+nR7RL8iT7t7NAaXCFIi24RR+7WGjTvKqWYjA=");

        setContentView(R.layout.activity_markerless);
    }

    // This method should be overridden and all of the AR content setup placed within it
    // This method is called only at the point at which the AR Content is ready to be set up
    @Override
    public void setup() {
        super.setup();

        // 我们选择地板节点的方向，以便目标节点平放在地板上。我们将节点绕X轴旋转-90度
        float[] angles = {-(float)Math.PI/2.0f,0.0f, 0.0f};
        Quaternion floorOrientation = new Quaternion(angles);

        // 创建目标节点。目标节点是一个节点，其位置用于在启动仲裁时确定仲裁世界的初始位置。
        // 本例中的目标节点是kudan奶牛的图像节点
        Vector3f floorScale = new Vector3f(0.5f,0.5f,0.5f);
        ARImageNode floorTarget = createImageNode("tree_house.png",floorOrientation,floorScale);

        // 将我们的目标节点添加到陀螺仪管理器的世界中
        // 目标节点的位置用于确定仲裁世界的初始位置。
        addNodeToGyroPlaceManager(floorTarget);

        // 创建一个图像节点放置在仲裁的世界中
        // 我们可以选择与目标节点方向相同的跟踪节点
        Vector3f trackingScale = new Vector3f(1.0f,1.0f,1.0f);
        ARImageNode trackingImageNode = createImageNode("cowtracking.png",floorOrientation,trackingScale);

        // 设置仲裁
        setUpArbiTrack(floorTarget, trackingImageNode);
    }

    private ARImageNode createImageNode(String imageName, Quaternion orientation, Vector3f scale)
    {
        ARImageNode imageNode = new ARImageNode(imageName);
        imageNode.setOrientation(orientation);
        imageNode.setScale(scale);

        return imageNode;
    }

    private void addNodeToGyroPlaceManager(ARNode node)
    {
        // The gyroplacemanager positions it's world on a plane that represents the floor.
        // You can adjust the floor depth (The distance between the device and the floor) using ARGyroPlaceManager's floor depth variable.
        // The default floor depth is -150
        ARGyroPlaceManager gyroPlaceManager = ARGyroPlaceManager.getInstance();
        gyroPlaceManager.initialise();
        gyroPlaceManager.getWorld().addChild(node);
    }

    private void setUpArbiTrack(ARNode targetNode, ARNode childNode)
    {
        // Get the arbitrack manager and initialise it
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();
        arbiTrack.initialise();

        // Set it's target node
        arbiTrack.setTargetNode(targetNode);

        // Add the tracking image node to the arbitrack world
        arbiTrack.getWorld().addChild(childNode);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();

        // If arbitrack is tracking, stop the tracking so that it's world is no longer rendered, and make it's target nodes visible
        if (arbiTrack.getIsTracking())
        {
            arbiTrack.stop();
            arbiTrack.getTargetNode().setVisible(true);
        }
        // If it's not tracking, start the tracking and hide the target node

        // When arbitrack has started, it will take the initial position of it's target node and it's world will be rendered to the screen.
        // After this, it's pose will be continually updated to give the appearance of it remaining in the same place
        else
        {
            arbiTrack.start();
            arbiTrack.getTargetNode().setVisible(false);
        }

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
