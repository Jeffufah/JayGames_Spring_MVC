using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
public class PlayerInputListener : MonoBehaviour
{
    private GameObject player;
    private CommandHandler commandHandler;
    private PlayerInventory playerInventory;

    int entityId;

    float jumpTimer;
    float jumpCooldown = 1f;

    private void Start()
    {
        jumpTimer = jumpCooldown;
        player = gameObject;
        commandHandler = GetComponent<CommandHandler>();
        entityId = player.GetComponent<EntityIdentity>().getEntityId();
        playerInventory = player.GetComponent<PlayerInventory>();
    }

    void Update()
    {
        jumpTimer += Time.deltaTime;

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("Main Menu");
        }

        if (Input.GetKey(KeyCode.W))
        {
            ForwardCommand forward = new ForwardCommand(entityId);
            commandHandler.SendCommand(forward);
        }

        if (Input.GetKey(KeyCode.S))
        {
            BackwardCommand backward = new BackwardCommand(entityId);
            commandHandler.SendCommand(backward);
        }

        if (Input.GetKey(KeyCode.A))
        {
            LeftCommand left = new LeftCommand(entityId);
            commandHandler.SendCommand(left);
        }

        if (Input.GetKey(KeyCode.D))
        {
            RightCommand right = new RightCommand(entityId);
            commandHandler.SendCommand(right);
        }

        //Health heal
        if (Input.GetKeyDown(KeyCode.Alpha1))
        {
            if (playerInventory.health != false)
            {
                HealthActivateCommand activateHealth = new HealthActivateCommand(entityId);
                commandHandler.SendCommand(activateHealth);
            }
        }

        //Speed boost
        if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            if (playerInventory.speedBoost != false)
            {
                SpeedActivateCommand activateSpeed = new SpeedActivateCommand(entityId);
                commandHandler.SendCommand(activateSpeed);
            }
        }

        //Jump boost
        if (Input.GetKeyDown(KeyCode.Alpha3))
        {
            if (playerInventory.jumpBoost != false)
            {
                JumpActivateCommand activateJump = new JumpActivateCommand(entityId);
                commandHandler.SendCommand(activateJump);
            }
        }

        if (Input.GetKeyDown(KeyCode.Space))
        {
            if (jumpTimer >= jumpCooldown)
            {
                jumpTimer = 0;
                JumpCommand jump = new JumpCommand(entityId);
                commandHandler.SendCommand(jump);
            }
        }

        if (Input.GetKey(KeyCode.LeftShift))
        {
            SprintCommand sprint = new SprintCommand(entityId, true);
            commandHandler.SendCommand(sprint);
        }
        else
        {
            SprintCommand sprint = new SprintCommand(entityId, false);
            commandHandler.SendCommand(sprint);
        }

        if (Input.GetAxis("Mouse X") != 0 || (Input.GetAxis("Mouse Y")) != 0)
        {
            float mouseX = Input.GetAxis("Mouse X");
            float mouseY = Input.GetAxis("Mouse Y");
            MouseLookCommand mouseLookCommand = new MouseLookCommand(entityId, mouseX, mouseY);
            commandHandler.SendCommand(mouseLookCommand);
        }
    }
}
