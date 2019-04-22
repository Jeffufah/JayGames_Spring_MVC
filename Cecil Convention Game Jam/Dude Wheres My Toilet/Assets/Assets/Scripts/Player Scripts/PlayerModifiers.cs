using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerModifiers : MonoBehaviour
{  
    public float jumpPower;
    public float moveSpeed;

    public bool isSprinting;

    public float baseJumpPower;
    public float baseMoveSpeed;

    public float boostJumpPower;
    public float boostMoveSpeed;

    private void Start()
    {
        jumpPower = baseJumpPower;
        moveSpeed = baseMoveSpeed;
    }

    public void activateSuperJump(float timeLength)
    {
        StopCoroutine("jumpBoostTimer");
        StartCoroutine(jumpBoostTimer(timeLength));
    }

    private IEnumerator jumpBoostTimer(float timeLength)
    {
        float jumpBoostTimer = timeLength;

        jumpPower = boostJumpPower;

        while (jumpBoostTimer > 0)
        {
            yield return new WaitForEndOfFrame();

            jumpBoostTimer -= Time.deltaTime;
        }

        jumpPower = baseJumpPower;
    }

    public void activateSuperSpeed(float timeLength)
    {
        StopCoroutine("speedBoostTimer");
        StartCoroutine(speedBoostTimer(timeLength));
    }

    private IEnumerator speedBoostTimer(float timeLength)
    {
        float speedBoostTimer = timeLength;

        moveSpeed = boostMoveSpeed;

        while (speedBoostTimer > 0)
        {
            yield return new WaitForEndOfFrame();

            speedBoostTimer -= Time.deltaTime;
        }

        moveSpeed = baseMoveSpeed;
    }

    public void activateHealthBoost(float timeLength, float healthBoost)
    {
        StopCoroutine("healthBoostTimer");
        StartCoroutine(healthBoostTimer(timeLength, healthBoost));
    }

    private IEnumerator healthBoostTimer(float timeLength, float healthBoost)
    {
        float healthBoostTimer = timeLength;

        PlayerHealth ph = gameObject.GetComponent<PlayerHealth>();

        ph.maxHealth += healthBoost;
        ph.receiveHeal(healthBoost);

        while (healthBoostTimer > 0)
        {
            yield return new WaitForEndOfFrame();

            healthBoostTimer -= Time.deltaTime;
        }

        ph.maxHealth -= healthBoost;
    }
}
