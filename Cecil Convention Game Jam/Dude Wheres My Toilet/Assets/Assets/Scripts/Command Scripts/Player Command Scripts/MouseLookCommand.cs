using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class MouseLookCommand : CommandBase
{
    private float mouseX;
    private float mouseY;

    public enum RotationAxes { MouseXAndY = 0, MouseX = 1, MouseY = 2 }
    public RotationAxes axes = RotationAxes.MouseXAndY;
    public float sensitivityX = 150F;
    public float sensitivityY = 150F;

    public float minimumX = -360F;
    public float maximumX = 360F;

    public float minimumY = -60F;
    public float maximumY = 60F;

    static float rotationY;// = 0F;

    public MouseLookCommand(int entityId, float mouseX, float mouseY)
    {
        this.entityId = entityId;
        commandType = "MouseLook";
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public override void ExecuteCommand()
    {
        GameObject obj = EntityManager.getEntity(entityId);

        FirstPersonCamera(obj);
    }

    public float getMouseX()
    {
        return mouseX;
    }

    public float getMouseY()
    {
        return mouseY;
    }

    void FirstPersonCamera(GameObject obj)
    {
        //Debug.Log("RotationY = " + mouseY);
        if (axes == RotationAxes.MouseXAndY)
        {
            float rotationX = obj.transform.localEulerAngles.y + mouseX * sensitivityX * Time.deltaTime;

            rotationY += mouseY * sensitivityY * Time.deltaTime;
            rotationY = Mathf.Clamp(rotationY, minimumY, maximumY);

            obj.transform.localEulerAngles = new Vector3(-rotationY, rotationX, 0);
        }
        else if (axes == RotationAxes.MouseX)
        {
            obj.transform.Rotate(0, mouseX * sensitivityX * Time.deltaTime, 0);
        }
        else
        {
            rotationY += mouseY * sensitivityY * Time.deltaTime;
            rotationY = Mathf.Clamp(rotationY, minimumY, maximumY);

            obj.transform.localEulerAngles = new Vector3(-rotationY, obj.transform.localEulerAngles.y, 0);
        }
    }
}
