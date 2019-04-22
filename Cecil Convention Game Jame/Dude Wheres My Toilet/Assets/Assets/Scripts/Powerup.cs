using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Powerup : MonoBehaviour
{
    private CommandHandler commandHandler;

    public string powerUpType;

    void OnTriggerEnter(Collider theCollision)
    {
        if (powerUpType == "HealthPickup")
        {
            try
            {
                if (theCollision.gameObject.GetComponent<EntityIdentity>().entityName == "Player")
                {
                    int colEntityId = theCollision.gameObject.GetComponent<EntityIdentity>().getEntityId();
                    commandHandler = theCollision.gameObject.GetComponent<CommandHandler>();
                    HealthPickupCommand hpPickupCommand = new HealthPickupCommand(colEntityId);
                    commandHandler.SendCommand(hpPickupCommand);

                    int hpEntityId = gameObject.GetComponent<EntityIdentity>().getEntityId();
                    DestroyEntityCommand destroyCommand = new DestroyEntityCommand(hpEntityId);
                    commandHandler.SendCommand(destroyCommand);

                    int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
                    HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 1, true);
                    GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
                }
                else
                {
                    Debug.Log("Not player");
                }
            }
            catch
            {
                Debug.Log("Doesn't have EntityIdentity");
            }
        }

        if (powerUpType == "SpeedPickup")
        {
            try
            {
                if (theCollision.gameObject.GetComponent<EntityIdentity>().entityName == "Player")
                {
                    int colEntityId = theCollision.gameObject.GetComponent<EntityIdentity>().getEntityId();
                    commandHandler = theCollision.gameObject.GetComponent<CommandHandler>();
                    SpeedPickupCommand speedPickupCommand = new SpeedPickupCommand(colEntityId);
                    commandHandler.SendCommand(speedPickupCommand);

                    int spEntityId = gameObject.GetComponent<EntityIdentity>().getEntityId();
                    DestroyEntityCommand destroyCommand = new DestroyEntityCommand(spEntityId);
                    commandHandler.SendCommand(destroyCommand);

                    int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
                    HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 2, true);
                    GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
                }
            }
            catch
            {
                Debug.Log("Doesn't have EntityIdentity");
            }
        }

        if (powerUpType == "JumpPickup")
        {
            try
            {
                if (theCollision.gameObject.GetComponent<EntityIdentity>().entityName == "Player")
                {
                    int colEntityId = theCollision.gameObject.GetComponent<EntityIdentity>().getEntityId();
                    commandHandler = theCollision.gameObject.GetComponent<CommandHandler>();
                    JumpPickupCommand jumpPickupCommand = new JumpPickupCommand(colEntityId);
                    commandHandler.SendCommand(jumpPickupCommand);

                    int jpEntityId = gameObject.GetComponent<EntityIdentity>().getEntityId();
                    DestroyEntityCommand destroyCommand = new DestroyEntityCommand(jpEntityId);
                    commandHandler.SendCommand(destroyCommand);

                    int hudEntityId = GameObject.Find("HUD").GetComponent<EntityIdentity>().getEntityId();
                    HealthPickupUICommand healthPickupUICommand = new HealthPickupUICommand(hudEntityId, 3, true);
                    GetComponent<CommandHandler>().SendCommand(healthPickupUICommand);
                }
            }
            catch
            {
                Debug.Log("Doesn't have EntityIdentity");
            }
        }
    }
}
